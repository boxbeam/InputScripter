package redempt.inputscripter.gui.edit;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import org.jnativehook.keyboard.NativeKeyEvent;

import redempt.inputscripter.utils.Keybind;

public class KeybindEntry extends JButton {
	
	public KeybindEntry(Keybind keybind, KeybindList list) {
		super();
		String combine = "";
		for (int key : keybind.getCombo()) {
			combine += NativeKeyEvent.getKeyText(key) + " ";
		}
		this.setText(combine);
		this.setSize(280, 40);
		this.setMargin(new Insets(0, 0, 0, 0));
		if (!keybind.isActive()) {
			this.setText("( " + this.getText() + ")");
		}
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					new KeybindEditor(keybind, list);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					keybind.setActive(!keybind.isActive());
					list.refresh();
				}
			}
			
		});
	}
	
}
