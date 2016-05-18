package entities;

import java.util.ArrayList;
import java.util.List;

import entityComponents.EntityComponent;
import graphics.Renderable;

public abstract class Entity {
	protected List<EntityComponent> components;

	protected Renderable texture;

	protected double x, y;
	protected double width, height;
	protected double rotation;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param rotation
	 */
	public Entity(double x, double y, int width, int height, double rotation, Renderable texture) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setRotation(rotation);
		setTexture(texture);
		components = new ArrayList<EntityComponent>();
	}

	public void addEntityComponent(EntityComponent e) {
		if (e != null)
			components.add(e);
	}

	public void removeEntityComponent(EntityComponent e) {
		components.remove(e);
	}

	public void removeEntityComponent(int index) {
		components.remove(index);
	}

	public void update() {
		for (EntityComponent e : components)
			e.update();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the texture
	 */
	public Renderable getTexture() {
		return texture;
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Renderable texture) {
		this.texture = texture;
	}
}