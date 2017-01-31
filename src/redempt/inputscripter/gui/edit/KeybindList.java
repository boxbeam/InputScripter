package redempt.inputscripter.gui.edit;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import redempt.inputscripter.utils.Keybind;

public class KeybindList extends JFrame {
	
	private List<KeybindEntry> entries = new ArrayList<>();
	
	public KeybindList() {
		super();
		this.setTitle("Keybinds");
		this.setSize(300, 500);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(null);
		KeybindList self = this;
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int y = 0;
				for (KeybindEntry entry : entries) {
					entry.setLocation(0, y - scrollbar.getValue() * self.getComponents().length);
					y += 40;
				}
			}
			
		});
		scrollbar.setSize(20, 500);
		scrollbar.setLocation(280, 0);
		scrollbar.setVisible(true);
		this.add(scrollbar);
		int y = 0;
		for (Keybind keybind : Keybind.getAll()) {
			KeybindEntry entry = new KeybindEntry(keybind, this);
			entry.setLocation(0, y);
			this.add(entry);
			entries.add(entry);
			y += 40;
		}
	}
	
	public void refresh() {
		for (KeybindEntry entry : entries) {
			this.remove(entry);
		}
		entries.clear();
		int y = 0;
		for (Keybind keybind : Keybind.getAll()) {
			KeybindEntry entry = new KeybindEntry(keybind, this);
			entry.setLocation(0, y);
			this.add(entry);
			entries.add(entry);
			y += 40;
		}
	}
	
}
