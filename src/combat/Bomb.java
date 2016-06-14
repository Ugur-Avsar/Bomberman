package combat;

import java.awt.geom.Ellipse2D;

import entities.Entity;
import graphics.Spritesheet;
import sound.SoundPlayer;
import toolbox.Ticker;

public class Bomb extends Entity {
	private Ticker ticker;
	public static final int LIFE_TIME = 195; // In Frames
	private static final int DEAD_LINE = 80; // Gibt an ab wieviel Prozent der
												// Animation, Spieler im Radius
												// Schaden bekommen
	private static final int SPRITE_COLS = 13;
	private static final int SPRITE_ROWS = 1;
	private static final int CHANGE_FRQUENCY = LIFE_TIME / (SPRITE_COLS * SPRITE_ROWS);
	private double explosionRadius;
	private Player source;

	private SoundPlayer bombSound;

	/**
	 * 
	 * Erstellt eine neue Bombe mit den gegebenen Daten.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param explosionRadius
	 * @param texture
	 * @param player
	 */
	public Bomb(double x, double y, int width, int height, double explosionRadius, String texture, Player player) {
		super(x, y, width, height, 0, new Spritesheet(texture, SPRITE_ROWS, SPRITE_COLS, 0));
		this.setExplosionRadius(explosionRadius);
		this.source = player;
		ticker = new Ticker(true, 0);
		bombSound = new SoundPlayer("bombSound");
	}

	/**
	 * Ändert den Spritesheet-Index um eine Animation zu erzeugen. Explodiert ab
	 * der gegebenen DEAD_LINE. Entfernt sich aus der Liste der Bomben wenn
	 * LIFE_TIME erreicht wurde.
	 */
	@Override
	public void update() {
		super.update();

		if (ticker.getI() % CHANGE_FRQUENCY == 0) {
			((Spritesheet) texture).incSpriteIndex();
		}

		if (ticker.getI() >= LIFE_TIME - DEAD_LINE) {
			if (!bombSound.isPlaying())
				bombSound.play();

			for (Player p : PlayerMaster.getPlayers()) {
				Ellipse2D kreis = new Ellipse2D.Double(x - explosionRadius, y - explosionRadius, explosionRadius * 2,
						explosionRadius * 2);
				if (kreis.intersects(p.getX() - p.getWidth() / 2, p.getY() - p.getHeight() / 2, p.getWidth(),
						p.getHeight())) {
					p.damage();
				}
			}
		}

		if (ticker.getI() >= LIFE_TIME) {
			BombMaster.destroyBomb(this);
			return;
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