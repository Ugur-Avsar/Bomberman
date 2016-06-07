package inputManagement;

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