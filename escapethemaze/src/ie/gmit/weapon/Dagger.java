package ie.gmit.weapon;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ie.gmit.tile.TileType;

public class Dagger extends Weapon {

	BufferedImage image;
	
	public Dagger(int x, int z,String w, int aV, int dV) {
		
		super(TileType.WEAPON, x, z, w, aV, dV);
		
		try
		{
			image = ImageIO.read(new java.io.File("Images/dagger.png"));
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
