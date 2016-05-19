package combat;

import java.util.ArrayList;
import java.util.List;

public final class BombMaster {
	private static List<Bomb> livingBombs = new ArrayList<Bomb>();

	public static void update() {
		for (Bomb bomb : livingBombs) {
			bomb.update();
		}
	}

	public static boolean addBomb(Player p) {
		return livingBombs
				.add(new Bomb(p.getX() + p.getWidth() / 2, p.getY() + p.getHeight() / 2, 75, 100, 500, "bomb", p));
	}

	public static boolean destroyBomb(Bomb b) {
		b.getSource().decBombsSet();
		return livingBombs.remove(b);
	}

	public static List<Bomb> getBombs() {
		return livingBombs;
	}
}