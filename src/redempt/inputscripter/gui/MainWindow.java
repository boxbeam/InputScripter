package redempt.inputscripter.gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import redempt.inputscripter.gui.configuration.ConfigButton;
import redempt.inputscripter.gui.dir.DirButton;
import redempt.inputscripter.gui.edit.KeybindsButton;
import redempt.inputscripter.gui.help.HelpButton;
import redempt.inputscripter.utils.Keybind;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = -1180000923066620806L;
	
	public final JPanel panel;
	
	public MainWindow() {
		super();
		this.setSize(300, 100);
		this.setTitle("InputScripter");
		this.setResizable(false);
		this.setFocusable(false);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					System.out.println("Saving keybinds...");
					Keybind.saveAll();
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
			
		});
		panel = new JPanel();
		this.setLayout(null);
		panel.setLayout(null);
		panel.setSize(300, 100);
		panel.setBackground(Color.WHITE);
		panel.setVisible(true);
		this.add(panel);
		panel.add(new ConfigButton());
		panel.add(new KeybindsButton());
		panel.add(new HelpButton());
		panel.add(new DirButton());
	}
	
}
