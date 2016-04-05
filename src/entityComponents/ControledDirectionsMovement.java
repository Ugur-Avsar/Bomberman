package entityComponents;

import entities.DynamicEntity;
import inputManagement.Keyboard;
import resources.Spritesheet;

public class ControledDirectionsMovement implements EntityComponent {

	private DynamicEntity target;
	private int[] keys;

	private int frameCounter;
	private int changeTime;

	// Key-Positions
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;

	// Spritesheet Indexes
	private static final int LEFT_BEGIN_INDEX = 3;
	private static final int RIGHT_BEGIN_INDEX = 6;
	private static final int UP_BEGIN_INDEX = 9;
	private static final int DOWN_BEGIN_INDEX = 0;

	private static final int SPRITE_COUNT = 3 - 1;

	/**
	 * @param target
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public ControledDirectionsMovement(DynamicEntity target, int spriteChangeTime, int left, int right, int up,
			int down) {
		frameCounter = 0;
		this.changeTime = spriteChangeTime;
		this.target = target;
		keys = new int[4];
		keys[LEFT] = left;
		keys[RIGHT] = right;
		keys[UP] = up;
		keys[DOWN] = down;
	}

	@Override
	public void update() {
		boolean up = Keyboard.isKeyDown(keys[UP]);
		boolean down = Keyboard.isKeyDown(keys[DOWN]);
		boolean left = Keyboard.isKeyDown(keys[LEFT]);
		boolean right = Keyboard.isKeyDown(keys[RIGHT]);
		
		Spritesheet s = (Spritesheet) target.getTexture();

		int selectedSprite = s.getSeletedSprite();

		// Moving X
		if (left) {
			if (target.getCurrentXSpeed() < 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < LEFT_BEGIN_INDEX + SPRITE_COUNT) {
						s.incSpriteIndex();
					} else
						s.selectSprite(LEFT_BEGIN_INDEX);
				}
			} else
				s.selectSprite(LEFT_BEGIN_INDEX);

			target.goLeft();
		}

		if (right) {
			if (target.getCurrentXSpeed() > 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < RIGHT_BEGIN_INDEX + SPRITE_COUNT) {
						s.incSpriteIndex();
					} else
						s.selectSprite(RIGHT_BEGIN_INDEX);
				}
			} else
				s.selectSprite(RIGHT_BEGIN_INDEX);

			target.goRight();
		}

		if (up) {
			if (target.getCurrentYSpeed() < 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < UP_BEGIN_INDEX + SPRITE_COUNT) {
						s.incSpriteIndex();
					} else
						s.selectSprite(UP_BEGIN_INDEX);
				}
			} else
				s.selectSprite(UP_BEGIN_INDEX);

			target.goUp();
		}

		if (down) {
			if (target.getCurrentYSpeed() > 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < DOWN_BEGIN_INDEX + SPRITE_COUNT) {
						s.incSpriteIndex();
					} else
						s.selectSprite(DOWN_BEGIN_INDEX);
				}
			} else
				s.selectSprite(DOWN_BEGIN_INDEX);

			target.goDown();
		}

		// If player hasn't moved...
		if (!up && !down && !right && !left) {
			target.stay();
			frameCounter = 0;

			switch (target.getDirection()) {
			case 0:
				s.selectSprite(10);
				break;
			case 1:
				s.selectSprite(7);
				break;
			case 3:
				s.selectSprite(4);
				break;
			default:
				s.selectSprite(1);
			}
		} else if (!left && !right) {
			target.stayX();
		} else if (!up && !down) {
			target.stayY();
		}

		frameCounter++;
	}
}