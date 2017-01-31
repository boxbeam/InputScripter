package redempt.inputscripter.gui.help;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class HelpButton extends JButton {
	
	public HelpButton() {
		super();
		this.setText("Help");
		this.setSize(150, 30);
		this.setLocation(0, 30);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new HelpWindow();
				}
			}
			
		});
	}
	
}
