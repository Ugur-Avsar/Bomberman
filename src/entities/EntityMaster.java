package entities;

import java.util.ArrayList;
import java.util.List;

import combat.Bomb;

public class EntityMaster {
	private static List<Entity> entities = new ArrayList<Entity>();

	public static void update() {
		for (Entity e : entities)
			e.update();
	}

	public static boolean addEntity(Entity e) {
		return entities.add(e);
	}

	public static boolean removeEntity(Entity e) {
		return entities.remove(e);
	}

	public static List<Entity> getEntity() {
		return new ArrayList<Entity>(entities);
	}
}
