package combat;

import java.awt.event.KeyEvent;

import entities.DynamicEntity;
import entityComponents.BombPlacerComponent;
import entityComponents.ControledDirectionsMovement;
import entityComponents.DamageableComponent;
import graphics.MovingSpriteConfiguration;
import inputManagement.Keyboard;
import main.Game;
import sound.SoundPlayer;

public class Player extends DynamicEntity {
	private static int nextPlayerID;
	private int playerID;

	private static final int maxHP = 500;
	private static final int BOMB_CAP = 2;
	private int bombsSet = 0;
	private int HP;
	private SoundPlayer bombSound;

	private DamageableComponent dmgComponent;
	private BombPlacerComponent bombComponent;

	public Player(Game parent, int width, int height, double rotation, String spriteSheet, double speedX, double speedY,
			MovingSpriteConfiguration config, int goLeft, int goRight, int goUp, int goDown, int bombKey) {
		super(parent, 0, 0, width, height, rotation, spriteSheet, speedX, speedY, config.getRowCount(),
				config.getColCount(), 1);
		playerID = nextPlayerID;
		dmgComponent = new DamageableComponent(this, 10, 10);
		bombComponent = new BombPlacerComponent(this, bombKey);
		nextPlayerID++;
		addEntityComponent(new ControledDirectionsMovement(this, goLeft, goRight, goUp, goDown, config));
		addEntityComponent(dmgComponent);
		addEntityComponent(bombComponent);
	}

	@Override
	public void update() {
		super.update();
		if (Keyboard.isKeyDown(KeyEvent.VK_E) && bombsSet < BOMB_CAP) {
			BombMaster.addBomb(this);
			bombsSet++;
		}

		/*
		 * if(!bombSound.isPlaying()) { bombSound.play(); }
		 */

	}

	public void damage() {
		dmgComponent.damage();
	}

	public void decBombsSet() {
		bombComponent.decBombsSet();
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	public int getHP() {
		return dmgComponent.getHp();
	}
}