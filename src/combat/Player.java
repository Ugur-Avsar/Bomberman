package combat;

import java.awt.event.KeyEvent;

import entities.DynamicEntity;
import entityComponents.ControledDirectionsMovement;
import graphics.MovingSpriteConfiguration;
import inputManagement.Keyboard;
import main.Game;
import sound.SoundPlayer;

public class Player extends DynamicEntity {

	private static final  int maxHP = 500;
	private static final int BOMB_CAP = 2;
	private int bombsSet = 0;
	private int HP;
    private SoundPlayer bombSound;
    
	public Player(Game parent, int x, int y, int width, int height, double rotation, String spriteSheet, double speedX,
			double speedY, MovingSpriteConfiguration config, int goLeft, int goRight, int goUp, int goDown) {

		super(parent, x, y, width, height, rotation, spriteSheet, speedX, speedY, config.getRowCount(),
				config.getColCount(), 1);
		addEntityComponent(new ControledDirectionsMovement(this, goLeft, goRight, goUp, goDown,
				new MovingSpriteConfiguration(4, 4, 15, 4, 8, 12, 0, 4)));
		
		bombSound = new SoundPlayer("bombSound");
	}

	@Override
	public void update() {
		super.update();
		if (Keyboard.isKeyDown(KeyEvent.VK_E) && bombsSet < BOMB_CAP) {
			BombMaster.addBomb(this);
			bombsSet++;
			
			if(!bombSound.isPlaying())
			{
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