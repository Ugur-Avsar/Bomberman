package entities;

import main.Game;

public class Player extends DynamicEntity {

	public Player(Game parent, int x, int y, int width, int height, double rotation, String spriteSheet, double speedX,
			double speedY, int rowCount, int colCount) {
		super(parent, x, y, width, height, rotation, spriteSheet, speedX, speedY, rowCount, colCount);
	}
}