package winnerScreen;

import javax.swing.*;

import combat.Player;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Locale;

/**
 * StartFrame
 * 
 *
 */
public class SimpleFrame extends JFrame {

	/**
	 * constructor
	 * 
	 * @throws UnsupportedLookAndFeelException
	 */
	public SimpleFrame(Player p) throws UnsupportedLookAndFeelException {
		super.setIconImage(new ImageIcon("./res/winnerIcon.png").getImage());
		setTitle("THE WINNER TAKES IT ALL!");
		setSize(800, 350);

		SimplePanel panel = new SimplePanel(this, p);
		add(panel);

		this.setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}