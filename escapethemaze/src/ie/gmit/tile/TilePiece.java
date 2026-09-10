package ie.gmit.tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TilePiece {

	private TileType tileType;
	private BufferedImage image;
	private int x,z;
	
	public TilePiece(TileType tileType,int x,int z)
	{
		setTileType(tileType);
		setX(x);
		setZ(z);
		
		if(tileType == TileType.DEBUG)
		{
			try 
			{
				image = ImageIO.read(new java.io.File("Images/debug.png"));
			}
			catch (IOException e){e.toString();}
			
		}
		else if(tileType == TileType.WALL)
		{
			try 
			{
				image = ImageIO.read(new java.io.File("Images/wall.png"));
			}
			catch (IOException e){e.toString();}
		}
		else if(tileType == TileType.HINT)
		{
			try 
			{
				image = ImageIO.read(new java.io.File("Images/floorHint.png"));
			}
			catch (IOException e){e.toString();}
		}
		else if(tileType == TileType.FLOOR)
		{
			try 
			{
				image = ImageIO.read(new java.io.File("Images/floor.png"));
			}
			catch (IOException e){e.toString();}
		}
		else if(tileType == TileType.EXIT)
		{
			try 
			{
				image = ImageIO.read(new java.io.File("Images/door.png"));
			}
			catch (IOException e){e.toString();}
		}
		else if(tileType == TileType.HELP)
		{
			try 
			{
				image = ImageIO.read(new java.io.File("Images/cage.png"));
			}
			catch (IOException e){e.toString();}
		}
		else
		{			
			try 
			{
				image = ImageIO.read(new java.io.File("Images/debug.png"));
			}
			catch (IOException e){e.toString();}
		}
	}
	
	public void setTileType(TileType tileType)
	{
		this.tileType = tileType;
	}
	
	public TileType getTileType()
	{
		return this.tileType;
	}
	
	public void setTileImage(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage getTileImage()
	{
		return this.image;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public void setZ(int z)
	{
		this.z = z;
	}
	
	public int getZ()
	{
		return this.z;
	}
}
