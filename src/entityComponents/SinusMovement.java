package entityComponents;

import entities.DynamicEntity;

public class SinusMovement extends EntityComponent {
	private int angle;
	private double amplitudeX;
	private double amplitudeY;
	private double frequencyX;
	private double frequencyY;
	private double xoffsetX;
	private double yoffsetX;
	private double xoffsetY;
	private double yoffsetY;

	private boolean xCos;
	private boolean yCos;

	/**
	 * @param parent
	 * @param angle
	 * @param amplitudeX
	 * @param amplitudeY
	 * @param frequencyX
	 * @param frequencyY
	 * @param xoffsetX
	 * @param yoffsetX
	 * @param xoffsetY
	 * @param yoffsetY
	 */
	public SinusMovement(DynamicEntity parent, double amplitudeX, double amplitudeY, double frequencyX,
			double frequencyY, double xoffsetX, double yoffsetX, double xoffsetY, double yoffsetY) {
		super(parent);
		this.amplitudeX = amplitudeX;
		this.amplitudeY = amplitudeY;
		this.frequencyX = frequencyX;
		this.frequencyY = frequencyY;
		this.xoffsetX = xoffsetX;
		this.yoffsetX = yoffsetX;
		this.xoffsetY = xoffsetY;
		this.yoffsetY = yoffsetY;
	}

	public void update() {
		angle++;
		double sinX = Math.toRadians(angle * frequencyX + xoffsetX);
		double sinY = Math.toRadians(angle * frequencyY + yoffsetX);
		((DynamicEntity) parent).moveX(amplitudeX * (xCos ? Math.cos(sinX) : Math.sin(sinX) + xoffsetY));
		((DynamicEntity) parent).moveY(amplitudeY * (yCos ? Math.cos(sinY) : Math.sin(sinY) + yoffsetY));
	}

	/**
	 * @return the xCos
	 */
	public boolean isxCos() {
		return xCos;
	}

	/**
	 * @param xCos
	 *            the xCos to set
	 */
	public void setxCos(boolean xCos) {
		this.xCos = xCos;
	}

	/**
	 * @return the yCos
	 */
	public boolean isyCos() {
		return yCos;
	}

	/**
	 * @param yCos
	 *            the yCos to set
	 */
	public void setyCos(boolean yCos) {
		this.yCos = yCos;
	}
}