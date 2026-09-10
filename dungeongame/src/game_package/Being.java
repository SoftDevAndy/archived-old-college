// SoftDevAndy Year 2 Project for Xmas
// Being Class - Also Known as the base class
// This class is used as the base call for both the Player and the Monsters
// Health, Stamina and Damage attributes are inherited by those both classes
// Number of lines 87

package game_package;		// Part of the overall Game package
import java.util.*;			// Java.util ( I use this mainly for the Random() class)

public class Being
{
	int health;					// Health stat
	int baseStamina;			// Stamina stat
	int baseDamage;				// Damage stat
	
	int healthFull;				// Full Health stat (used as a dynamic constant when the initial stats are read in)
	int baseStaminaFull;		// Full Stamina stat (used as a dynamic constant when the initial stats are read in)
	
	Random give = new Random();			// Random object used to randomize stats
	
	public Being(int sMin,int sMax,int dMin,int dMax,int hMin,int hMax) // Takes in minStam,maxStam,minDamage,maxDamage,minHealth,maxHealth
	{
		setStamina(sMin,sMax + 1).setDamage(dMin,dMax).setHealth(hMin,hMax + 1);	// Using cascading methods to set initial stats
		
		healthFull = health;						// sets the maximum health for the player so the health doesn't over heal
		baseStaminaFull = baseStamina;				// sets the maximum stamina for the player so the health doesn't over regen
	}	// The only and initial Being constructor
	
	private Being setStamina(int min,int max)
	{
		baseStamina = give.nextInt(max);	
		
		while(baseStamina < min)
		{
			baseStamina = give.nextInt(max);
		}	
		
		return this;
	}	// This method returns the Object using the this method. It uses the random method to set a random baseStamina Value
	
	private Being setDamage(int min,int max)
	{
		baseDamage = give.nextInt(max);
		
		while(baseDamage < min)
		{
			baseDamage = give.nextInt(max);
		}	
		
		return this;
	}	// This method returns the Object using the this method. It uses the random method to set a random baseDamage Value
	
	private void setHealth(int min,int max)
	{
		health = give.nextInt(max);
		
		while(health < min)
		{
			health = give.nextInt(max);
		}		
	}	// This method is last in the cascading method it doesn't return anything. It uses the random method to set a random health Value
	
	public void updateHealth(int dmg)
	{
		health -= dmg;
	}	// Method used to decrease the player/monsters health
	
	public void updateStamina(int stam)
	{
		baseStamina += stam;
	}	// Method used to decrease the player/monsters stamina
	
	public int getStamina()
	{
		return baseStamina;
	}	// returns baseStamina value
	
	public int getDamage()
	{
		return baseDamage;
	}	// returns baseDamage value
	
	public int getHealth()
	{
		return health;
	}	// returns health value	
}
