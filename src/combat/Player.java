package combat;

import entities.DynamicEntity;
import entityComponents.BombPlacerComponent;
import entityComponents.ControledDirectionsMovement;
import entityComponents.DamageableComponent;
import graphics.MovingSpriteConfiguration;
import main.Game;
import sound.SoundPlayer;

public class Player extends DynamicEntity {
	private String name;
	private static int nextPlayerID;
	private int playerID;

	private int bombsSet = 0;
	private SoundPlayer bombSound;

	private DamageableComponent dmgComponent;
	private BombPlacerComponent bombComponent;

	public Player(Game parent, String name, int width, int height, double rotation, String spriteSheet, double speedX,
			double speedY, MovingSpriteConfiguration config, int goLeft, int goRight, int goUp, int goDown,
			int bombKey) {
		super(parent, 0, 0, width, height, rotation, spriteSheet, speedX, speedY, config.getRowCount(),
				config.getColCount(), 1);
		playerID = nextPlayerID;
		this.name = name;
		dmgComponent = new DamageableComponent(this, 1, 1);
		bombComponent = new BombPlacerComponent(this, bombKey);
		nextPlayerID++;
		addEntityComponent(new ControledDirectionsMovement(this, goLeft, goRight, goUp, goDown, config));
		addEntityComponent(dmgComponent);
		addEntityComponent(bombComponent);
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

	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", bombsSet=" + bombsSet + ", "
				+ (bombSound != null ? "bombSound=" + bombSound + ", " : "")
				+ (dmgComponent != null ? "dmgComponent=" + dmgComponent + ", " : "")
				+ (bombComponent != null ? "bombComponent=" + bombComponent + ", " : "")
				+ (texture != null ? "texture=" + texture + ", " : "") + "x=" + x + ", y=" + y + ", width=" + width
				+ ", height=" + height + ", rotation=" + rotation + "]";
	}

	public String getName() {
		return name;
	}
}