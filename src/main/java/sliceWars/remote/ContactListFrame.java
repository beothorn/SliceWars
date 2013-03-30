package sliceWars.remote;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ContactListFrame {
	
	private JFrame jFrame;

	public interface ContactListSelected{ public void selected(final List<String> selectedValuesList); };
	
	private ContactListSelected contactListSelected;
	
	public ContactListFrame(final String[] contacts) {
		jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final JList<String> contactList = new JList(contacts);
		contactList.setSize(100, 800);
		JScrollPane jScrollPane = new JScrollPane(contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jFrame.add(jScrollPane, BorderLayout.CENTER);
		
		Button buttonPlay = new Button("Play");
		buttonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactListSelected.selected(contactList.getSelectedValuesList());
			}
		});
		
		jFrame.add(buttonPlay, BorderLayout.SOUTH);
		
		jFrame.pack();
        jFrame.setVisible(true);
	}
	
	public void setContactListSelectedListener(final ContactListSelected contactListSelectedListener){
		this.contactListSelected = contactListSelectedListener;
	}
	
	public void close(){
		jFrame.setVisible(false);
		jFrame.dispose();
	}

}
