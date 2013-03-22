package skype;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

import sliceWars.RemoteInvite;
import sliceWars.gui.GuiPlayer;
import sliceWars.logic.Player;

import com.skype.Application;
import com.skype.ApplicationAdapter;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;
import com.thoughtworks.xstream.XStream;

public class SkypeClient {
	
	public static final String APPNAME = "Slicewars";
	
	public static void main(String[] args) throws Exception {
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
        
        String opponentId = JOptionPane.showInputDialog(null, "Enter player id : ", "gabrielsan", 1);
        return application.connect(opponentId);
    }
}
