package redempt.inputscripter.script;

import redempt.inputscripter.gui.indicator.Indicator;
import redempt.inputscripter.utils.Keybind;

public class Script {
	
	private boolean stop = false;
	private boolean running = false;
	private boolean ended = false;
	public boolean hold = false;
	private String lines;
	private Indicator indicator = null;
	public Keybind keybind = null;
	
	public Script(String lines) {
		this.lines = lines;
	}
	
	public void run() {
		running = true;
		if (!stop) {
			ScriptHandler.run(this);
		}
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void join() {
		while (!ended) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	
	public Indicator getIndicator() {
		return indicator;
	}
	
	public void kill() {
		stop();
		join();
		reset();
	}
	
	public void end() {
		ended = true;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void reset() {
		stop = false;
		running = false;
		ended = false;
		hold = false;
	}
	
	public boolean isStopped() {
		return stop;
	}
	
	public String getText() {
		return lines;
	}
	
	public void stop() {
		stop = true;
	}
	
}
