package ingameMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Game;
import settings.SettingsDialog;
import sound.SoundPlayer;

public class MenueListener implements ActionListener {

	private IngameMenue menue;
	private SettingsDialog settings;
	private Game game;
	private SoundPlayer buttonHover, mainmusic;

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

		this.buttonHover = new SoundPlayer("buttonHover");
		this.mainmusic = new SoundPlayer("mainmusic");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton)
			buttonHover.play();

		switch (e.getActionCommand()) {
		case "play":
			settings.setVisible(false);
			settings.setEnabled(false);
			game.setEnabled(true);
			game.setVisible(true);
			game.requestFocusInWindow();
			menue.setVisible(false);
			menue.setEnabled(false);
			mainmusic.stop();
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
