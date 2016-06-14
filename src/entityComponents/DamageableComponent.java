package entityComponents;

import java.util.Random;

import combat.Player;
import combat.PlayerMaster;
import entities.Entity;
import entities.EntityMaster;
import graphics.Texture;

/**
 * Erlaubt es der Einheit Schaden zu bekommen.
 * 
 * Wird standartm‰ﬂig von der Player-Klasse verwendet.
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public class DamageableComponent extends EntityComponent {

	private int maxHP, hp;

	public DamageableComponent(Entity parent, int maxHP, int initialHP) {
		super(parent);
		setMaxHP(maxHP);
		setHp(initialHP);
	}

	public void damage() {
		hp--;
		if (hp < 1) {
			if (parent instanceof Player)
				PlayerMaster.removePlayer((Player) parent);
			else
				EntityMaster.removeEntity(parent);

			// Grabstein mit zuf‰lliger Textur wird hinzugef¸gt.
			EntityMaster.addEntity(new Entity((int) parent.getX(), (int) parent.getY(), (int) parent.getWidth(),
					(int) parent.getHeight(), 0, new Texture("grave" + new Random().nextInt(4))));
		}
	}

	/**
	 * @return the maxHP
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * @param maxHP
	 *            the maxHP to set
	 */
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp
	 *            the hp to set
	 */
	public void setHp(int hp) {
		this.hp = Math.max(0, Math.min(hp, maxHP));
	}

	@Override
	public void update() {
	}
}
