package skype;

import sliceWars.RemoteInvite;
import sliceWars.gui.GuiPlayer;
import sliceWars.logic.Player;

import com.skype.Application;
import com.skype.ApplicationAdapter;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;
import com.skype.StreamAdapter;
import com.thoughtworks.xstream.XStream;

public class SkypeServer {
    public static void main(String[] args) throws Exception {
        Skype.setDebug(true);
        Skype.setDaemon(false);
        Application application = Skype.addApplication(SkypeClient.APPNAME);
        application.addApplicationListener(new ApplicationAdapter() {
            @Override
            public void connected(Stream stream) throws SkypeException {
                System.out.println("connected:" + stream.getId());
                stream.addStreamListener(new StreamAdapter() {
                    @Override
                    public void textReceived(String receivedText) throws SkypeException {
                    	XStream xstream = new XStream();
                    	RemoteInvite invite = (RemoteInvite) xstream.fromXML(receivedText);
						new GuiPlayer(new Player(2, 2), null, invite.get_randomSeed() , invite.get_numberOfPlayers(),invite.get_lines(),invite.get_columns(),invite.get_randomlyScenarioCells());
                    }
                });
            }
        });
    }
}
