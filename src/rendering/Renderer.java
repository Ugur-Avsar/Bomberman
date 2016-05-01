package rendering;

import java.awt.Graphics2D;
import java.awt.Polygon;

import entities.Entity;
import levels.Level;
import main.Game;

public class Renderer {
	public static void renderEntity(Level level, Graphics2D g) {

		level.getTexture().render(g, 0, 0, 1920, 1080);

		int x = 0, y = 0, width = 0, height = 0;

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

		for (Polygon box : Game.getLevel().getCollisionBoxes()) {
			Graphics2D g1 = (Graphics2D) g.create();
			g1.fillPolygon(box);
		}
	}
}