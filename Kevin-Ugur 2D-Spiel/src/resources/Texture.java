package resources;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Texture {
	private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
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
			System.out.println(filename + ".png ... Texture loaded");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (manager.removeReference() && !filename.isEmpty())
			texMap.remove(filename);
		super.finalize();
	}
	
	public void render(Graphics2D g, int x, int y) {
		g.drawImage(manager.getImage(), x, y, null);
	}
	
	public void render(Graphics2D g, int x, int y, int width, int height) {
		g.drawImage(manager.getImage(), x, y, width, height, null);
	}
	
	public void render(Graphics2D g, AffineTransform at) {
		g.drawImage(manager.getImage(), at, null);
	}
	
	public BufferedImage grabSubTexture(int x, int y, int width, int height) {
		return manager.getImage().getSubimage(x, y, width, height);
	}
	
	public BufferedImage grabTexture() {
		return manager.getImage();
	}
}