package entityComponents;

import entities.Entity;

public abstract class EntityComponent {
	protected Entity parent;

	public EntityComponent(Entity parent) {
		this.parent = parent;
	}

	public abstract void update();
}