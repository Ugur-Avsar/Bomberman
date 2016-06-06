package combat;

import java.util.ArrayList;
import java.util.List;

import entities.EntityMaster;

public class PlayerMaster {
	private static List<Player> players = new ArrayList<Player>();

	public static boolean addPlayer(Player p) {
		return players.add(p);
	}

	public static void update() {

		for (Player player : players) {
			player.update();
		}
	}

	public static boolean someoneAlive() {
		if (players.size() > 0)
			return true;
		return false;
	}

	public static boolean winnerFound() {
		if (players.size() <= 1) {
			return true;
		}
		return false;
	}

	public static boolean removePlayer(Player p) {
		EntityMaster.removeEntity(p);
		return players.remove(p);
	}

	public static void reset() {
		players.clear();
	}

	public static List<Player> getPlayers() {
		return new ArrayList<Player>(players);
	}
}
