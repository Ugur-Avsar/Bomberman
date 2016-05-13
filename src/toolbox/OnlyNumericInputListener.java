package toolbox;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

public class OnlyNumericInputListener extends DocumentFilter {

	private JTextComponent component;

	private int min, max, lengthCap;

	public OnlyNumericInputListener(JTextComponent component, int min, int max, int lengthCap) {
		this.component = component;
		this.min = min;
		this.max = max;
		this.lengthCap = lengthCap;
	}

	private JTextComponent getComponent() {
		return component;
	}

	private void setComponent(JTextComponent component) {
		this.component = component;
	}

	private int getMin() {
		return min;
	}

	private void setMin(int min) {
		this.min = min;
	}

	private int getMax() {
		return max;
	}

	private void setMax(int max) {
		this.max = max;
	}

	private int getLengthCap() {
		return lengthCap;
	}

	private void setLengthCap(int lengthCap) {
		this.lengthCap = lengthCap;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		if (StringUtils.isInt(string)) {
			int val = Integer.parseInt(string);
			if (string.length() + offset < lengthCap && val >= min && val <= max) {
				super.insertString(fb, offset, string, attr);
				component.setBackground(Color.WHITE);
			} else
				Toolkit.getDefaultToolkit().beep();
		} else {
			if (string.length() + offset < lengthCap) {
				component.setBackground(new Color(255, 200, 200));
			}
			Toolkit.getDefaultToolkit().beep();
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		if (StringUtils.isInt(text)) {
			int val = Integer.parseInt(text);
			if (text.length() + offset < lengthCap && val >= min && val <= max) {
				super.insertString(fb, offset, text, attrs);
				component.setBackground(Color.WHITE);
			} else
				Toolkit.getDefaultToolkit().beep();
		} else {
			if (text.length() + offset < lengthCap) {
				component.setBackground(new Color(255, 200, 200));
			}
			Toolkit.getDefaultToolkit().beep();
		}
	}
}