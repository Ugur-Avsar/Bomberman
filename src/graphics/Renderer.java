package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import combat.Bomb;
import combat.BombMaster;
import entities.Entity;
import entities.EntityMaster;
import levels.Level;
import main.Game;

public class Renderer {
	public static void render(Game game, Level level, Graphics2D g) {
		level.getTexture().render(g, 0, 0, 1920, 1080);

		int x = 0, y = 0, width = 0, height = 0;

		for (Bomb bomb : BombMaster.getBombs()) {
			Graphics2D g1 = (Graphics2D) g.create();

			g.setColor(new Color(0, 0, 0, 200));
			g1.fillOval((int) ((bomb.getX() - bomb.getExlosionRadius()) * Game.SCREEN_SCALING_FACTOR),
					(int) ((bomb.getY() - bomb.getExlosionRadius()) * Game.SCREEN_SCALING_FACTOR),
					(int) ((bomb.getExlosionRadius() * 2) * Game.SCREEN_SCALING_FACTOR),
					(int) ((bomb.getExlosionRadius() * 2) * Game.SCREEN_SCALING_FACTOR));

			g.setStroke(new BasicStroke(2));
			g1.drawOval((int) ((bomb.getX() - bomb.getExlosionRadius()) * Game.SCREEN_SCALING_FACTOR),
					(int) ((bomb.getY() - bomb.getExlosionRadius()) * Game.SCREEN_SCALING_FACTOR),
					(int) ((bomb.getExlosionRadius() * 2) * Game.SCREEN_SCALING_FACTOR),
					(int) ((bomb.getExlosionRadius() * 2) * Game.SCREEN_SCALING_FACTOR));

			bomb.getTexture().render(g, (int) (bomb.getX() - bomb.getWidth() / 2),
					(int) (bomb.getY() - bomb.getHeight() / 2), (int) bomb.getWidth(), (int) bomb.getHeight());
		}

		for (Entity entity : EntityMaster.getEntities()) {
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

		if (!game.getTopLevelFrame().isUndecorated()) {
			game.getTopLevelFrame().setSize(new Dimension(Game.DESKTOP_WIDTH, Game.DESKTOP_HEIGHT + 35));
		}
	}
}