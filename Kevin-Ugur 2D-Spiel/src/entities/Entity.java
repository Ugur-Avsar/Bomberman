package entities;

public abstract class Entity {
	protected int x, y;
	protected double rotation;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param rotation
	 */
	public Entity(int x, int y, int width, int height, double rotation) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}

	public Entity() {
		this.x = 0;
		this.y = 0;
		this.rotation = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
}