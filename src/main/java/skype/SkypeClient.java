package skype;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.commons.lang.UnhandledException;

import sliceWars.RemoteInvite;
import sliceWars.gui.GuiPlayer;
import sliceWars.logic.Player;

import com.skype.Application;
import com.skype.ApplicationAdapter;
import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;
import com.skype.connector.Connector;
import com.thoughtworks.xstream.XStream;

public class SkypeClient {
	
	public static final String APPNAME = "Slicewars";
	
	public static void main(String[] args) throws Exception {
		Connector.getInstance().setApplicationName(APPNAME);
        Skype.setDebug(true);
        Skype.setDaemon(false);
        
        Stream[] streams = connectToServer(APPNAME);
        
        for (int i = 0; i < 26; i++) {
            for (Stream stream: streams) {
                stream.write(createData(i + 1, (char)('a' + i)));
            }
        }
        Thread.sleep(5000);
        for (Stream stream: streams) {
            stream.disconnect();
        }
    }

    private static String createData(int length, char character) {
        byte[] data = new byte[length];
        Arrays.fill(data, (byte)character);
        return new String(data);
    }

    private static Stream[] connectToServer(String name) throws SkypeException {
        Application application = Skype.addApplication(name);
        application.addApplicationListener(new ApplicationAdapter() {
            @Override
            public void connected(Stream stream) throws SkypeException {
                printApplicationAndStreamName("connected:", stream);
                Random random = new Random(Calendar.getInstance().getTimeInMillis());
        		int randomSeed = random.nextInt();
                int numberOfPlayers = 2;
				int lines = 6;
				int columns = 6;
				int randomScenariosCellsCount = 12;
				RemoteInvite remoteInvite = new RemoteInvite(randomSeed, numberOfPlayers, lines, columns, randomScenariosCellsCount);
                
				XStream xstream = new XStream();
				String xml = xstream.toXML(remoteInvite);
				stream.write(xml);
				
				new GuiPlayer(new Player(1, 2), null, randomSeed , numberOfPlayers,lines,columns,randomScenariosCellsCount);

            }

            @Override
            public void disconnected(Stream stream) throws SkypeException {
                printApplicationAndStreamName("disconnected:", stream);
            }

            private void printApplicationAndStreamName(String header, Stream stream) {
                System.out.println(header + stream.getApplication().getName() + "-" + stream.getId());
            }
        });
        
        Friend[] allFriends = Skype.getContactList().getAllFriends();
        List<String> contacts = new ArrayList<String>();
        for (Friend friend : allFriends) {
        	contacts.add(friend.getId());
		}
        
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final JList<String> contactList = new JList(contacts.toArray());
		contactList.setSize(100, 800);
		JScrollPane jScrollPane = new JScrollPane(contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jFrame.add(jScrollPane, BorderLayout.CENTER);
		Button button = new Button("Play");
		final AtomicReference<String> opponentId = new AtomicReference<String>();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opponentId.set(contactList.getSelectedValue());
				countDownLatch.countDown();
			}
		});
		jFrame.add(button, BorderLayout.SOUTH);
		jFrame.pack();
        jFrame.setVisible(true);
        
        try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			throw new UnhandledException(e1);
		}
        String oponnent = opponentId.get();
		return application.connect(oponnent);
    }
}
