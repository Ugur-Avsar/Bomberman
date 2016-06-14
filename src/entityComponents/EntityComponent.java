package entityComponents;

import entities.Entity;

/**
 * Abstrakte Klasse von der sich alle Entity-Komponeten ableiten.
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public abstract class EntityComponent {
	protected Entity parent;

	public EntityComponent(Entity parent) {
		this.parent = parent;
	}

	/**
	 * Wird bei jedem tick() (Game.tick()) aufgerufen.
	 */
	public abstract void update();
}