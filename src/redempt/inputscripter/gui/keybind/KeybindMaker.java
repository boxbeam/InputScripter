package redempt.inputscripter.gui.keybind;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jnativehook.keyboard.NativeKeyEvent;

import redempt.inputscripter.KeyHandler;
import redempt.inputscripter.gui.configuration.ScriptWindow;
import redempt.inputscripter.script.Script;
import redempt.inputscripter.utils.Keybind;

public class KeybindMaker extends JFrame {
	
	private static final long serialVersionUID = -5754169013406600470L;
	private int[] combo = null;
	private boolean closed = false;
	
	public KeybindMaker() {
		super();
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				closed = true;
			}
		
		});
		this.setSize(200, 70);
		this.setTitle("Keybind");
		this.setResizable(false);
		this.setVisible(true);
		JPanel panel = new JPanel();
		panel.setSize(200, 70);
		panel.setBackground(Color.WHITE);
		panel.setVisible(true);
		this.add(panel);
		JLabel label = new JLabel("Set keybind");
		label.setSize(200, 70);
		label.setVisible(true);
		panel.add(label);
		KeybindMaker self = this;
		KeyHandler.addAction(new Runnable() {
			
			int most = 0;
			int[] keys;
			
			@Override
			public void run() {
				if (!self.isFocused()) {
					return;
				}
				if (KeyHandler.getPressed().size() > most) {
					most = KeyHandler.getPressed().size();
					Integer[] key = KeyHandler.getPressed().toArray(new Integer[KeyHandler.getPressed().size()]);
					keys = new int[key.length];
					for (int i = 0; i < key.length; i++) {
						keys[i] = key[i];
					}
				}
				if (KeyHandler.getPressed().size() == 0 && most != 0) {
					KeyHandler.removeAction(this);
					String combine = "";
					for (int key : keys) {
						combine += NativeKeyEvent.getKeyText(key) + " ";
					}
					label.setText(combine);
					ScriptWindow window = new ScriptWindow();
					Script script = new Script(window.join());
					Keybind keybind = new Keybind(keys, script);
					keybind.init();
					self.dispose();
				}
			}
			
		});
	}
	
	public KeybindMaker(Keybind old) {
		super();
		this.setSize(200, 70);
		this.setTitle("Keybind");
		this.setResizable(false);
		this.setVisible(true);
		JPanel panel = new JPanel();
		panel.setSize(200, 70);
		panel.setBackground(Color.WHITE);
		panel.setVisible(true);
		this.add(panel);
		JLabel label = new JLabel("Set keybind");
		label.setSize(200, 70);
		label.setVisible(true);
		panel.add(label);
		KeybindMaker self = this;
		KeyHandler.addAction(new Runnable() {
			
			int most = 0;
			int[] keys;
			
			@Override
			public void run() {
				if (!self.isFocused()) {
					return;
				}
				if (KeyHandler.getPressed().size() > most) {
					most = KeyHandler.getPressed().size();
					Integer[] key = KeyHandler.getPressed().toArray(new Integer[KeyHandler.getPressed().size()]);
					keys = new int[key.length];
					for (int i = 0; i < key.length; i++) {
						keys[i] = key[i];
					}
				}
				if (KeyHandler.getPressed().size() == 0 && most != 0) {
					KeyHandler.removeAction(this);
					combo = keys;
				}
			}
			
		});
	}
	
	public int[] join() {
		while (combo == null) {
			if (closed) {
				return null;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		dispose();
		return combo;
	}
	
}
