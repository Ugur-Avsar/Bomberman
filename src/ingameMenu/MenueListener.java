package ingameMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Game;
import settingsMenue.SettingsDialog;
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
			settings.setVisible(false);
			settings.setEnabled(false);
			game.setEnabled(true);
			game.requestFocusInWindow();
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
