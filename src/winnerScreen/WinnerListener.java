package winnerScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Game;

/**
 * 
 * JTextFieldListener
 *
 */
public class WinnerListener implements ActionListener {
	private WinnerScreen panel;

	/**
	 * 
	 * @param p
	 */
	public WinnerListener(WinnerScreen p) {
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