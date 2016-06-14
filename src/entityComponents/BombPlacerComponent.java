package entityComponents;

import combat.BombMaster;
import combat.Player;
import entities.Entity;
import inputManagement.Keyboard;

/**
 * Erlaubt es der Einheit Bomben mithilfe einer zugewiesenen Taste
 * (KeyEvent.Keycode) zu setzen.
 * 
 * Wird standartmäßig von der Player-Klasse verwendet.
 * 
 * @author AvsarUgur, KulcsarKevin
 *
 */
public class BombPlacerComponent extends EntityComponent {

	private static final int CAP = 1;
	/**
	 * Einschränkung wie viele Bomben gleichzeitig gesetzt werden können.
	 */

	private int bombKey;
	/**
	 * Taste mit der die Bombe gesetzt wird.
	 */

	private int bombsSet;

	/**
	 * Zwischenvariable für die Anzahl der bereits gesetzten und lebenden
	 * Bomben.
	 */

	/**
	 * 
	 * @param parent
	 * @param bombKey
	 */
	public BombPlacerComponent(Entity parent, int bombKey) {
		super(parent);
		this.bombKey = bombKey; // KeyEvent - Keycode
	}

	/**
	 * Setzt Bombe falls die zugewiesene Taste gedrückt wurde.
	 */
	@Override
	public void update() {
		if (Keyboard.isKeyDown(bombKey) && bombsSet < CAP) {
			Player par = ((Player) parent);
			BombMaster.addBomb(par);
			bombsSet++;
		}
	}

	public void decBombsSet() {
		if (bombsSet > 0)
			bombsSet--;
	}
}