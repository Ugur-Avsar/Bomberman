package inputManagement;

import static java.awt.event.KeyEvent.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import main.Game;

public class Keyboard implements KeyListener {

	private static Map<Integer, Key> keys;
	public static final int[] keyIDs = new int[] { VK_ESCAPE, VK_SPACE, VK_ENTER, VK_TAB, VK_SHIFT, VK_CONTROL, VK_ALT,
			VK_RIGHT, VK_LEFT, VK_UP, VK_DOWN, VK_W, VK_A, VK_S, VK_D, VK_E, VK_Q };

	public Keyboard() {
		keys = new HashMap<Integer, Key>();
		initKeys();
	}

	private void initKeys() {
		for (int id : keyIDs) {
			keys.put(id, new Key(false));
		}
	}

	/**
	 * 
	 * Checks if the given key is pressed.
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isKeyDown(int key) {
		Key value = keys.get(key);
		if (value != null)
			return keys.get(key).isPressed();
		else {
			System.err.println("Error: Key not defined!");
			return false;
		}
	}

	/**
	 * 
	 * Checks if every key of the given IDs is pressed.
	 * 
	 * @param IDs
	 * @return
	 */
	public static boolean areKeysDown(int[] IDs) {
		boolean everyonePressed = true;
		for (int key : IDs) {
			if (!isKeyDown(key))
				everyonePressed = false;
		}
		return everyonePressed;
	}

	/**
	 * 
	 * Checks if any key of the given IDs is pressed.
	 * 
	 * @param IDs
	 * @return
	 */
	public static boolean isSomethingDown(int[] IDs) {
		for (int key : IDs) {
			if (isKeyDown(key))
				return true;
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Set<Entry<Integer, Key>> valueSet = keys.entrySet();
		for (Entry<Integer, Key> entry : valueSet) {
			if (e.getKeyCode() == entry.getKey()) {
				entry.getValue().setPressed(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Set<Entry<Integer, Key>> valueSet = keys.entrySet();
		for (Entry<Integer, Key> entry : valueSet) {
			if (e.getKeyCode() == entry.getKey()) {
				entry.getValue().setPressed(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}