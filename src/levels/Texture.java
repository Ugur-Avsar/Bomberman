package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import graphics.Renderable;
import graphics.TextureManager;
import toolbox.TimeManager;

public class Texture implements Renderable {
	private static final Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
	private TextureManager manager;
	private String filename;

	/**
	 * 
	 * @param filename
	 */
	public Texture(String filename) {
		this.filename = filename;
		TextureManager oldTexture = texMap.get(filename);
		if (oldTexture != null) {
			manager = oldTexture;
			manager.addReference();
		} else {
			try {
				manager = new TextureManager(ImageIO.read(new File("./res/" + filename + ".png")));
				texMap.put(filename, manager);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(TimeManager.getCurrentTime() + "... " + "Texture (" + filename + ".png) loaded");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (manager.removeReference() && !filename.isEmpty())
			texMap.remove(filename);
		super.finalize();
	}

	@Override
	public void render(Graphics2D g, int x, int y, int width, int height) {
		if (manager != null)
			g.drawImage(manager.getImage(), x, y, width, height, null);
	}

	public BufferedImage grabSubTexture(int x, int y, int width, int height) {
		return manager.getImage().getSubimage(x, y, width, height);
	}

	public BufferedImage grabTexture() {
		return manager.getImage();
	}

	public int getHeight() {
		return manager.getImage().getHeight();
	}

	public int getWidth() {
		return manager.getImage().getWidth();
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
}