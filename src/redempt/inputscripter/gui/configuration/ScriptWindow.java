package redempt.inputscripter.gui.configuration;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ScriptWindow extends JFrame {
	
	private static final long serialVersionUID = 4900480254618803981L;
	private boolean closed = false;
	private JTextArea text;
	
	public ScriptWindow() {
		super();
		this.setSize(400, 300);
		this.setTitle("Configuration");
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setSize(400, 300);
		panel.setLayout(null);
		this.setLayout(null);
		panel.setBackground(Color.WHITE);
		this.add(panel);
		text = new JTextArea();
		text.setSize(400, 300);
		text.setVisible(true);
		panel.add(text);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				closed = true;
			}
			
		});
	}
	
	public ScriptWindow(String script) {
		this();
		text.setText(script);
	}
	
	public String join() {
		while (!closed) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return text.getText();
	}
	
}
