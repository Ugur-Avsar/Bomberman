package inputManagement;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_G;
import static java.awt.event.KeyEvent.VK_H;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_NUMPAD4;
import static java.awt.event.KeyEvent.VK_NUMPAD5;
import static java.awt.event.KeyEvent.VK_NUMPAD6;
import static java.awt.event.KeyEvent.VK_NUMPAD8;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_TAB;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_Z;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import toolbox.TimeManager;

public class Keyboard implements KeyListener {

	private static Map<Integer, Key> keys;

	public Keyboard() {
		keys = new HashMap<Integer, Key>();
		initKeys();
	}

	private void initKeys() {
		System.out.println("-----------------------------------------------------");
		System.err.println(TimeManager.getCurrentTime() + "... Loading Keyboard-Values");
		Field[] fields = KeyEvent.class.getFields();
		List<Integer> keysList = new ArrayList<Integer>();
		for (Field field : fields) {
			if (field.getName().startsWith("VK_")) {
				try {
					keysList.add(field.getInt(null));
					System.out.print(field.getName() + " ");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		for (int id : keysList) {
			keys.put(id, new Key(false, false));
		}
		System.out.println();
		System.out.println("-----------------------------------------------------");
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