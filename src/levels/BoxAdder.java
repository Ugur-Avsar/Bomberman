package levels;

import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import toolbox.TimeManager;

public class BoxAdder implements MouseListener {
	private List<Integer> xCoords;
	private List<Integer> yCoords;
	private int countPointMax;
	private LevelBuilder lb;

	public BoxAdder(LevelBuilder lb, int maxPointCount) {
		this.lb = lb;
		xCoords = new ArrayList<Integer>();
		yCoords = new ArrayList<Integer>();
		countPointMax = 0;
		countPointMax = maxPointCount;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (xCoords.size() < countPointMax) {
			xCoords.add(e.getX());
			yCoords.add(e.getY());
			lb.repaintLevel(xCoords, yCoords);
		}

		if (xCoords.size() >= countPointMax) {
			int[] xpoints = new int[xCoords.size()];
			int[] ypoints = new int[yCoords.size()];

			for (int i = 0; i < xCoords.size(); i++) {
				xpoints[i] = xCoords.get(i);
				ypoints[i] = yCoords.get(i);
			}
			Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
			lb.addCollisionBox(p);
			lb.removeMouseListenerFromLevel(this);

			System.out.println("---------------------------------------------");
			System.out.println(TimeManager.getCurrentTime() + "... Collision box added to Level: ");
			System.out.println(xCoords + "\n" + yCoords);
			System.out.println("---------------------------------------------");
			lb.repaintLevel(null, null);
		}
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