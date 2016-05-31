package combat;

import java.awt.event.KeyEvent;

import entities.DynamicEntity;
import entityComponents.ControledDirectionsMovement;
import graphics.MovingSpriteConfiguration;
import inputManagement.Keyboard;
import main.Game;
import sound.SoundPlayer;

public class Player extends DynamicEntity {

	private static final int maxHP = 3;
	private static final int BOMB_CAP = 2;
	private int bombsSet = 0;
	private int hp;
	private SoundPlayer bombSound;

	public Player(Game parent, int width, int height, double rotation, String spriteSheet, double speedX, double speedY,
			MovingSpriteConfiguration config, int goLeft, int goRight, int goUp, int goDown) {

		super(parent, 0, 0, width, height, rotation, spriteSheet, speedX, speedY, config.getRowCount(),
				config.getColCount(), 1);
		addEntityComponent(new ControledDirectionsMovement(this, goLeft, goRight, goUp, goDown, config));
		bombSound = new SoundPlayer("bombSound");
	}

	@Override
	public void update() {
		super.update();
		if (Keyboard.isKeyDown(KeyEvent.VK_E) && bombsSet < BOMB_CAP) {
			BombMaster.addBomb(this);
			bombsSet++;

			if (!bombSound.isPlaying()) {
				bombSound.play();
			}

		}
	}

	public void decBombsSet() {
		if (bombsSet > 0) {
			bombsSet--;
		}
	}

}