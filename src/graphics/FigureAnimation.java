package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JPanel;

import toolbox.Ticker;

public class FigureAnimation extends JPanel {
	private Ticker ticker;
	private Direction direction;
	private Spritesheet spritesheet;
	private MovingSpriteConfiguration config;
	private int x, y;

	public enum Direction {
		TOP, RIGHT, BOTTOM, LEFT;
	}

	/**
	 * @param direction
	 * @param spritesheet
	 * @param config
	 * @param x
	 * @param y
	 */
	public FigureAnimation(Direction direction, Spritesheet spritesheet, MovingSpriteConfiguration config, int x,
			int y) {
		super();
		ticker = new Ticker(true, 1);
		this.direction = direction;
		this.spritesheet = spritesheet;
		this.spritesheet.setResponsive(false);
		this.config = config;
		this.x = x;
		this.y = y;
		if (direction.equals(Direction.TOP)) {
			spritesheet.selectSprite(config.getUpIndex());
		} else if (direction.equals(Direction.RIGHT)) {
			spritesheet.selectSprite(config.getRightIndex());
		} else if (direction.equals(Direction.BOTTOM)) {
			spritesheet.selectSprite(config.getDownIndex());
		} else if (direction.equals(Direction.LEFT)) {
			spritesheet.selectSprite(config.getLeftIndex());
		}
	}

	public void update() {
		if (ticker.getI() % config.getChangeTime() == 0) {
			if (direction.equals(Direction.TOP)) {
				if (spritesheet.getSeletedSprite() < config.getUpIndex() + config.getSpritesPerAction() - 1) {
					spritesheet.incSpriteIndex();
				} else
					spritesheet.selectSprite(config.getUpIndex());
			} else if (direction.equals(Direction.RIGHT)) {
				if (spritesheet.getSeletedSprite() < config.getRightIndex() + config.getSpritesPerAction() - 1) {
					spritesheet.incSpriteIndex();
				} else
					spritesheet.selectSprite(config.getRightIndex());
			} else if (direction.equals(Direction.BOTTOM)) {
				if (spritesheet.getSeletedSprite() < config.getDownIndex() + config.getSpritesPerAction() - 1) {
					spritesheet.incSpriteIndex();
				} else
					spritesheet.selectSprite(config.getDownIndex());
			} else if (direction.equals(Direction.LEFT)) {
				if (spritesheet.getSeletedSprite() < config.getLeftIndex() + config.getSpritesPerAction() - 1) {
					spritesheet.incSpriteIndex();
				} else
					spritesheet.selectSprite(config.getLeftIndex());
			} else
				System.out.println("adfsadsa");
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		spritesheet.render((Graphics2D) g, x, y, getWidth(), getHeight());
	}
}