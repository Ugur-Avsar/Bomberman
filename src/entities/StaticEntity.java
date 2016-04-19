package entities;

import resources.Texture;

public class StaticEntity extends Entity {

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param rotation
	 * @param texture
	 */
	public StaticEntity(double x, double y, int width, int height, double rotation, String texture) {
		super(x, y, width, height, rotation, new Texture(texture));
		this.setTexture(new Texture(texture));
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}