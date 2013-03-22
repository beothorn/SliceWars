package skype;

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
                        System.out.println("received:" + receivedText);
                    }
                });
            }
        });
    }
}
