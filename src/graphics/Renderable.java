package graphics;

import java.awt.Graphics2D;

public interface Renderable {
	public void render(Graphics2D g, int x, int y, int w, int h);

	public void setResponsive(boolean responsive);
}