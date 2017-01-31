package redempt.inputscripter.gui.indicator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Indicator extends JDialog {
	
	private static List<Indicator> indicators = new ArrayList<>();
	
	public Indicator(String text) {
		super();
		this.setUndecorated(true);
		JLabel label = new JLabel(text);
		int width = label.getFontMetrics(label.getFont()).stringWidth(text);
		this.setSize(0, 0);
		label.setSize(width + 30, 30);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBackground(Color.GREEN);
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		label.setBorder(new LineBorder(Color.BLACK));
		this.setSize(width + 30, 30);
		label.setSize(500, 500);
		label.setVisible(true);
		this.add(label);
		this.setLocation(0, indicators.size() * 30);
		this.setAlwaysOnTop(true);
		this.setFocusable(false);
		indicators.add(this);
	}
	
	public void showIndicator() {
		this.setVisible(true);
		repaint();
	}
	
	public void hideIndicator() {
		this.dispose();
		indicators.remove(this);
		refresh();
	}
	
	public static void refresh() {
		int y = 0;
		for (Indicator indicator : indicators) {
			indicator.setLocation(0, y * 30);
			y++;
		}
	}
	
}
