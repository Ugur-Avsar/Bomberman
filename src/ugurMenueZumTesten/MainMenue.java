package ugurMenueZumTesten;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenue extends JPanel {

	public MainMenue() {
		super(new GridLayout(4, 1, 10, 10));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			JPanel panel = new JPanel();
			panel.setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
			add(panel);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Bomberman HD - Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MainMenue());
		frame.setSize(300, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
