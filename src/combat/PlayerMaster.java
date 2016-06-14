package combat;

import java.util.ArrayList;

import java.util.List;

import entities.EntityMaster;

/**
 * Manager für alle Spieler die im Spiel vorhanden sind.
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public class PlayerMaster {
	private static List<Player> players = new ArrayList<Player>();

	public static void update() {
		for (Player player : players) {
			player.update();
		}
	}

	/**
	 * Fügt den Spieler p hinzu.
	 * 
	 * @param p
	 */
	public static boolean addPlayer(Player p) {
		return players.add(p);
	}

	/**
	 * Entfernt den Spieler p.
	 * 
	 * @param p
	 */
	public static boolean removePlayer(Player p) {
		EntityMaster.removeEntity(p);
		return players.remove(p);
	}

	/**
	 * Leert die Liste.
	 */
	public static void reset() {
		players.clear();
	}

	/**
	 * 
	 * @return einen Klon der Liste der Spieler mit einer unterschiedlichen
	 *         Referenz.
	 */
	public static List<Player> getPlayers() {
		return new ArrayList<Player>(players);
	}

	/**
	 * @return ob der Gewinner feststeht.
	 */
	public static boolean winnerFound() {
		return players.size() < 2;
	}
}