package rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import entities.Entity;
import entities.StaticEntity;
import levels.Level;
import main.Game;

public class Renderer {
	static int i = 0;

	public static void renderEntity(Level level, List<Entity> entities, Graphics2D g) {

		level.getTexture().render(g, 0, 0, 1920, 1080);

		int x = 0, y = 0, width = 0, height = 0;

		for (Entity entity : entities) {
			Graphics2D g1 = (Graphics2D) g.create();

			x = (int) (entity.getX());
			y = (int) (entity.getY());
			width = (int) (entity.getWidth());
			height = (int) (entity.getHeight());

			g1.rotate(Math.toRadians(entity.getRotation()),
					(int) (x * Game.SCREEN_SCALING_FACTOR) + (int) (width * Game.SCREEN_SCALING_FACTOR) / 2,
					(int) (y * Game.SCREEN_SCALING_FACTOR) + (int) (height * Game.SCREEN_SCALING_FACTOR) / 2);
			g1.setColor(Color.BLACK);
			// g1.fillRect((int) (x * Game.SCREEN_SCALING_FACTOR), (int) (y *
			// Game.SCREEN_SCALING_FACTOR),
			// (int) (width * Game.SCREEN_SCALING_FACTOR), (int) (height *
			// Game.SCREEN_SCALING_FACTOR));

			entity.getTexture().render(g1, x, y, width, height);
		}

		for (StaticEntity entity : level.getEntities()) {
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

		for (Rectangle box : Game.getLevel().getCollisionBoxes()) {
			Graphics2D g1 = (Graphics2D) g.create();
			g1.fill(box);
		}
	}
}