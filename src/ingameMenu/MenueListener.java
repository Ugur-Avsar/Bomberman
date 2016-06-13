package ingameMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Game;
import settings.SettingsDialog;
import sound.Sounds;
import sound.SoundPlayer;

public class MenueListener implements ActionListener {

	private IngameMenue menue;
	private SettingsDialog settings;
	private Game game;
	private SoundPlayer player;

	public MenueListener(Game game, IngameMenue menue, SettingsDialog settings) {
		this.game = game;
		this.menue = menue;
		this.settings = settings;
		
		game.setEnabled(true);
		game.setVisible(true);
		
		menue.setEnabled(false);
		menue.setVisible(false);
		
		settings.setEnabled(false);
		settings.setVisible(false);
		
		this.player = new SoundPlayer("buttonHover");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.play();

		switch (e.getActionCommand()) {
		case "play":
			settings.setVisible(false);
			settings.setEnabled(false);
			game.setEnabled(true);
			game.setVisible(true);
			game.requestFocusInWindow();
			menue.setVisible(false);
			menue.setEnabled(false);
			Sounds.MAIN_MUSIC.stop();
			break;
		case "openSettings":
			settings.getParentFrame().setVisible(true);
			settings.getParentFrame().setEnabled(true);
			settings.getParentFrame().requestFocus();
			menue.setEnabled(false);
			break;
		case "exit":
			System.exit(0);
			break;
		}
	}
}
