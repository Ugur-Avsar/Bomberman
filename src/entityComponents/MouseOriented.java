package entityComponents;

import entities.Entity;
import inputManagement.Mouse;

public class MouseOriented implements EntityComponent {

	private Entity target;
	private double rotationOffset;

	public MouseOriented(Entity target, double rotationOffset) {
		this.target = target;
		this.rotationOffset = rotationOffset;
	}

	@Override
	public void update() {
		double angle = Math.atan2(Mouse.getY() - target.getY() - target.getHeight() / 2,
				Mouse.getX() - target.getX() - target.getWidth() / 2);
		target.setRotation(angle * (180.0 / Math.PI) + rotationOffset);
	}

}
