package skype;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import sliceWars.RemoteInvite;
import sliceWars.RemotePlay;
import sliceWars.gui.GuiPlayer;
import sliceWars.logic.Player;

import com.skype.Application;
import com.skype.ApplicationAdapter;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;
import com.skype.StreamAdapter;
import com.skype.connector.Connector;
import com.thoughtworks.xstream.XStream;

public class SkypeClient {
	
	public static void connectTo(String serverContactId) throws SkypeException, InterruptedException {
		Connector.getInstance().setApplicationName(SkypeMain.APPNAME);
        Skype.setDebug(true);
        Skype.setDaemon(false);
        
        Stream[] streams = connectToServer(serverContactId);
        
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

    private static Stream[] connectToServer(String serverContactId) throws SkypeException {
        Application application = Skype.addApplication(SkypeMain.APPNAME);
        final SkypePlayBroadcaster skypePlayBroadcaster = new SkypePlayBroadcaster();
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
				
				stream.addStreamListener(new StreamAdapter() {
                    @Override
                    public void textReceived(String receivedText) throws SkypeException {
                    	XStream xstream = new XStream();
                    	RemotePlay play = (RemotePlay)xstream.fromXML(receivedText);
                    	skypePlayBroadcaster.play(play);
                    }
                });
				
				skypePlayBroadcaster.setSkypePlayerStream(stream);
				GuiPlayer guiPlayer = new GuiPlayer(new Player(1, 2), skypePlayBroadcaster, randomSeed , numberOfPlayers,lines,columns,randomScenariosCellsCount);
				skypePlayBroadcaster.setLocalPlayer(guiPlayer);

            }

            @Override
            public void disconnected(Stream stream) throws SkypeException {
                printApplicationAndStreamName("disconnected:", stream);
            }

            private void printApplicationAndStreamName(String header, Stream stream) {
                System.out.println(header + stream.getApplication().getName() + "-" + stream.getId());
            }
        });
        
                
		return application.connect(serverContactId);
    }
}
