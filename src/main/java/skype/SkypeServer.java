package skype;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.commons.lang.UnhandledException;

import sliceWars.RemoteInvite;
import sliceWars.gui.GuiPlayer;
import sliceWars.logic.Player;

import com.skype.*;

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
                    	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedText.getBytes());
                        ObjectInputStream ois;
                        RemoteInvite invite;
						try {
							ois = new ObjectInputStream(byteArrayInputStream);
							invite = (RemoteInvite) ois.readObject();
							ois.close();
						} catch (IOException e) {
							throw new UnhandledException(e);
						} catch (ClassNotFoundException e) {
							throw new UnhandledException(e);
						}
						new GuiPlayer(new Player(2, 2), null, invite.get_randomSeed() , invite.get_numberOfPlayers(),invite.get_lines(),invite.get_columns(),invite.get_randomlyScenarioCells());
                    }
                });
            }
        });
    }
}
