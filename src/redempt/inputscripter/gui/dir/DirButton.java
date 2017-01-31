package redempt.inputscripter.gui.dir;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class DirButton extends JButton {
	
	public DirButton() {
		super();
		this.setSize(150, 30);
		this.setLocation(150, 30);
		this.setText("Set directory");
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new DirWindow();
				}
			}
			
		});
	}
	
}
