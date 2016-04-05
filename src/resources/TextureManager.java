package resources;

import java.awt.image.BufferedImage;

public class TextureManager extends ResourceManager {
	private BufferedImage image;
	
	/**
	 * 
	 * @param image
	 */
	public TextureManager(BufferedImage image) {
		super();
		this.setImage(image);
	}

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * 
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
