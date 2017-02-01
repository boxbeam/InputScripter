package redempt.inputscripter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import redempt.inputscripter.KeyHandler;
import redempt.inputscripter.Main;
import redempt.inputscripter.script.Script;

public class Keybind {
	
	private final int[] combo;
	private final Script action;
	private boolean active = true;
	public boolean pressed = false;
	private Runnable runnable;
	private static List<Keybind> keybinds = new CopyOnWriteArrayList<>();
	private long last = 0;
	
	public Keybind(int[] combo, Script action) {
		action.keybind = this;
		this.combo = combo;
		this.action = action;
		keybinds.add(this);
	}
	
	public int[] getCombo() {
		return combo.clone();
	}
	
	public static List<Keybind> getAll() {
		return keybinds;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
	@Override
	public String toString() {
		String combine = "";
		for (int key : combo) {
			combine += key + "+";
		}
		combine = combine.replaceAll("/$", "");
		combine += String.valueOf(active);
		combine += ":\n";
		combine += action.getText();
		return combine;
	}
	
	public void init() {
		runnable = () -> {
			if (!active) {
				return;
			}
			int count = 0;
			for (int press : KeyHandler.getPressed()) {
				for (int key : combo) {
					if (press == key) {
						count++;
					}
				}
			}
			if (count == combo.length) {
				pressed = true;
				activate();
			} else {
				pressed = false;
			}
		};
		KeyHandler.addAction(runnable);
	}
	
	public static Keybind fromString(String string) {
		String[] bindSplit = string.split(":");
		String bind = bindSplit[0];
		String[] codeSplit = bind.split("\\+");
		int[] combo = new int[codeSplit.length - 1];
		boolean active = false;
		for (int i = 0; i < codeSplit.length; i++) {
			String code = codeSplit[i];
			if (i == codeSplit.length - 1) {
				active = Boolean.parseBoolean(code);
			} else {
				combo[i] = Integer.parseInt(code);
			}
		}
		Keybind keybind = new Keybind(combo, new Script(bindSplit[1].replaceAll("^\n", "")));
		keybind.setActive(active);
		return keybind;
	}
	
	public Script getScript() {
		return action;
	}
	
	public void unregister() {
		action.stop();
		KeyHandler.removeAction(runnable);
		keybinds.remove(this);
	}
	
	private void activate() {
		if (action.hold || System.currentTimeMillis() - last < 100) {
			return;
		}
		last = System.currentTimeMillis();
		if (action.isRunning()) {
			action.kill();
			action.reset();
		} else {
			action.run();
		}
	}
	
	public static void saveAll() throws IOException, URISyntaxException {
		File folder = new File(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile(), Main.dir);
		folder.mkdirs();
		for (File file : folder.listFiles()) {
			file.delete();
		}
		int i = 0;
		for (Keybind keybind : keybinds) {
			File file = new File(folder, String.valueOf(i) + ".insc");
			FileWriter writer = new FileWriter(file);
			writer.write(keybind.toString());
			writer.close();
			i++;
		}
	}
	
	public static void reset() {
		for (Keybind keybind : keybinds) {
			keybind.unregister();
		}
	}
	
	public static void loadAll() throws IOException, URISyntaxException {
		File folder = new File(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile(), Main.dir);
		if (!folder.exists()) {
			return;
		}
		for (File file : folder.listFiles()) {
			if (file.getName().endsWith(".insc")) {
				FileReader read = new FileReader(file);
				BufferedReader reader = new BufferedReader(read);
				String combine = "";
				String next = "";
				while ((next = reader.readLine()) != null) {
					combine += next + "\n";
				}
				reader.close();
				Keybind keybind = Keybind.fromString(combine);
				keybind.init();
			}
		}
	}
	
}
