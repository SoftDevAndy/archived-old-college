// SoftDevAndy Year 2 Project for Xmas
// The Discriber class
// This class is just used to hold big strings
// this keeps my main code from being jumbled with strings.
// 147 Lines

package game_package; // Part of the overall Game package

import java.util.*;		// Used for the Random function

public class Discriber
{
	private final int ATTACK_MAX = 19;			// monster monsterAttack array max value 
	private final int ATTACK_DISC_MAX = 27;		// monster monsterDiscripter string array max value 
	
	public String monsterAttack[] =						// 19 Values
	{
		"swipes","lunges at","attacks","strikes","charges","mugs","pushes","jumps at",
		"barrages","ravages","hurts","mobs","bashes","blasts","slams","lashes at","gashes",
		"scrapes","scratch"
	};

	public String monsterDiscripter[] =					// 27 Values
	{
		 "howls","snarls","yells","taunts","roars","screams","shouts","squeals",
		 "wails","bellows","cries","coughs","laughs","points","moves forward","leaps",
		 "dives","twitches","vaults","hops","jolts","dashes","sprints","shifts","turns",
		 "hobbles","crawls forward"
	};
	
	public String monsterFightDiscription() 
	{
		Random give = new Random();
		String disc,atk;
		int temp;
		
		temp = give.nextInt(ATTACK_MAX);
		
		atk = monsterAttack[temp];
		
		temp = give.nextInt(ATTACK_DISC_MAX);
		
		disc = monsterDiscripter[temp];
		
		return disc + " and " + atk;
	}	// Chooses a random discription and attack and returns it as a screen for the battle function

	public void WelcomeMessage()
	{
		System.out.println("Welcome to SoftDevAndy's Year 2 Java Project");
		System.out.println("If you need help type \"Help\"");
		
		System.out.println("============================================================");
		System.out.println("Deep inside the main frame there was a character.");	
		System.out.println("\nHe was alone,lost inside of a forgotten character array.");
		System.out.println("\nHe was P character and he was mislaid but there was a path.");
		System.out.println("\nA few corrupt M characters seemed to sit stagnant.");
		System.out.println("\nMaybe if he fought and fixed the corrupt M characters");
		System.out.println("\nhe could fix the array and go back to working normally.");
		System.out.println("\nP would need a name though... all heroes have names...");
		System.out.println("============================================================");
	}	// Welcome Text Part 1
	
	public void WelcomeWarning(String name)
	{
		System.out.println("\n" + name + " thought to himself for awhile.. maybe the M characters");
		System.out.println("gave themselves names too...maybe big scarey monster names!");
		System.out.println("I guess if I have a personality the M characters can have");
		System.out.println("their own too along with big scarey names and attacks...*gulps*");
		System.out.println("The character array looked like some sort of scarey cave");
		System.out.println("with twists and turns and perilous data " + name + " hoped he could escape.");		
	}	// Welcome Text Part 2
	
	public void help()
	{
		System.out.println("==============================================================");
		System.out.println("     The objective of the game is to kill all the monsters. \n \t\tFind the Key! Best of luck!");
		System.out.println("==============================================================");
		System.out.println("All Commands.....");
		System.out.println("Type \"Move\"      - Let's you move around 1 space at a time.");
		System.out.println("Type \"Run\"       - Let's you run a distance.");
		System.out.println("Type \"Map\"       - Prints a copy of the map to screen.");
		System.out.println("Type \"Where\"     - Prints out your co-ordinates in the array.");
		System.out.println("Type \"Story\"     - Prints out the beginning story to the screen.");
		System.out.println("Type \"PrntMap\"   - Prints a copy of the map to a text file.");
		System.out.println("Type \"Rest\"      - Rests for 1 turn and gives the player 3 health + 3 Stamina (use outside of battle).");
		System.out.println("Type \"PrntStats\" - Prints a copy of the player stats to a text file.");
		System.out.print("==============================================================\n");
	}	// Help Text
	
	public void battleHelp()
	{
		System.out.println("\nType (Fi)ght to do your attack damage against the enemies health");
		System.out.println("Type (Sh)ield if you wish to defend against 2 points of the enemies next damage");
		System.out.println("Type (Re)st if you wish to rest and gain 2 stamina points but leaving you open to the enemies next damage");
		System.out.println("Type (Ba)ttlehelp to see these instructions again");
	}	// Battle help text
	
	public void giveXPrompMsg()
	{
		System.out.print("Please enter the x co-ordinate of where you wish to move: ");
	} // x Prompt Message
 
	public void giveYPrompMsg()
	{
		System.out.print("Please enter the y co-ordinate of where you wish to move: ");
	} // y Prompt Message
	
	public void giveWallPrompMsg()
	{
		System.out.println("You've met a wall here,choose again: ");
	} // Hit a wall message
	
	public void tooFarMsg()
	{
		System.out.println("You can only move one space at a time,choose again");
	} // To far message
	
	public void alreadyHereMsg()
	{
		System.out.println("You are already here choose again");
	} // Already here message
	
	public void moveSpaceMsg()
	{
		System.out.println("You've moved 1 space.");
	} // Moved one space message
	
	public void waitPlease()
	{
		System.out.println("Finding the music file and playing it (turn on your speakers)");
		System.out.println("Creating the JFrame and JPanel it takes one moment...");
		System.out.println("If a warning shows below this message the college admin permissions are causing it");
		System.out.println("Please refer to the comments the top of the RunMe.java file to see why this happens.");
	}
	
	public void win()
	{
		System.out.println("You win!");
		
		System.out.println("Peace was restored to the array. Our hero" + Player.name);
		System.out.println("was very happy with himself. He had saved the array from");
		System.out.println("some very scarey monsters. Well.. characters that thought");
		System.out.println("they were monsters..Thanks for taking time to play my game.");
		System.out.println("- SoftDevAndy");
	} // The fantastic Win Message
};	