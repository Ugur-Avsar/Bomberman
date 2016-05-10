package ingameMenu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import main.Game;

public class IngameMenue extends JPanel {

	private JButton play_BTN;
	private JButton settings_BTN;
	private JButton exit_BTN;

	private MenueListener listener;

	private Thread thread;

	public IngameMenue(Game game) {
		super(new GridLayout(3, 1, 30, 30));
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		listener = new MenueListener(game, this);
		initButtons();
		initActionCommands();
		addActionListeners();
		add(play_BTN);
		add(settings_BTN);
		add(exit_BTN);
	}

	private void addActionListeners() {
		this.play_BTN.addActionListener(listener);
		this.settings_BTN.addActionListener(listener);
		this.exit_BTN.addActionListener(listener);
		ButtonHoverSoundListener soundListener = new ButtonHoverSoundListener();
		this.play_BTN.addMouseListener(soundListener);
		this.settings_BTN.addMouseListener(soundListener);
		this.exit_BTN.addMouseListener(soundListener);
	}

	private void initActionCommands() {
		this.play_BTN.setActionCommand("play");
		this.settings_BTN.setActionCommand("openSettings");
		this.exit_BTN.setActionCommand("exit");
	}

	private void initButtons() {
		this.play_BTN = new JButton("Play");
		this.settings_BTN = new JButton("Settings");
		this.exit_BTN = new JButton("Exit");
	}
}