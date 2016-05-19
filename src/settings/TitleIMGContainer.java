package settings;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import graphics.Texture;

public class TitleIMGContainer extends JPanel {
	private Texture img;

	public TitleIMGContainer() {
		img = new Texture("titleIMG");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		img.render((Graphics2D) g, 0, 0, getWidth(), getHeight());
		System.out.println(getWidth());
		System.out.println(getHeight());
		System.out.println();
	}
}
