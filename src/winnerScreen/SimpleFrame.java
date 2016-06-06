package winnerScreen;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	public SimpleFrame() throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
		setTitle("Winning-Screen");
		setSize(800, 400);

		// *****************************************************************************
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("saved ...");
			}
		});

		fileMenu.add(exitItem);
		// anonymous actionlistener to close the frame
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.setDefaultLocale(Locale.ENGLISH);
				int exit = JOptionPane.showConfirmDialog(getParent(), "Exit the application?", "Exit ...",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.OK_OPTION)
					System.exit(1);
			}
		});
		// add menu to the frame
		setJMenuBar(menuBar);
		// *****************************************************************************

		// panel for gui components
		SimplePanel panel = new SimplePanel(this);

		// add JTextFieldPanel to the frame
		add(panel);

		// Groessenaenderung
		this.setResizable(false);

		// center the frame
		setLocationRelativeTo(this);

		// sichtbar machen
		setVisible(true);

	}

}
