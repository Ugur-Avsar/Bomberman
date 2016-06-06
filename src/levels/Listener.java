package levels;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import graphics.Texture;
import toolbox.TimeManager;

public class Listener implements ActionListener {

	private LevelBuilder lvlBuilder;

	public Listener(LevelBuilder lvlBuilder) {
		this.lvlBuilder = lvlBuilder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "editBackground":
			File[] levelFiles = LevelBuilder.LEVEL_BACKGROUND_FOLDER.listFiles();
			String[] levels = new String[levelFiles.length];

			for (int i = 0; i < levelFiles.length; i++) {
				levels[i] = levelFiles[i].getName();
			}

			String input = (String) JOptionPane.showInputDialog(lvlBuilder, "Select Level-Background-Image:",
					"Level-Background Selection", JOptionPane.PLAIN_MESSAGE, null, levels, levels[0]);

			if (input != null)
				lvlBuilder.setBG(new Texture("levels/" + input.substring(0, input.length() - 4)));
			break;

		case "addCollisionBox":
			try {
				lvlBuilder.addMouseListenerToLevel(new BoxAdder(lvlBuilder,
						Integer.parseInt(JOptionPane.showInputDialog("Polygon-Point-Count:"))));
			} catch (NumberFormatException | HeadlessException e1) {
				System.err.println(TimeManager.getCurrentTime() + "... Invalid Polygon-Point count entered!");
			}
			break;

		case "exportLevel":
			lvlBuilder.exportLevel();
			break;

		case "openLevel":
			lvlBuilder.loadLevel(
					JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(lvlBuilder), "Level-Name:"));
			lvlBuilder.repaintLevel(null, null);
			break;

		case "delete":
			lvlBuilder.addMouseListenerToLevel(new BoxRemover(lvlBuilder));
			break;

		case "setPlayerSpawnPoint":
			lvlBuilder.addMouseListenerToLevel(new SpawnAdder(lvlBuilder));
			break;
		}
	}
}