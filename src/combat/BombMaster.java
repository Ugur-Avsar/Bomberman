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
		return livingBombs.add(new Bomb(p.getX(), p.getY(), 100, 100, 300, "bomb"));
	}

	public static boolean destroyBomb(Bomb b) {
		return livingBombs.remove(b);
	}
}