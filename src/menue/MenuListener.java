package menue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import levelBuilding.Level;
import levelBuilding.LevelBuilder;
import main.Game;
import settings.SettingsDialog;
import sound.SoundPlayer;
import toolbox.TimeManager;

public class MenuListener implements MouseListener, ActionListener {

	private Menue m;
	private Game g;
	private LevelBuilder lvl;
	private SettingsDialog options;
	private SoundPlayer hoverSound;

	public MenuListener(Menue m) {
		this.lvl = new LevelBuilder();
		this.options = new SettingsDialog();
		this.m = m;
		this.hoverSound = new SoundPlayer("buttonHover");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof BombenButton) {
			((BombenButton) e.getSource()).toggleBomb();
			hoverSound.play();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof BombenButton) {
			((BombenButton) e.getSource()).toggleBomb();
		}
	}

	// --------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "start":
			File[] levelFiles = LevelBuilder.LEVEL_EXPORT_FOLDER.listFiles();
			String[] levels = new String[levelFiles.length];
			for (int i = 0; i < levelFiles.length; i++)
				levels[i] = levelFiles[i].getName();

			String level = (String) JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(m), "Select Level",
					"Level - Selection", JOptionPane.PLAIN_MESSAGE, null, levels, "Select");
			if (level != null) {
				m.stopTimer();
				if (g != null) {
					g.setEnabled(false);
					g.setVisible(false);
				}
				g = Game.createNewGame(new Level(new File(LevelBuilder.LEVEL_EXPORT_FOLDER + "/" + level)));
			}
			break;
		case "levelEditor":
			lvl.createNewLevelBuilderFrame();
			break;
		case "options":
			options.setVisible(true);
			break;
		case "exit":
			System.out.println(TimeManager.getCurrentTime() + "... Programm wurde beendet.");
			System.exit(0);
		}
	}
}