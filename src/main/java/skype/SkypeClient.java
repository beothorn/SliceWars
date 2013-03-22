package skype;

import java.util.Arrays;

import javax.swing.JOptionPane;

import com.skype.Application;
import com.skype.ApplicationAdapter;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.Stream;

public class SkypeClient {
	
	public static final String APPNAME = "Slice wars";
	
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
            }

            @Override
            public void disconnected(Stream stream) throws SkypeException {
                printApplicationAndStreamName("disconnected:", stream);
            }

            private void printApplicationAndStreamName(String header, Stream stream) {
                System.out.println(header + stream.getApplication().getName() + "-" + stream.getId());
            }
        });
        
        String oponnentId = JOptionPane.showInputDialog(null, "Enter player id : ", "gabrielsan", 1);
        return application.connect(oponnentId);
    }
}
