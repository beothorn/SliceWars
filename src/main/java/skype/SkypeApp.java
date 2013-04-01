package skype;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.lang.UnhandledException;

import com.skype.Application;
import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;
import com.skype.connector.Connector;
import com.thoughtworks.xstream.XStream;

public class SkypeApp {

	public static String[] getContacts() throws SkypeException {
		Friend[] allFriends;
		allFriends = Skype.getContactList().getAllFriends();
	    List<String> contacts = new ArrayList<String>();
	    for (Friend friend : allFriends) {
	    	contacts.add(friend.getId());
		}
	    return contacts.toArray(new String[0]);
	}

	public static final String APPNAME = "Slicewars";
	private static Application application;

	public static void showDisconnectedMessageAndExit(Stream stream) {
		JOptionPane.showMessageDialog(null,stream.getId()+" disconnected from "+stream.getApplication().getName()+".");
		System.exit(0);
	}

	public static Application getApp() throws SkypeException {
		if(application == null){
			Connector.getInstance().setApplicationName(APPNAME);
		    Skype.setDebug(true);
		    Skype.setDaemon(false);    
		    application = Skype.addApplication(APPNAME);
		}
		return application;
	}

	public static void sendMessageThroughStream(Object object,final Stream stream) {
		XStream xstream = new XStream();
		String xml = xstream.toXML(object);
		try {
			stream.write(xml);
		} catch (SkypeException e) {throw new RuntimeException(e);}
	}

	public static String getOwnId() {
		try {
			return Skype.getProfile().getId();
		} catch (SkypeException e) {throw new RuntimeException(e);}
	}

	public static void connectToIds(List<String> selectedValuesList) {
		try {
    		for (String contactId : selectedValuesList) {
            	application.connect(contactId);
    		}
		} catch (SkypeException e){throw new UnhandledException(e);}
	}

}
