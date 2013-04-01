package skype;

import java.util.List;

import javax.swing.JOptionPane;

import sliceWars.remote.ContactListFrame;
import sliceWars.remote.ContactListFrame.ContactListSelected;

import com.skype.Application;
import com.skype.SkypeException;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException, InterruptedException {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtHandler());
		
		final ContactListFrame contactListFrame = new ContactListFrame(SkypeApp.getContacts());
		
		final Application application = SkypeApp.getApp();
		final SkypeBridge skypeBridge = new SkypeBridge(SkypeApp.getOwnId());
        
        contactListFrame.setContactListSelectedListener(new ContactListSelected(){@Override public void selected(List<String> selectedValuesList) {
        	if(selectedValuesList.size() == 0){
        		JOptionPane.showMessageDialog(null,"Select at least one contact.");
        		return;
        	}
        	contactListFrame.close();
        	skypeBridge.setServer();
        	skypeBridge.setInvited(selectedValuesList);
        	SkypeApp.connectToIds(selectedValuesList);
		}});
        
		application.addApplicationListener(skypeBridge);
        
	}
}
