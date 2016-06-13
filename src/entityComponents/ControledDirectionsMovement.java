package entityComponents;

import entities.DynamicEntity;
import graphics.MovingSpriteConfiguration;
import graphics.Spritesheet;
import inputManagement.Keyboard;
import toolbox.Ticker;

public class ControledDirectionsMovement extends EntityComponent {

	private int[] keys;

	private Ticker frameCounter;
	private int changeTime;

	// Key-Positions
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;

	// Spritesheet Indexes
	private int leftIndex;
	private int rightIndex;
	private int upIndex;
	private int downIndex;

	private int spritesPerAction;

	/**
	 * @param ((DynamicEntity)
	 *            parent)
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public ControledDirectionsMovement(DynamicEntity parent, int left, int right, int up, int down,
			MovingSpriteConfiguration config) {
		super(parent);
		frameCounter = new Ticker(true, 1);
		changeTime = config.getChangeTime();
		keys = new int[4];
		keys[LEFT] = left;
		keys[RIGHT] = right;
		keys[UP] = up;
		keys[DOWN] = down;
		leftIndex = config.getLeftIndex();
		rightIndex = config.getRightIndex();
		upIndex = config.getUpIndex();
		downIndex = config.getDownIndex();
		spritesPerAction = config.getSpritesPerAction();
		changeTime = config.getChangeTime();
	}

	@Override
	public void update() {
		boolean up = Keyboard.isKeyDown(keys[UP]);
		boolean down = Keyboard.isKeyDown(keys[DOWN]);
		boolean left = Keyboard.isKeyDown(keys[LEFT]);
		boolean right = Keyboard.isKeyDown(keys[RIGHT]);

		Spritesheet s = (Spritesheet) parent.getTexture();

		int selectedSprite = s.getSeletedSprite();

		// Moving X
		if (left) {
			if (((DynamicEntity) parent).getCurrentXSpeed() < 0) {
				if (frameCounter.getI() % changeTime == 0) {
					if (selectedSprite < leftIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(leftIndex);
				}
			} else
				s.selectSprite(leftIndex);

			((DynamicEntity) parent).goLeft();
		} else if (right) {
			if (((DynamicEntity) parent).getCurrentXSpeed() > 0) {
				if (frameCounter.getI() % changeTime == 0) {
					if (selectedSprite < rightIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(rightIndex);
				}
			} else
				s.selectSprite(rightIndex);

			((DynamicEntity) parent).goRight();
		}

		if (up) {
			if (((DynamicEntity) parent).getCurrentYSpeed() < 0) {
				if (frameCounter.getI() % changeTime == 0) {
					if (selectedSprite < upIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(upIndex);
				}
			} else
				s.selectSprite(upIndex);

			((DynamicEntity) parent).goUp();
		} else if (down) {
			if (((DynamicEntity) parent).getCurrentYSpeed() > 0) {
				if (frameCounter.getI() % changeTime == 0) {
					if (selectedSprite < downIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(downIndex);
				}
			} else
				s.selectSprite(downIndex);

			((DynamicEntity) parent).goDown();
		}

		// If player hasn't moved...
		if (!up && !down && !right && !left) {
			((DynamicEntity) parent).stay();
			frameCounter.reset();

			switch (((DynamicEntity) parent).getDirection()) {
			case 0:
				s.selectSprite(upIndex);
				break;
			case 1:
				s.selectSprite(rightIndex);
				break;
			case 3:
				s.selectSprite(downIndex);
				break;
			case 4:
				s.selectSprite(leftIndex);
				break;
			}
		} else if (!left && !right) {
			((DynamicEntity) parent).stayX();
		} else if (!up && !down) {
			((DynamicEntity) parent).stayY();
		}

		frameCounter.incI();
	}
}