package winnerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import levelBuilding.Level;
import levelBuilding.LevelBuilder;
import main.Game;

/**
 * 
 * JTextFieldListener
 *
 */
public class WinnerListener implements ActionListener {
	private Game game;
	private WinnerScreen panel;

	/**
	 * 
	 * @param p
	 */
	public WinnerListener(Game g, WinnerScreen p) {
		game = g;
		panel = p;
	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == panel.getReset()) {
			File[] levelFiles = LevelBuilder.LEVEL_EXPORT_FOLDER.listFiles();
			String[] levels = new String[levelFiles.length];
			for (int i = 0; i < levelFiles.length; i++)
				levels[i] = levelFiles[i].getName();

			String level = (String) JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(panel), "Select Level",
					"Level - Selection", JOptionPane.PLAIN_MESSAGE, null, levels, "Select");
			if (level != null) {
				game.stop();
				game = Game.createNewGame(new Level(new File(LevelBuilder.LEVEL_EXPORT_FOLDER + "/" + level)));
			}
		}
		if (source == panel.getClose_BTN()) {
			System.exit(0);
		}
	}
}