package ingameMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import sound.Sounds;
import toolbox.TimeManager;

public class OpenOnEscapeListener implements KeyListener {

	private Game parent;
	private IngameMenue child;
	private boolean menueOpened;

	public OpenOnEscapeListener(Game parent, IngameMenue child) {
		this.parent = parent;
		this.child = child;
		child.setLocation(1920 / 2 - child.getWidth() / 2, 1080 / 2 - child.getHeight() / 2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !child.isVisible()) {
			child.setEnabled(true);
			child.setVisible(true);
			child.requestFocusInWindow();
			parent.setEnabled(false);
			menueOpened = true;
			Sounds.MAIN_MUSIC.play();
			System.out.println(TimeManager.getCurrentTime() + "... Menue opened!");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * @return the menueOpened
	 */
	public boolean isMenueOpened() {
		return menueOpened;
	}
}