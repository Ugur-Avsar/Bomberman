package levels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoxRemover implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		LevelBuilder.removeBox(e.getX(), e.getY());
		LevelBuilder.removeMouseListenerFromLevel(this);
		LevelBuilder.repaintLevel(null, null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}