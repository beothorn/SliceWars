package skype;

import java.util.List;
import org.apache.commons.lang.UnhandledException;
import sliceWars.remote.ContactListFrame;
import sliceWars.remote.ContactListFrame.ContactListSelected;

import com.skype.Application;
import com.skype.SkypeException;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException, InterruptedException {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtHandler());
		
		final ContactListFrame contactListFrame = new ContactListFrame(SkypeApp.getContacts());
		
		final Application application = SkypeApp.getApp();
		final SkypeBridge listener = new SkypeBridge(SkypeApp.getOwnId());
        
        contactListFrame.setContactListSelectedListener(new ContactListSelected(){@Override public void selected(List<String> selectedValuesList) {
        	contactListFrame.close();
        	listener.setServer();
        	listener.setInvited(selectedValuesList);
        	try {
        		for (String contactId : selectedValuesList) {
                	application.connect(contactId);
        		}
			} catch (SkypeException e){throw new UnhandledException(e);}
		}});
        
		application.addApplicationListener(listener);
        
	}
}
