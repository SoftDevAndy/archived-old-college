// SoftDevAndy Year 2 Project for Xmas
// The Monster Class
// This class tracks the Monsters name, x & y co-ordinates,gold count, the URI's for the monster images and alive status
// The Monster class is a (extends) the Being class.
// 103 Lines

package game_package;							// Part of the over all game_package

import java.util.*;								// For the Random Class method

public class Monster extends Being				// Extends the being class (inherits the stamina,health,damage methods)
{	
	final static int MAX_NAME_IMAGE = 7;		// Used as the max range for getting the random image uri string from the monsterImage string array				
	final static char GROUND_SYMBOL = '.';		// The constant symbol used to reset the place in the array the monster "dies" on (resets it back to ground instead of M)
	Random gimme = new Random();				// Standard Random Method
		
	String monsterName;							// MonsterName String
	String monsterImg;							// MonsterImage String (the uri for the monster image gets set to this)
	int gold;									// Gold Amount
	int xCo;									// X Co-ordinates
	int yCo;									// Y Co-ordinates
	boolean monsterAlive = true;				// Whether the monster is alive of not (alive by default)
	
	public String allMonstName[] =
	{	 
		 "Bear",
		 "Goblin",
		 "Ogre",
		 "Snake",
		 "Spider",
		 "Wraith",
		 "Zombie"
	};					// Holds all the monster names relative to the monster images
	
	public String monsterImage[] =
	{
		"/MonsterImages/Bear.png",
		"/MonsterImages/Goblin.png",
		"/MonsterImages/Ogre.png",
		"/MonsterImages/Snake.png",
		"/MonsterImages/Spider.png",
		"/MonsterImages/Wraith.png",
		"/MonsterImages/Zombie.png"
	};					// Holds the monster image locations relative to the monster names
	
	public Monster(int x,int y,int a,int b,int c,int d,int e,int f)	// Takes in co-ordinates and all the default stat ranges just like the player
	{
		super(a, b, c, d, e, f);		// Calling the being class to pass the stats down
 		
		setNameImgToo();				//	Allocates the Monster objects Image and name
		setGold();						//  Sets the gold amount

		setMonCords(x,y);				//  Sets the monsters co-ordinates (passed in from RunMe where the monsters position is decided)
	}	
	
	private void setNameImgToo()
	{
		int pos;

		pos = gimme.nextInt(MAX_NAME_IMAGE);
		monsterName = allMonstName[pos];
		monsterImg = monsterImage[pos];
	}										// Sets the name and Image Very important for visual rep
	
	private void setGold()
	{
		final int MAX = 50;

		gold = gimme.nextInt(MAX);
		
		while(gold < 1)
		{
			gold = gimme.nextInt(MAX);
		}		
	}									// Basic random gold method it makes sure the monster always has more than 1 gold
	
	public int getGold()
	{
		return gold;
	}									// returns the gold int value
	
	public String getMonName()
	{
		return monsterName;
	}									// returns the string for the monsters name
	
	public String toString()
	{
		return  getMonName() + " " + getHealth() + " " + getDamage() + " " + getStamina()  + " " + getGold() + "\n";	// Returns out all the monsters stats to a string
	}
	
	public void setMonCords(int a,int b)
	{
		xCo = a;
		yCo = b;
	}								// Takes in the co-ordinates set by RunMe for the monsters location and sets them inside of the Monster object
	
	public void killMonster()
	{
		monsterAlive = false;						// Sets the monsters alive status to not alive
		RunMe.map[xCo][yCo] = GROUND_SYMBOL;		// Changes the location of the monsters M character in the array to '.'
	}	
}