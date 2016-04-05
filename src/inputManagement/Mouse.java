package inputManagement;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import main.Game;

public class Mouse implements MouseWheelListener, MouseListener {
	private static double x, y;
	private static boolean[] buttons;

	// buttons = 0: Left Button .... 1: Middle Button .... 2: Right Button

	public Mouse() {
		x = 0;
		y = 0;
		buttons = new boolean[3];
	}

	public static void update() {
		try {
			Point mousePoint = Game.parentFrame.getMousePosition();
			x = mousePoint.getX();
			y = mousePoint.getY();
		} catch (NullPointerException e) {
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
			buttons[0] = true;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			buttons[1] = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			buttons[2] = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			buttons[0] = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			buttons[1] = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			buttons[2] = false;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public static boolean isButtonDown(int buttonID) {
		return buttons[buttonID];
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
}