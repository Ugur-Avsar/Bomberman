package ugurMenueZumTesten;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sound.SoundPlayer;
import sun.net.www.content.text.plain;

public class MainMenue extends JPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Bomberman HD - Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.setLocationRelativeTo(null);

		SoundPlayer p1 = new SoundPlayer("mainmusic");
		SoundPlayer p2 = new SoundPlayer("mainmusic");
		SoundPlayer p3 = new SoundPlayer("mainmusic");

		Button b = new Button("play");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (p1.isPlaying()) {
					p1.stop();
				} else
					p1.play();
			}
		});

		frame.add(b);
		frame.setVisible(true);
	}

}
