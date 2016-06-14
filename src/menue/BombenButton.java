package menue;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import graphics.Texture;

public class BombenButton extends JButton {
	private static final Texture BOMBE = new Texture("Bombe");
	private boolean bombVisible;

	public BombenButton(String text) {
		super(text);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (bombVisible)
			BOMBE.render((Graphics2D) g, 8, 8, getHeight()-16, getHeight()-16);
	}

	public void toggleBomb() {
		bombVisible = !bombVisible;
	}
}