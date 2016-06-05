package combat;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import graphics.Spritesheet;
import sound.SoundPlayer;
import toolbox.Ticker;
import toolbox.TimeManager;

public class Bomb extends Entity {

	private Ticker ticker;
	public static final int LIFE_TIME = 255; // In Frames
	private static final int DEAD_LINE = 70; // Gibt an ab wieviel Prozent der
												// Animation, Spieler im Radius
												// Schaden bekommen
	private static final int SPRITE_COLS = 13;
	private static final int SPRITE_ROWS = 1;
	private static final int CHANGE_FRQUENCY = LIFE_TIME / (SPRITE_COLS * SPRITE_ROWS);
	private double explosionRadius;
	private SoundPlayer bombSound;
	private Player source;

	public Bomb(double x, double y, int width, int height, double explosionRadius, String texture, Player player) {
		super(x, y, width, height, 0, new Spritesheet(texture, SPRITE_ROWS, SPRITE_COLS, 0));
		this.setExplosionRadius(explosionRadius);
		this.source = player;
		ticker = new Ticker(false, 1);
		bombSound = new SoundPlayer("bombSound");
	}

	@Override
	public void update() {
		super.update();
		if (!ticker.isTicking()) {
			ticker.start();
		}

		ticker.incI();

		if (ticker.getI() >= LIFE_TIME) {
			BombMaster.destroyBomb(this);
			return;
		}

		if (ticker.getI() >= (LIFE_TIME / 100) * DEAD_LINE) {
			bombSound.play();
			for (Player p : PlayerMaster.getPlayers()) {
				Ellipse2D kreis = new Ellipse2D.Double(x - explosionRadius - width / 2,
						y - explosionRadius - height / 2, explosionRadius * 2, explosionRadius * 2);
				if (kreis.intersects(p.getX(), p.getY(), p.getWidth(), p.getHeight())) {
					p.damage();
				}
			}
		}

		if (ticker.getI() % (CHANGE_FRQUENCY + 1) == 0) {
			((Spritesheet) texture).incSpriteIndex();
		}
	}

	public double getExlosionRadius() {
		return explosionRadius;
	}

	/**
	 * @return the frameCounter
	 */
	public int getFrameCounter() {
		return ticker.getI();
	}

	private void setExplosionRadius(double exlosionRadius) {
		this.explosionRadius = exlosionRadius;
	}

	public Player getSource() {
		return source;
	}
}