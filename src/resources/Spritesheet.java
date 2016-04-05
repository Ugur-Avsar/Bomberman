package resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidSpritesheetSizeException;
import main.Game;
import rendering.Renderable;

public class Spritesheet implements Renderable {

	private int rowCount;
	private int colCount;

	private int rowHeight;
	private int colWidth;

	private String spritesheetFilename;
	private List<BufferedImage> sprites;

	private int selectedTexture;

	public Spritesheet(String filename, int rowCount, int colCount) {
		sprites = new ArrayList<BufferedImage>();
		this.selectedTexture = 1;

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
		spritesheetFilename = filename;

		if (sheet.getWidth() == sheet.getHeight()
				&& sheet.getWidth() / colCount - (int) sheet.getWidth() / colCount == 0
				&& sheet.getHeight() / rowCount - (int) sheet.getHeight() / rowCount == 0) {

			this.rowHeight = sheet.getHeight() / rowCount;
			this.colWidth = sheet.getWidth() / colCount;

			for (int y = 0; y < rowCount; y++) {
				for (int x = 0; x < colCount; x++) {
					sprites.add(sheet.grabSubTexture(x * colWidth, y * rowHeight, colWidth, rowHeight));
				}
			}

		} else
			throw new InvalidSpritesheetSizeException();
	}

	public void incSpriteIndex() {
		this.selectedTexture++;
	}

	public void render(Graphics2D g, int x, int y, int breite, int hoehe) {
		g.drawImage(sprites.get(selectedTexture), (int) (x * Game.SCREEN_SCALING_FACTOR),
				(int) (y * Game.SCREEN_SCALING_FACTOR), (int) (breite * Game.SCREEN_SCALING_FACTOR),
				(int) (hoehe * Game.SCREEN_SCALING_FACTOR), null);
	}

	public String toString() {
		return sprites.size() + " Sprites from ./res/" + spritesheetFilename + ".png";
	}

	public static void main(String[] args) {
		System.out.println(new Spritesheet("playerRed", 4, 3).toString());
	}

	public int getSeletedSprite() {
		return selectedTexture;
	}
}