package rendering;

import java.awt.Graphics2D;

import main.Game;

public interface Renderable {
	public void render(Graphics2D g, int x, int y, int w, int h);
}