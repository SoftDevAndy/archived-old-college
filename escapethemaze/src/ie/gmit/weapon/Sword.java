package ie.gmit.weapon;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ie.gmit.tile.TileType;

public class Sword extends Weapon {

	BufferedImage image;
	
	public Sword(int x, int z,String w, int aV, int dV) {
		
		super(TileType.WEAPON, x, z, w, aV, dV);
		
		try
		{
			image = ImageIO.read(new java.io.File("Images/sword.png"));
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
