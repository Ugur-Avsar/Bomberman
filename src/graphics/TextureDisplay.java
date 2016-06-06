package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TextureDisplay extends JPanel {
	private Renderable renderable;
	private int xOffset, yOffset;

	/**
	 * @param renderable
	 * @param xDelay
	 * @param yDelay
	 */
	public TextureDisplay(Renderable renderable, int xOffset, int yOffset) {
		super();
		this.renderable = renderable;
		this.renderable.setResponsive(false);
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		renderable.render((Graphics2D) g, xOffset, yOffset, super.getWidth(), super.getHeight());
	}
}