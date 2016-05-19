package inputManagement;

public class Key {
	private boolean pressed;
	private boolean typed;

	public Key(boolean pressed, boolean typed) {
		this.setPressed(pressed);
		this.setTyped(typed);
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

	/**
	 * @return the typed
	 */
	public boolean isTyped() {
		return typed;
	}

	/**
	 * @param typed
	 *            the typed to set
	 */
	public void setTyped(boolean typed) {
		this.typed = typed;
	}
}
