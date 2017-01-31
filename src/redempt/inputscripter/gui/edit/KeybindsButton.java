package redempt.inputscripter.gui.edit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class KeybindsButton extends JButton {
	
	private static final long serialVersionUID = -2127421272738283111L;

	public KeybindsButton() {
		super();
		this.setText("Keybinds");
		this.setSize(150, 30);
		this.setLocation(150, 0);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new KeybindList();
				}
			}
			
		});
	}
	
}
