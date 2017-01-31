package redempt.inputscripter.gui.dir;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import redempt.inputscripter.Main;
import redempt.inputscripter.utils.Keybind;

public class DirWindow extends JFrame {
	
	public DirWindow() {
		super();
		this.setSize(400, 60);
		this.setTitle("Set directory");
		JTextField text = new JTextField(Main.dir);
		text.setSize(300, 30);
		text.setVisible(true);
		this.setLayout(null);
		this.add(text);
		JButton go = new JButton("Go");
		go.setSize(100, 30);
		go.setLocation(300, 0);
		go.setVisible(true);
		DirWindow self = this;
		go.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					self.dispose();
					try {
						Keybind.saveAll();
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
					Keybind.reset();
					Main.dir = text.getText();
					try {
						Keybind.loadAll();
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
		this.add(go);
		this.setVisible(true);
	}
	
}
