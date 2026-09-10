package ie.gmit.food;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Food extends TilePiece implements Eatable 
{
	private String name = "Food";
	private int health = 0;
	private int hunger = 0;
	private int expireStart = 0;
	private int expireMoveCount = 50;
	
	public Food(int x, int z,String name,int health,int hunger)
	{
		super(TileType.FOOD, x, z);
		
		this.health = health;
		this.name = name;
		this.hunger = hunger;
	}
	
	public int giveHealth()
	{
		return this.health;
	}
	
	public int giveHunger()
	{
		return this.hunger;
	}
	
	public void pickedUp()
	{
		expireStart = GlobalsVars.TurnCount;
	}
	
	public String getFoodName()
	{
		return this.name;
	}
	
	public boolean isEdable() 
	{
		if((expireStart - expireMoveCount) < GlobalsVars.TurnCount)
			return true;
		else
			return false;
	}	
}
