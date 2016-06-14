package combat;

import java.util.ArrayList;
import java.util.List;

import sound.SoundPlayer;

/**
 * Manager für alle Bomben die im Spiel vorhanden sind.
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public final class BombMaster {
	private static List<Bomb> livingBombs = new ArrayList<Bomb>();

	public static void update() {
		for (int i = 0; i < livingBombs.size(); i++)
			livingBombs.get(i).update();
	}

	/**
	 * Fügt eine Bombe an der Position des Spielers 'p' hinzu und nimmt diesen
	 * als 'source:Player' Parameter im Bomben-Konstuktor.
	 * 
	 * @param p
	 */
	public static boolean addBomb(Player p) {
		return livingBombs
				.add(new Bomb(p.getX() + p.getWidth() / 2, p.getY() + p.getHeight() / 2, 75, 100, 200, "bomb", p));
	}

	/**
	 * 
	 * Löscht die Bombe und dekrementiert die Anzahl gesetzter Bomben des
	 * Bomben-Setzers.
	 * 
	 * @param b
	 */
	public static boolean destroyBomb(Bomb b) {
		if (b.getSource() != null)
			b.getSource().decBombsSet();
		return livingBombs.remove(b);
	}

	/**
	 * Leert die Liste.
	 */
	public static void reset() {
		livingBombs.clear();
	}

	/**
	 * 
	 * @return einen Klon der Liste der "lebenden" Bomben mit einer
	 *         unterschiedlichen Referenz.
	 */
	public static List<Bomb> getBombs() {
		return new ArrayList<>(livingBombs);
	}
}