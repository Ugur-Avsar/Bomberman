package settings;

public class Settings {
	private static boolean animations = true;
	private static boolean fpsLimitOn = true;
	private static boolean antialiasing = true;
	private static boolean fullscreen = true;

	private static int fpsLimit = 60;
	private static int windowWidth = 1280, windowHeight = 720;

	/**
	 * @return the animations
	 */
	private static boolean isAnimations() {
		return animations;
	}

	/**
	 * @return the fpsLimitOn
	 */
	private static boolean isFpsLimitOn() {
		return fpsLimitOn;
	}

	/**
	 * @return the antialiasing
	 */
	private static boolean isAntialiasing() {
		return antialiasing;
	}

	/**
	 * @return the fullscreen
	 */
	private static boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * @return the fpsLimit
	 */
	private static int getFpsLimit() {
		return fpsLimit;
	}

	/**
	 * @return the windowWidth
	 */
	private static int getWindowWidth() {
		return windowWidth;
	}

	/**
	 * @return the windowHeight
	 */
	private static int getWindowHeight() {
		return windowHeight;
	}

	/**
	 * @param animations
	 *            the animations to set
	 */
	private static void setAnimations(boolean animations) {
		Settings.animations = animations;
	}

	/**
	 * @param fpsLimitOn
	 *            the fpsLimitOn to set
	 */
	private static void setFpsLimitOn(boolean fpsLimitOn) {
		Settings.fpsLimitOn = fpsLimitOn;
	}

	/**
	 * @param antialiasing
	 *            the antialiasing to set
	 */
	private static void setAntialiasing(boolean antialiasing) {
		Settings.antialiasing = antialiasing;
	}

	/**
	 * @param fullscreen
	 *            the fullscreen to set
	 */
	private static void setFullscreen(boolean fullscreen) {
		Settings.fullscreen = fullscreen;
	}

	/**
	 * @param fpsLimit
	 *            the fpsLimit to set
	 */
	private static void setFpsLimit(int fpsLimit) {
		Settings.fpsLimit = fpsLimit;
	}

	/**
	 * @param windowWidth
	 *            the windowWidth to set
	 */
	private static void setWindowWidth(int windowWidth) {
		Settings.windowWidth = windowWidth;
	}

	/**
	 * @param windowHeight
	 *            the windowHeight to set
	 */
	private static void setWindowHeight(int windowHeight) {
		Settings.windowHeight = windowHeight;
	}
}