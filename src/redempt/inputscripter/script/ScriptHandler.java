package redempt.inputscripter.script;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import redempt.inputscripter.gui.indicator.Indicator;

public class ScriptHandler {
	
	private static Robot robot;
	
	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void run(Script script) {
		Thread thread = new Thread(() -> {
			String[] lines = script.getText().split("\n");
			runLines(lines, script);
		});
		thread.start();
	}
	
	private static void runLines(String[] lines, Script script) {
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.startsWith("click ")) {
				String rest = line.replace("click ", "");
				switch (rest) {
					case "left":
						robot.mousePress(MouseEvent.BUTTON1_MASK);
						break;
					case "right":
						robot.mousePress(MouseEvent.BUTTON3_MASK);
						break;
					case "middle":
						robot.mousePress(MouseEvent.BUTTON2_MASK);
						break;
					default:
						System.out.println("Unknown click type " + rest);
						break;
				}
			}
			if (line.startsWith("unclick ")) {
				String rest = line.replace("unclick ", "");
				switch (rest) {
					case "left":
						robot.mouseRelease(MouseEvent.BUTTON1_MASK);
						break;
					case "right":
						robot.mouseRelease(MouseEvent.BUTTON2_MASK);
						break;
					case "middle":
						robot.mouseRelease(MouseEvent.BUTTON3_MASK);
						break;
					default:
						System.out.println("Unknown click type " + rest);
						break;
				}
			}
			if (line.startsWith("wait ")) {
				String rest = line.replace("wait ", "");
				try {
					int amount = Integer.parseInt(rest);
					try {
						Thread.sleep(amount);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid number " + rest);
				}
			}
			if (line.startsWith("press ")) {
				String key = line.replace("press ", "").toUpperCase();
				key = "VK_" + key;
				try {
					Field field = KeyEvent.class.getField(key);
					int keyCode = field.getInt(null);
					robot.keyPress(keyCode);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					System.out.println("No such key");
				}
			}
			if (line.startsWith("unpress ")) {
				String key = line.replace("unpress ", "").toUpperCase();
				key = "VK_" + key;
				try {
					Field field = KeyEvent.class.getField(key);
					int keyCode = field.getInt(null);
					robot.keyRelease(keyCode);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					System.out.println("No such key");
				}
			}
			if (line.startsWith("x ")) {
				String rest = line.replace("x ", "");
				try {
					int amount = Integer.parseInt(rest);
					robot.mouseMove(amount, (int) MouseInfo.getPointerInfo().getLocation().getY());
				} catch (NumberFormatException e) {
					System.out.println("Invalid number " + rest);
				}
			}
			if (line.startsWith("y ")) {
				String rest = line.replace("y ", "");
				try {
					int amount = Integer.parseInt(rest);
					robot.mouseMove((int) MouseInfo.getPointerInfo().getLocation().getX(), amount);
				} catch (NumberFormatException e) {
					System.out.println("Invalid number " + rest);
				}
			}
			if (line.startsWith("x+ ")) {
				String rest = line.replace("x+ ", "");
				try {
					int amount = Integer.parseInt(rest);
					robot.mouseMove(amount + (int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY());
				} catch (NumberFormatException e) {
					System.out.println("Invalid number " + rest);
				}
			}
			if (line.startsWith("y+ ")) {
				String rest = line.replace("y+ ", "");
				try {
					int amount = Integer.parseInt(rest);
					robot.mouseMove((int) MouseInfo.getPointerInfo().getLocation().getX(), amount + (int) MouseInfo.getPointerInfo().getLocation().getY());
				} catch (NumberFormatException e) {
					System.out.println("Invalid number " + rest);
				}
			}
			if (line.startsWith("print ")) {
				String rest = line.replace("print ", "");
				System.out.println(rest);
			}
			if (line.equals("repeat")) {
				if (script.isStopped()) {
					script.end();
					if (script.getIndicator() != null) {
						script.getIndicator().hideIndicator();
						script.setIndicator(null);
					}
					return;
				} else {
					i = -1;
					continue;
				}
			}
			if (line.startsWith("indicator ")) {
				String rest = line.replace("indicator ", "");
				if (script.getIndicator() == null) {
					script.setIndicator(new Indicator(rest));
					script.getIndicator().showIndicator();
				}
			}
		}
		script.end();
		script.join();
		script.reset();
		if (script.getIndicator() != null) {
			script.getIndicator().hideIndicator();
			script.setIndicator(null);
		}
	}
	
}
