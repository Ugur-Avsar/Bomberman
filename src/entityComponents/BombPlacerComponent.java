package entityComponents;

import combat.BombMaster;
import combat.Player;
import entities.Entity;
import inputManagement.Keyboard;
import sound.SoundPlayer;

public class BombPlacerComponent extends EntityComponent {

	private static final int CAP = 1;
	private int bombKey;
	private int bombsSet;

	public BombPlacerComponent(Entity parent, int bombKey) {
		super(parent);
		this.bombKey = bombKey; // KeyEvent - Keycode
	}

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
