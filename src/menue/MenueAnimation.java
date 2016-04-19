package menue;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class MenueAnimation  extends Applet{

	private Image bomb= null;
	
	public void paint(Graphics g)
	{
		if(bomb == null)
			bomb=imageLoader("bomb.png");
			
		Graphics2D g2= (Graphics2D)g;
		g2.drawImage(bomb, 25,50,50,50,this);
			
	}
	
	public Image imageLoader(String path)
	{
		Image tempImage = null;
		try{
			URL imageURL =MenueAnimation.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		}
		catch(Exception e)
		{
			System.out.println("An error occured-"+e.getMessage());
		}
		return tempImage;
	}
	
}
