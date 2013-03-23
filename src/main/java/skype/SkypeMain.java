package skype;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.lang.UnhandledException;

import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException, InterruptedException {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
		    public void uncaughtException(Thread t, Throwable e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		        System.exit(1);
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
		final AtomicReference<String> opponentId = new AtomicReference<String>();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		buttonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opponentId.set(contactList.getSelectedValue());
				countDownLatch.countDown();
			}
		});
		buttons.add(buttonPlay);
		
		Button buttonServer = new Button("Server");
		buttonServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jFrame.dispose();
					SkypeServer.main(null);
				} catch (SkypeException ex) {
					throw new UnhandledException(ex);
				}
				countDownLatch.countDown();
			}
		});
		buttons.add(buttonServer);
		
		jFrame.add(buttons, BorderLayout.SOUTH);
		
		jFrame.pack();
        jFrame.setVisible(true);
        
        try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			throw new UnhandledException(e1);
		}
        
        SkypeClient.connectTo(opponentId.get());
	}
}
