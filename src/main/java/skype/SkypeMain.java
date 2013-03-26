package skype;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.lang.UnhandledException;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException, InterruptedException {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
		    public void uncaughtException(Thread t, Throwable e) {
				final JFrame jFrame = new JFrame("Erro");
				jFrame.setLayout(new BorderLayout());
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JLabel jLabel = new JLabel("Erro");
				if(e instanceof com.skype.NotAttachedException){
					jLabel.setText("Skype is closed, offline or "+APPNAME+" wasn't authorized to use skype.");
				}
				jFrame.add(jLabel,BorderLayout.NORTH);
				
				JTextArea jTextArea = new JTextArea();
				jTextArea.setEditable(false);
				jTextArea.setText(e.getMessage()+"\n"+ExceptionUtils.getFullStackTrace(e));
				jFrame.add(jTextArea,BorderLayout.CENTER);
				JButton close = new JButton("Close application");
				close.addActionListener(new ActionListener(){@Override public void actionPerformed(ActionEvent e) {
						jFrame.dispose();
				}});
				jFrame.add(close, BorderLayout.SOUTH);
				
				jFrame.pack();
				jFrame.setVisible(true);
		    }
		});
		
		Friend[] allFriends;
		allFriends = Skype.getContactList().getAllFriends();
        List<String> contacts = new ArrayList<String>();
        for (Friend friend : allFriends) {
        	contacts.add(friend.getId());
		}
        
        final JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final JList<String> contactList = new JList(contacts.toArray());
		contactList.setSize(100, 800);
		JScrollPane jScrollPane = new JScrollPane(contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jFrame.add(jScrollPane, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		Button buttonPlay = new Button("Play");
		buttonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String opponentId = contactList.getSelectedValue();
				try {
					jFrame.dispose();
					SkypeClient.connectTo(opponentId);
				} catch (SkypeException | InterruptedException ex) {
					throw new UnhandledException(ex);
				}
			}
		});
		buttons.add(buttonPlay);
		
		Button buttonServer = new Button("Server");
		buttonServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jFrame.dispose();
					SkypeServer.awaitConnection();
				} catch (SkypeException ex) {
					throw new UnhandledException(ex);
				}
			}
		});
		buttons.add(buttonServer);
		
		jFrame.add(buttons, BorderLayout.SOUTH);
		
		jFrame.pack();
        jFrame.setVisible(true);
        
	}

	public static final String APPNAME = "Slicewars";

	public static void showDisconnectedMessageAndExit(Stream stream) {
		JOptionPane.showMessageDialog(null,stream.getId()+" disconnected from "+stream.getApplication().getName()+".");
		System.exit(0);
	}
}
