package ingameMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Game;
import sound.SoundPlayer;

public class MenueListener implements ActionListener {

	private IngameMenue menue;
	private Game game;
	private SoundPlayer player;

	public MenueListener(Game game, IngameMenue menue) {
		this.game = game;
		this.menue = menue;
		this.player = new SoundPlayer("buttonHover");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (player.isPlaying())
			player.stop();
		player.restart();
		switch (e.getActionCommand()) {
		case "play":
			menue.setVisible(false);
			menue.setEnabled(false);
			game.setEnabled(true);
			game.requestFocusInWindow();
			break;
		case "openLevelBuilder":
			
			break;
		case "exit":
			System.exit(0);
			break;
		}
	}
}
