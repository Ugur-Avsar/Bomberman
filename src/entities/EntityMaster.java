package entities;

import java.util.ArrayList;
import java.util.List;

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

	public static List<Entity> getEntities() {
		return new ArrayList<Entity>(entities);
	}

	public static void reset() {
		entities.clear();
	}
}