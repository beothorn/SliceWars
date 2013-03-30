package skype;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.commons.lang.exception.ExceptionUtils;

public class UncaughtHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		final JFrame jFrame = new JFrame("Erro");
		jFrame.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel jLabel = new JLabel("Erro");
		if(e instanceof com.skype.NotAttachedException){
			jLabel.setText("Skype is closed, offline or "+SkypeApp.APPNAME+" wasn't authorized to use skype.");
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

}
