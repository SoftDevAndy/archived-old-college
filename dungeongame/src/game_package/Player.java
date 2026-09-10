// SoftDevAndy Year 2 Project for Xmas
// The Player Class
// This class tracks the players name, x & y co-ordinates and gold count
// The Player class is a (extends) the Being class.
// 83 Lines

package game_package;	// Part of the over all game_package

import java.util.Random;	// Used for the random Method

public class Player extends Being		// Extends the being class making the Player a Being
{	
	Random gimme = new Random();		// Random object called gimme
	
	static String name;					// Players name
	static int gold;					// Players gold
	static int xCo;						// Players X Co-ordinate
	static int yCo;						// Players Y Co-ordinate
	
	public Player(String n,int a,int b,int c,int d,int e,int f)			// Takes in the players name and the stats for the being class
	{
		super(a, b, c, d, e, f);										// Takes the stamina min/max, health min/max and damage min/max
		setName(n);														// Sets the players name
		
		resetGold();													// Sets the players gold to 0
	}	// The player constructor
	
	private void setName(String nme)
	{
		name = nme;
	}	// Sets the players name

	public String getName()
	{
		return name;
	} // Gets the players name

	public int getGold()
	{
		return gold;
	}	// returns the players gold int
	
	public void resetGold()
	{
		gold = 0;
	}	// resets the players gold count to 0
	
	public void addGold(int add)
	{
		gold += add;
	}	// used to add to the players gold the amount of gold on the dead monster
	
	public void setPosition(int x,int y)
	{
		xCo = x;
		yCo = y;
	}		// Sets the players x and y positions
	
	public void passHealing()
	{
		if(super.health < super.healthFull && super.health + 1 <= super.healthFull)		// Checks the players initial (full health) against his current health. If his health + 1 is under or = his full health
		{																				// then he can heal up by 1 point
			super.health += 1;
			System.out.println("You've rested 1 health");
		}
		
		if(super.baseStamina < super.baseStamina && super.baseStamina + 1 <= super.baseStaminaFull) // Checks the players initial (full stamina) against his current stamina. If his health + 1 is under or = his full stamina
		{																							// or = his full stamina then he can regen up by 1 point
			super.baseStamina += 1;
			System.out.println("You've rested 1 Stamina");
		}
	}
	
	public int getPlayerX()
	{
		return xCo;		
	}	// returns the players x int
	
	public int getPlayerY()
	{
		return yCo;		
	}	// returns the players y int
}