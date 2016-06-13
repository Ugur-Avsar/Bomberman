package winnerScreen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import combat.Player;
import main.Game;

/**
 * StartFrame
 * 
 *
 */
public class WinnerFrame extends JFrame {

	/**
	 * constructor
	 * 
	 * @throws UnsupportedLookAndFeelException
	 */
	public WinnerFrame(Game g, Player p) throws UnsupportedLookAndFeelException {
		super.setIconImage(new ImageIcon("./lib/res/winnerIcon.png").getImage());
		setTitle("THE WINNER TAKES IT ALL!");
		setSize(800, 350);

		WinnerScreen panel = new WinnerScreen(g, this, p);
		add(panel);

		this.setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}