package entityComponents;

import entities.DynamicEntity;
import graphics.MovingSpriteConfiguration;
import graphics.Spritesheet;
import inputManagement.Keyboard;

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
	private int leftIndex;
	private int rightIndex;
	private int upIndex;
	private int downIndex;
	private MovingSpriteConfiguration config;

	private int spritesPerAction;

	/**
	 * @param target
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public ControledDirectionsMovement(DynamicEntity target, int left, int right, int up, int down,
			MovingSpriteConfiguration config) {
		frameCounter = 0;
		this.changeTime = config.getChangeTime();
		this.target = target;
		keys = new int[4];
		keys[LEFT] = left;
		keys[RIGHT] = right;
		keys[UP] = up;
		keys[DOWN] = down;
		left = config.getLeftIndex();
		right = config.getRightIndex();
		up = config.getUpIndex();
		down = config.getDownIndex();
		this.config = config;
	}

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

		if (config != null) {
			leftIndex = config.getLeftIndex();
			rightIndex = config.getRightIndex();
			upIndex = config.getUpIndex();
			downIndex = config.getDownIndex();
			spritesPerAction = config.getSpritesPerAction();
			changeTime = config.getChangeTime();
		}

		Spritesheet s = (Spritesheet) target.getTexture();

		int selectedSprite = s.getSeletedSprite();

		// Moving X
		if (left) {
			if (target.getCurrentXSpeed() < 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < leftIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(leftIndex);
				}
			} else
				s.selectSprite(leftIndex);

			target.goLeft();
		} else if (right) {
			if (target.getCurrentXSpeed() > 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < rightIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(rightIndex);
				}
			} else
				s.selectSprite(rightIndex);

			target.goRight();
		}

		if (up) {
			if (target.getCurrentYSpeed() < 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < upIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(upIndex);
				}
			} else
				s.selectSprite(upIndex);

			target.goUp();
		} else if (down) {
			if (target.getCurrentYSpeed() > 0) {
				if (frameCounter % changeTime == 0) {
					if (selectedSprite < downIndex + spritesPerAction - 1) {
						s.incSpriteIndex();
					} else
						s.selectSprite(downIndex);
				}
			} else
				s.selectSprite(downIndex);

			target.goDown();
		}

		// If player hasn't moved...
		if (!up && !down && !right && !left) {
			target.stay();
			frameCounter = 0;

			switch (target.getDirection()) {
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
			target.stayX();
		} else if (!up && !down) {
			target.stayY();
		}

		System.out.println(target.getDirection());
		frameCounter++;
	}
}