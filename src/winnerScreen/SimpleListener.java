package winnerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.*;

import main.Game;

/**
 * 
 * JTextFieldListener
 *
 */
public class SimpleListener implements ActionListener {
	private SimplePanel panel;

	/**
	 * 
	 * @param p
	 */
	public SimpleListener(SimplePanel p) {
		panel = p;

	}

	/**
	 * Button pressed, ....
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == panel.getReset()) {
			Game.createNewGame();
		}
		if (source == panel.getClose_BTN()) {
			System.exit(0);
		}
	}
}