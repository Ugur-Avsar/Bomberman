package combat;

import entities.Entity;
import graphics.Spritesheet;

public class Bomb extends Entity {

	private int frameCounter = 1;
	private static final int LIFE_TIME = 300; // In Frames
	private static final int SPRITE_COLS = 13;
	private static final int CHANGE_FRQUENCY = LIFE_TIME / SPRITE_COLS;

	private double explosionRadius;

	public Bomb(double x, double y, int width, int height, double explosionRadius, String texture) {
		super(x, y, width, height, 0, new Spritesheet(texture, 1, SPRITE_COLS, 0));
		this.setExplosionRadius(explosionRadius);
	}

	public void update() {
		super.update();

		if (frameCounter >= LIFE_TIME) {
			BombMaster.destroyBomb(this);
			return;
		}

		frameCounter++;
		if (frameCounter % CHANGE_FRQUENCY == 0) {
			((Spritesheet) texture).incSpriteIndex();
		}
	}

	public double getExlosionRadius() {
		return explosionRadius;
	}

	private void setExplosionRadius(double exlosionRadius) {
		this.explosionRadius = exlosionRadius;
	}
}