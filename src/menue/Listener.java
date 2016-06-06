package menue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import levels.LevelBuilder;
import main.Game;
import settings.SettingsDialog;
import sound.DefaultSounds;
import sound.SoundPlayer;
import toolbox.TimeManager;

public class Listener implements MouseListener, ActionListener {

	private Menue m;
	private Game g;
	private LevelBuilder lvl;
	private SettingsDialog options;

	public Listener(Menue m) {
		this.lvl = new LevelBuilder();
		this.options = new SettingsDialog();
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof BombenButton) {
			((BombenButton) e.getSource()).toggleBomb();
			DefaultSounds.CLICKSOUND.play();
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
			Game.createNewGame();
			m.stopTimer();
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