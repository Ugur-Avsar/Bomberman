package entities;

import main.Game;
import resources.Spritesheet;

public class DynamicEntity extends Entity {

	private double maxXSpeed;
	private double maxYSpeed;

	private double currentXSpeed;
	private double currentYSpeed;

	private int direction;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param rotation
	 * @param texture
	 */
	public DynamicEntity(int x, int y, int width, int height, double rotation, String spriteSheet, double speedX,
			double speedY, int rowCount, int colCount) {

		super(x, y, width, height, rotation, new Spritesheet(spriteSheet, rowCount, colCount));

		this.direction = 0;
		this.setMaxXSpeed(speedX);
		this.setMaxYSpeed(speedY);
	}

	@Override
	public void update() {
		super.update();
		if (Game.getLevel().walkable(x + currentXSpeed, y + currentYSpeed, width, height)) {
			moveX(currentXSpeed);
			moveY(currentYSpeed);
		}
	}

	public void moveX(double dX) {
		x += dX;
	}

	public void moveY(double dY) {
		y += dY;
	}

	public void rotate(double dA) {
		rotation += dA;
	}

	/**
	 * @return the currentXSpeed
	 */
	public double getCurrentXSpeed() {
		return currentXSpeed;
	}

	/**
	 * @param currentXSpeed
	 *            the currentXSpeed to set
	 */
	public void setCurrentXSpeed(double currentXSpeed) {
		this.currentXSpeed = currentXSpeed;
	}

	/**
	 * @return the currentYSpeed
	 */
	public double getCurrentYSpeed() {
		return currentYSpeed;
	}

	/**
	 * @param currentYSpeed
	 *            the currentYSpeed to set
	 */
	public void setCurrentYSpeed(double currentYSpeed) {
		this.currentYSpeed = currentYSpeed;
	}

	/**
	 * @return the maxXSpeed
	 */
	public double getMaxXSpeed() {
		return maxXSpeed;
	}

	/**
	 * @param speedX
	 *            the maxXSpeed to set
	 */
	public void setMaxXSpeed(double speedX) {
		this.maxXSpeed = speedX;
	}

	/**
	 * @return the maxYSpeed
	 */
	public double getMaxYSpeed() {
		return maxYSpeed;
	}

	/**
	 * @param maxYSpeed
	 *            the maxYSpeed to set
	 */
	public void setMaxYSpeed(double maxYSpeed) {
		this.maxYSpeed = maxYSpeed;
	}

	public void goUp() {
		currentYSpeed = -maxYSpeed;
		direction = 0;
	}

	public void goDown() {
		currentYSpeed = maxYSpeed;
		direction = 2;
	}

	public void goLeft() {
		currentXSpeed = -maxXSpeed;
		direction = 3;
	}

	public void goRight() {
		currentXSpeed = maxXSpeed;
		direction = 1;
	}

	public void stay() {
		currentXSpeed = 0;
		currentYSpeed = 0;
	}

	public void stayX() {
		currentXSpeed = 0;
	}

	public void stayY() {
		currentYSpeed = 0;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}