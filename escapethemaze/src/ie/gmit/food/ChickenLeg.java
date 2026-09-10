package ie.gmit.food;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ChickenLeg extends Food 
{
	BufferedImage image;
	
	public ChickenLeg(int posX,int posZ)
	{	
		super(posX, posZ, "ChickenLeg",10,20);
		
		try
		{
			image = ImageIO.read(new java.io.File("Images/chickenleg.png"));
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
