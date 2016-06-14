package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidSpritesheetSizeException;
import main.Game;

public class Spritesheet implements Renderable {

	private int rowCount;
	private int colCount;

	private int rowHeight;
	private int colWidth;

	private List<BufferedImage> sprites;

	private int selectedTexture;
	private boolean responsive;

	public Spritesheet(String filename, int rowCount, int colCount, int initialSprite) {
		sprites = new ArrayList<BufferedImage>();
		this.selectedTexture = initialSprite;
		setResponsive(true);
		this.rowCount = rowCount;
		this.colCount = colCount;

		// geht die Schleife durch und setzt die einzelnen Sprites
		try {
			loadSpritesheet(filename);
		} catch (InvalidSpritesheetSizeException e) {
			e.printStackTrace();
		}
	}

	public void selectSprite(int selectTexture) {
		this.selectedTexture = selectTexture;
	}

	public void loadSpritesheet(String filename) throws InvalidSpritesheetSizeException {
		sprites.clear();
		Texture sheet = new Texture(filename);

		this.rowHeight = sheet.getHeight() / rowCount;
		this.colWidth = sheet.getWidth() / colCount;

		for (int y = 0; y < rowCount; y++) {
			for (int x = 0; x < colCount; x++) {
				sprites.add(sheet.grabSubTexture(x * colWidth, y * rowHeight, colWidth, rowHeight));
			}
		}
	}

	public void incSpriteIndex() {
		if (selectedTexture < sprites.size() - 1)
			this.selectedTexture++;
	}

	@Override
	public void render(Graphics2D g, int x, int y, int breite, int hoehe) {
		if (responsive)
			g.drawImage(sprites.get(selectedTexture), (int) (x * Game.SCREEN_SCALING_FACTOR),
					(int) (y * Game.SCREEN_SCALING_FACTOR), (int) (breite * Game.SCREEN_SCALING_FACTOR),
					(int) (hoehe * Game.SCREEN_SCALING_FACTOR), null);
		else
			g.drawImage(sprites.get(selectedTexture), x, y, breite, hoehe, null);
	}

	public int getSeletedSprite() {
		return selectedTexture;
	}

	@Override
	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}

	public int getSpriteCount() {
		return sprites.size();
	}
}