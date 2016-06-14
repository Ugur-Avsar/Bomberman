package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager für alle Einheiten die im Spiel vorhanden sind (ausgenommen Bomben
 * und Spieler da diese ihre eigene Master-Klasse haben).
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public class EntityMaster {
	private static List<Entity> entities = new ArrayList<Entity>();

	public static void update() {
		for (Entity e : entities)
			e.update();
	}

	/**
	 * Fügt die Einheit e hinzu.
	 * 
	 * @param e
	 */
	public static boolean addEntity(Entity e) {
		return entities.add(e);
	}

	/**
	 * Entfernt die Einheit e.
	 * 
	 * @param e
	 */
	public static boolean removeEntity(Entity e) {
		return entities.remove(e);
	}

	/**
	 * 
	 * @return einen Klon der Liste der Einheiten mit einer unterschiedlichen
	 *         Referenz.
	 */
	public static List<Entity> getEntities() {
		return new ArrayList<Entity>(entities);
	}

	/**
	 * Leert die Liste.
	 */
	public static void reset() {
		entities.clear();
	}
}