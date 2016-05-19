package ingameMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public class OpenOnEscapeListener implements KeyListener {

	private Game parent;
	private IngameMenue child;
	private boolean menueOpened;

	public OpenOnEscapeListener(Game parent, IngameMenue child) {
		this.parent = parent;
		this.child = child;
		parent.setEnabled(true);
		child.setLocation(1920 / 2 - child.getWidth() / 2, 1080 / 2 - child.getHeight() / 2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !child.isVisible()) {
			parent.setEnabled(true);
			child.setEnabled(true);
			child.setVisible(true);
			child.requestFocusInWindow();
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
			parent.requestFocusInWindow();
			menueOpened = false;
			System.out.println(menueOpened);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}