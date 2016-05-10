package levels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoxRemover implements MouseListener {

	private LevelBuilder lb;

	public BoxRemover(LevelBuilder lb) {
		this.lb = lb;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		lb.removeBox(e.getX(), e.getY());
		lb.removeMouseListenerFromLevel(this);
		lb.repaintLevel(null, null);
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