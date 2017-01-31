package redempt.inputscripter.gui.edit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import redempt.inputscripter.gui.configuration.ScriptWindow;
import redempt.inputscripter.gui.keybind.KeybindMaker;
import redempt.inputscripter.script.Script;
import redempt.inputscripter.utils.Keybind;

public class KeybindEditor extends JFrame {
	
	public KeybindEditor(Keybind keybind, KeybindList list) {
		super();
		this.setSize(300, 120 + this.getContentPane().getHeight());
		this.setResizable(false);
		this.setTitle("Edit keybind");
		this.setVisible(true);
		this.setLayout(null);
		JLabel text = new JLabel();
		text.setText("Choose action:");
		text.setSize(300, 50);
		text.setVisible(true);
		text.setHorizontalAlignment(JLabel.CENTER);
		this.add(text);
		KeybindEditor self = this;
		JButton delete = new JButton("Delete");
		delete.setSize(100, 50);
		delete.setLocation(0, 50);
		delete.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					self.dispose();
					keybind.unregister();
				}
			}
			
		});
		this.add(delete);
		JButton editScript = new JButton("Edit script");
		editScript.setSize(100, 50);
		editScript.setLocation(100, 50);
		editScript.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					self.dispose();
					ScriptWindow window = new ScriptWindow(keybind.getScript().getText());
					Thread thread = new Thread(() -> {
						String text = window.join();
						keybind.unregister();
						Keybind newKeybind = new Keybind(keybind.getCombo(), new Script(text));
						newKeybind.setActive(keybind.isActive());
						newKeybind.init();
						list.refresh();
					});
					thread.start();
				}
			}
			
		});
		this.add(editScript);
		JButton editKeybind = new JButton("Edit keybind");
		editKeybind.setSize(100, 50);
		editKeybind.setLocation(200, 50);
		editKeybind.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					self.dispose();
					KeybindMaker maker = new KeybindMaker(keybind);
					Thread thread = new Thread(() -> {
						int[] combo = maker.join();
						if (combo == null) {
							return;
						}
						keybind.unregister();
						Keybind newKeybind = new Keybind(combo, new Script(keybind.getScript().getText()));
						newKeybind.setActive(keybind.isActive());
						newKeybind.init();
						list.refresh();
					});
					thread.start();
				}
			}
			
		});
		this.add(editKeybind);
	}
	
}
