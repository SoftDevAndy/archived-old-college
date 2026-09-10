package ie.gmit.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.food.Food;
import ie.gmit.fuzzy.Hunger;
import ie.gmit.fuzzy.HungerStatus;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;
import ie.gmit.weapon.Weapon;

public class Player extends TilePiece
{
	private int health;
	private int hunger = 100;
	private int currentFoodPosition = 0;
	private int currentWeaponPosition = 0;
	private List<Food> InventoryFood = new ArrayList<Food>();
	private List<Weapon> InventoryWeapons = new ArrayList<Weapon>();
	private Hunger h;
	private boolean isAlive = true;
	
	private int imagePosition = 0;
	List<BufferedImage> allFrames;
	BufferedImage imageFrame1;
	BufferedImage imageFrame2;
	BufferedImage imageFrame3;
		
	public Player(int health,int x, int z) 
	{
		super(TileType.PLAYER, x, z);
		this.health = health;
		h = new Hunger();
				
		try 
		{
			imageFrame1 = ImageIO.read(new java.io.File("Images/hero_walk_1.png"));
			imageFrame2 = ImageIO.read(new java.io.File("Images/hero_walk_2.png"));
			imageFrame3 = ImageIO.read(new java.io.File("Images/hero_walk_3.png"));
		}
		catch (IOException e){e.toString();}
		
		allFrames = new ArrayList<BufferedImage>();
		
		if(imageFrame1 != null && imageFrame2 != null && imageFrame3 != null)
		{
			allFrames.add(imageFrame1);
			allFrames.add(imageFrame2);
			allFrames.add(imageFrame3);
		}
	}
	
	
	
	public BufferedImage getTileImage()
	{
		imagePosition++;
		
		if(imagePosition == allFrames.size())
			imagePosition = 0;
		
		return allFrames.get(imagePosition);
	}
	
	public int getAttack()
	{
		int x = getCurrentWeapon().getAttackValue();
		
		if(getHungryStatus() == HungerStatus.WEAK)
		{
			if(x - 8 <= 0)
				return 0;
			else
				x -= 8;
			
			return x;
		}
		else if(getHungryStatus() == HungerStatus.WEAKEST)
		{
			if(x - 6 <= 0)
				return 0;
			else
				x -= 6;
			
			return x;
		}
		
		return x;
	}
	
	public void weaponUsed()
	{
		if(canUseWeapon())
		{
			getCurrentWeapon().weaponUsed();
			
			if(getCurrentWeapon().getDurabilityValue() == 0)
			{				
				InventoryWeapons.remove(currentWeaponPosition);
				currentWeaponPosition = 0;
			}
		}
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public int getHunger()
	{
		return this.hunger;
	}
	
	public void setHunger(int hunger)
	{
		this.hunger = hunger;
	}
	
	public int getFoodCount()
	{
		return this.InventoryFood.size();
	}
	
	public int getWeaponCount()
	{
		return this.InventoryWeapons.size();
	}
	
	public int getWeaponPos()
	{
		return this.currentWeaponPosition;
	}
	
	public int getFoodPos()
	{
		return this.currentFoodPosition;
	}
	
	public void feelHunger()
	{
		int h = GlobalsVars.RandomNumber(7);
		
		if(this.hunger - h > 0)
			this.hunger -= h;
	}
	
	public boolean canEatFood()
	{
		try
		{
			if(InventoryFood.size() > 0)
				if(InventoryFood.get(currentFoodPosition) != null)
					return true;			
		}
		catch(Exception e){e.toString(); return false;}
		
		return false;
	}
	
	public boolean canUseWeapon()
	{
		try
		{
			if(InventoryWeapons.size() > 0)
				if(InventoryWeapons.get(currentWeaponPosition) != null)
					return true;
			
		}
		catch(Exception e){e.toString(); return false;}
		
		return false;
	}
	
	public Food getCurrentFood()
	{
		if(canEatFood())
		{
			return this.InventoryFood.get(currentFoodPosition);
		}
		
		return null;
	}
	
	public Weapon getCurrentWeapon()
	{
		if(canUseWeapon())
		{
			return this.InventoryWeapons.get(currentWeaponPosition);
		}
		
		return null;
	}
	
	public void eatCurrentFood()
	{
		if(canEatFood())
		{
			this.health += InventoryFood.get(currentFoodPosition).giveHealth();
			this.hunger += InventoryFood.get(currentFoodPosition).giveHunger();
			InventoryFood.remove(currentFoodPosition);
			currentFoodPosition = 0;
		}
	}
	
	public void cycleFood()
	{
		if(currentFoodPosition + 1 > InventoryFood.size() - 1)
		{
			currentFoodPosition = 0;
		}
		else
		{
			currentFoodPosition++;
		}
	}
	
	public void pickupFood(TilePiece f)
	{
		Food food = (Food)f;
		
		this.InventoryFood.add(food);
	}
	
	public void pickupWeapon(TilePiece w)
	{
		Weapon weapon = (Weapon)w;
		
		this.InventoryWeapons.add(weapon);
	}
	
	public void cycleWeapons()
	{
		if(currentWeaponPosition + 1 > InventoryWeapons.size() - 1)
		{
			currentWeaponPosition = 0;
		}
		else
		{
			currentWeaponPosition++;
		}
	}
	
	public HungerStatus getHungryStatus()
	{
		return h.howHungryAmI(getHealth(),getHunger());
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
