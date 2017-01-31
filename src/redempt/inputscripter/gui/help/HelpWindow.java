package redempt.inputscripter.gui.help;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpWindow extends JFrame {

	public HelpWindow() {
		super();
		this.setSize(300, 800);
		this.setTitle("Help");
		this.setVisible(true);
		this.setResizable(false);
		JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(11F));
		String text = combine("Welcome to InputScripter!<br>",
				"InputScripter allows you to make keybinds to do various things",
				"To start, simply click 'New keybind'",
				"Press a key combo, and a script window will appear",
				"You can type a script into this window",
				"Scripts will be executed, line by line, in order",
				"Here's a list of commands:<br>",
				"click [left/right/middle]",
				"Clicks the left/right/middle mouse (holds it down)<br>",
				"unclick [left/right/middle]",
				"Releases the left/right/middle moust<br>",
				"press [key]",
				"Presses a key on the keyboard (holds it down)<br>",
				"unpress [key]",
				"Releases a key on the keyboard<br>",
				"x [x]",
				"Moves the mouse to the specified X<br>",
				"y [y]",
				"Moves the mouse to the specified Y<br>",
				"x+ [x]",
				"Increases the mouse X by the specified amount<br>",
				"y+ [y]",
				"Increases the mouse Y by the specified amount<br>",
				"wait [time]",
				"Waits for the specified time, in milliseconds<br>",
				"print [text]",
				"Prints text to console (mainly for debug)<br>",
				"indicator [name]",
				"Displays an indicator while this script is active<br>",
				"repeat",
				"Makes the script repeat, and the keybind a toggle<br>",
				"You can also edit keybinds",
				"Clicking on the button, you'll see a list of all keybinds",
				"Left click one to edit or delete it",
				"Right click one to toggle it being active or not",
				"Click the 'Set directory' button to change the save/load directory for the rest of this session");
		label.setText(text);
		label.setVerticalAlignment(JLabel.TOP);
		label.setSize(300, 800);
		label.setVisible(true);
		this.setLayout(null);
		this.add(label);
	}
	
	private String combine(String... strings) {
		String combine = "<html>";
		for (String string : strings) {
			combine += string + "<br>";
		}
		combine += "</html>";
		return combine;
	}
	
}
