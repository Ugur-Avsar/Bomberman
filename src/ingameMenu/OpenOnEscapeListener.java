package ingameMenu;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import exceptions.InvalidHierachyException;
import main.Game;

public class OpenOnEscapeListener implements KeyListener {

	private Game parent;
	private IngameMenue child;
	private boolean menueOpened;

	public OpenOnEscapeListener(Game parent, IngameMenue child) {
		this.parent = parent;
		this.child = child;
		child.setVisible(false);
		child.setEnabled(false);
		child.setSize(500, 500);
		child.setLocation(1920 / 2 - child.getWidth() / 2, 1080 / 2 - child.getHeight() / 2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !child.isVisible()) {
			parent.setEnabled(false);
			child.setEnabled(true);
			child.setVisible(true);
			menueOpened = true;
			System.out.println(menueOpened);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			parent.setEnabled(true);
			child.setEnabled(false);
			child.setVisible(false);
			menueOpened = false;
			System.out.println(menueOpened);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}