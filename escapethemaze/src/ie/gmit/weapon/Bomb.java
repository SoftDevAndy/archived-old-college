package ie.gmit.weapon;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import ie.gmit.tile.TileType;

public class Bomb extends Weapon implements Explodable{

	BufferedImage image;
	private int damageRadius = 5;
	
	public Bomb(int x, int z,String w, int aV, int dV) {
		
		super(TileType.WEAPON, x, z, w, aV, dV);
		
		try
		{
			image = ImageIO.read(new java.io.File("Images/bomb.png"));
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

	public int getDamageRadius() {
		return this.damageRadius;
	}
}
