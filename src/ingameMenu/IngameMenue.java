package ingameMenu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import graphics.Texture;
import main.Game;
import settingsMenue.SettingsDialog;

public class IngameMenue extends JPanel {

	private Texture background;

	private JButton play_BTN;
	private JButton settings_BTN;
	private JButton exit_BTN;

	private MenueListener listener;

	public IngameMenue(Game game, SettingsDialog settings) {
		super(new CardLayout());
		listener = new MenueListener(game, this, settings);
		background = new Texture("menueBG");
		setVisible(false);
		setEnabled(false);
		setSize(200, 300);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY));
		initButtons();
		initActionCommands();
		addActionListeners();
		JPanel gapPanel = new JPanel(new GridLayout(3, 1, 20, 20));
		gapPanel.setOpaque(false);
		gapPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		gapPanel.add(play_BTN);
		gapPanel.add(settings_BTN);
		gapPanel.add(exit_BTN);
		add(gapPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		background.render((Graphics2D) g, 0, 0, this.getWidth(), this.getHeight());
	}

	private void addActionListeners() {
		this.play_BTN.addActionListener(listener);
		this.settings_BTN.addActionListener(listener);
		this.exit_BTN.addActionListener(listener);
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
		
		this.play_BTN.setFont(new Font("Arial", Font.BOLD, 27));
		this.play_BTN.setFont(new Font("Arial", 0, 23));
		this.play_BTN.setFont(new Font("Arial", 0, 23));
	}
}