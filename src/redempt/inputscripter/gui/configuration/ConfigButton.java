package redempt.inputscripter.gui.configuration;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import redempt.inputscripter.gui.keybind.KeybindMaker;

public class ConfigButton extends JButton {
	
	private static final long serialVersionUID = -8561844273484282522L;

	public ConfigButton() {
		super();
		this.setSize(150, 30);
		this.setText("New keybind");
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new KeybindMaker();
				}
			}
			
		});
	}
	
}
