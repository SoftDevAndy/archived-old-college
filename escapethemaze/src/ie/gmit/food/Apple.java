package ie.gmit.food;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Apple extends Food
{
	BufferedImage image;
	
	public Apple(int posX,int posZ)
	{	
		super(posX, posZ, "Apple",5,10);
		
		try
		{
			image = ImageIO.read(new java.io.File("Images/apple.png"));
			setTileImage(image);
			
		}
		catch(Exception e)
		{
			e.toString();
		}
	}
	
	public BufferedImage getFoodImage()
	{
		return this.image;
	}
}
