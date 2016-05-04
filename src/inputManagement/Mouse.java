package inputManagement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseWheelListener, MouseListener, MouseMotionListener {
	private static double x, y;
	private static boolean[] buttonsPressed;

	// 0: Left Button .... 1: Middle Button .... 2: Right Button

	public Mouse() {
		super();
		x = 0;
		y = 0;
		buttonsPressed = new boolean[3];
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			buttonsPressed[0] = true;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			buttonsPressed[1] = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			buttonsPressed[2] = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			buttonsPressed[0] = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			buttonsPressed[1] = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			buttonsPressed[2] = false;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public static boolean isButtonDown(int buttonID) {
		return buttonsPressed[buttonID];
	}

	/**
	 * @return the x
	 */
	public static double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public static double getY() {
		return y;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}
}