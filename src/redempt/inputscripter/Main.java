package redempt.inputscripter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.LogManager;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jnativehook.NativeHookException;

import redempt.inputscripter.gui.MainWindow;
import redempt.inputscripter.utils.Keybind;

public class Main {
	
	public static String dir = "keybinds";
	
	public static void main(String[] args) throws NativeHookException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		LogManager.getLogManager().reset();
		KeyHandler.init();
		try {
			System.out.println("Loading keybinds...");
			Keybind.loadAll();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		MainWindow window = new MainWindow();
		window.setVisible(true);
	}
	
}
