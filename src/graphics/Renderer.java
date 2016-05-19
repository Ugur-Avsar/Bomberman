package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import combat.Bomb;
import entities.Entity;
import levels.Level;
import main.Game;

public class Renderer {
	public static void render(Level level, Graphics2D g) {
		level.getTexture().render(g, 0, 0, 1920, 1080);

		int x = 0, y = 0, width = 0, height = 0;

		for (Bomb bomb : level.getBombs()) {
			Graphics2D g1 = (Graphics2D) g.create();

			g1.setStroke(new BasicStroke(10));
			g1.setColor(new Color(255, 0, 0, (int) (0.5 * ((bomb.getFrameCounter() * (10 / 3)) / 4))));

			g1.drawOval((int) (bomb.getX() - bomb.getExlosionRadius() / 2),
					(int) (bomb.getY() - bomb.getExlosionRadius() / 2), (int) bomb.getExlosionRadius(),
					(int) bomb.getExlosionRadius());

			bomb.getTexture().render(g, (int) (bomb.getX() - bomb.getWidth() / 2),
					(int) (bomb.getY() - bomb.getHeight() / 2), (int) bomb.getWidth(), (int) bomb.getHeight());
		}

		for (Entity entity : level.getPlayers()) {
			Graphics2D g1 = (Graphics2D) g.create();

			x = (int) (entity.getX());
			y = (int) (entity.getY());
			width = (int) (entity.getWidth());
			height = (int) (entity.getHeight());

			g1.rotate(Math.toRadians(entity.getRotation()),
					(int) (x * Game.SCREEN_SCALING_FACTOR) + (int) (width * Game.SCREEN_SCALING_FACTOR) / 2,
					(int) (y * Game.SCREEN_SCALING_FACTOR) + (int) (height * Game.SCREEN_SCALING_FACTOR) / 2);

			entity.getTexture().render(g1, x, y, width, height);
		}
	}
}