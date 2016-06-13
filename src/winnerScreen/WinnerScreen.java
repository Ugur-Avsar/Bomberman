package winnerScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import combat.Player;
import graphics.Spritesheet;
import graphics.TextureDisplay;
import main.Game;

/**
 * JTextFieldPanel
 * 
 *
 */
class WinnerScreen extends JPanel {

	private WinnerFrame parentFrame;

	private JButton reset;
	private JButton close;

	private JTextField winner_TF;
	private WinnerListener listener;
	private JLabel winnerLabel;

	/**
	 * constructor
	 */
	public WinnerScreen(Game g, WinnerFrame simpleFrame, Player winner) {
		super(new GridLayout(1, 3, 10, 10));
		super.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.parentFrame = simpleFrame;
		listener = new WinnerListener(g, this);

		// ****************************************************************
		Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 26);
		reset = new JButton("Play Again!");

		close = new JButton("Exit");

		Font fontWinner = new Font("Arial", Font.BOLD, 45);
		winnerLabel = new JLabel("UNSER GEWINNER:");
		winnerLabel.setFont(font);

		winner_TF = new JTextField(winner.getName());
		winner_TF.setFont(fontWinner);
		winner_TF.setHorizontalAlignment(SwingUtilities.CENTER);
		winner_TF.setEnabled(false);
		winner_TF.setDisabledTextColor(Color.BLACK);

		Spritesheet pokalSprites1 = new Spritesheet("pokal", 1, 4, 0);
		TextureDisplay pokal1 = new TextureDisplay(pokalSprites1, 0, 0);

		Spritesheet pokalSprites2 = new Spritesheet("pokal", 1, 4, 0);
		TextureDisplay pokal2 = new TextureDisplay(pokalSprites2, 0, 1);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					Random r = new Random();
					pokalSprites1.selectSprite(r.nextInt(pokalSprites1.getSpriteCount()));
					pokalSprites2.selectSprite(r.nextInt(pokalSprites2.getSpriteCount()));

					pokal1.repaint();
					pokal2.repaint();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		add(pokal1);

		JPanel buttons = new JPanel(new GridLayout(1, 2));
		buttons.add(reset);
		buttons.add(close);

		JPanel mitte = new JPanel(new BorderLayout());
		mitte.add(winnerLabel, BorderLayout.NORTH);
		mitte.add(winner_TF, BorderLayout.CENTER);
		mitte.add(buttons, BorderLayout.SOUTH);
		add(mitte);
		add(pokal2);

		addActionListeners();
	}

	/**
	 * add actionListeners
	 */
	private void addActionListeners() {
		reset.addActionListener(listener);
		close.addActionListener(listener);
	}

	/**
	 * @return the reset_BTN
	 */
	public JButton getReset() {
		return reset;
	}

	/**
	 * @param reset_BTN
	 *            the reset_BTN to set
	 */
	public void setreset(JButton reset_BTN) {
		this.reset = reset_BTN;
	}

	/**
	 * @return the close_BTN
	 */
	public JButton getClose_BTN() {
		return close;
	}

	/**
	 * @param close_BTN
	 *            the close_BTN to set
	 */
	public void setclose(JButton close_BTN) {
		this.close = close_BTN;
	}

	/**
	 * @return the name_TF
	 */
	public JTextField getwinner_TF() {
		return winner_TF;
	}

	/**
	 * @param name_TF
	 *            the name_TF to set
	 */
	public void setwinner_TF(JTextField winner_TF) {
		this.winner_TF = winner_TF;
	}

	/**
	 * @return the town_TF
	 */

	/**
	 * @return the simpleFrame
	 */
	public WinnerFrame getSimpleFrame() {
		return parentFrame;
	}

}
