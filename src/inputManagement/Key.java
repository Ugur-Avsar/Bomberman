package inputManagement;

public class Key {
	private boolean pressed;

	public Key(boolean pressed) {
		this.setPressed(pressed);
	}

	/**
	 * @return the pressed
	 */
	public boolean isPressed() {
		return pressed;
	}

	/**
	 * @param pressed
	 *            the pressed to set
	 */
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
}
