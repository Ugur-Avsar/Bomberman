package combat;

import entities.DynamicEntity;
import entityComponents.ControledDirectionsMovement;
import main.Game;

public class Player extends DynamicEntity {

	public Player(Game parent, int x, int y, int width, int height, double rotation, String spriteSheet, double speedX,
			double speedY, int rowCount, int colCount, int goLeft, int goRight, int goUp, int goDown) {

		super(parent, x, y, width, height, rotation, spriteSheet, speedX, speedY, rowCount, colCount, 1);
		addEntityComponent(new ControledDirectionsMovement(this, 15, goLeft, goRight, goUp, goDown));
	}
}