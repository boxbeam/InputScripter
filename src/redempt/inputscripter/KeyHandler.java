package redempt.inputscripter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;

public class KeyHandler {
	
	private static List<Integer> pressed = new ArrayList<>();
	private static List<Runnable> actions = new CopyOnWriteArrayList<>();
	
	public static void init() throws NativeHookException {
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(new NativeKeyAdapter() {
			
			@Override
			public void nativeKeyPressed(NativeKeyEvent e) {
				if (!pressed.contains(e.getKeyCode())) {
					pressed.add(e.getKeyCode());
				}
				for (Runnable runnable : actions) {
					runnable.run();
				}
			}

			@Override
			public void nativeKeyReleased(NativeKeyEvent e) {
				pressed.remove((Integer) e.getKeyCode());
				for (Runnable runnable : actions) {
					runnable.run();
				}
			}
			
		});
	}
	
	public static List<Integer> getPressed() {
		return pressed;
	}
	
	public static void addAction(Runnable runnable) {
		actions.add(runnable);
	}
	
	public static void removeAction(Runnable runnable) {
		actions.remove(runnable);
	}
	
}
