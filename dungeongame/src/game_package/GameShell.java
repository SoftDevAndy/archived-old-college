// SoftDevAndy Year 2 Project for Xmas
// This is the GameShell class
// This class manages all the graphics and stats that display on the JFrame
// The Jframe is strictly visual and isn't interactive
// 206 Lines

package game_package;				// Part of the overall Game package

import javax.swing.*;				// Used for Jpanels
import java.awt.event.*;			// Awt class lets you use the Listeners
import java.awt.*;					// Allows you to paint onto the JPanel
import javax.imageio.ImageIO;		// Allows the input and output of Images
import java.io.IOException;			// Class that is used to handle input/output exceptions
import java.net.URL;				// Lets you search through directories
import javax.sound.sampled.*;		// Allows the use of audio

public class GameShell extends JPanel implements ActionListener  // setting Gameshell as a JPanel
{
	private final int SCREEN_LEN = 800;		// Constant value for the screen length
	private final int SCREEN_WID = 600;		// Constant value for the screen width	
	private final int GOLD_MULT = 25;
	
	private final int MAX_MOVE = 70;		// The maximum point of pixels the image will "Float" for 
	private final int MIN_MOVE = 40;		// The minimum point of pixels the image will "Float" for
	private int moveVal = 50;				// The base value for the counter
	private boolean movey = false;			// Sets whether the monster is floating or not
	
	public boolean checkMon = false;		// The constant that checks if a monster is "visible" or not
	public static String monName;			// The static monster name string printed to the JPanel
	public static int monHealth;			// The static monster health int printed to the JPanel
	public static int monDamage;			// The static monster damage int printed to the JPanel		
	public static int monStamina;			// The static monster stamina int printed to the JPanel
	public static int monGold;				// The static monster gold int printed to the JPanel
	
	public static String haveKeyString = "Have key!";		// This string is printed to the JPanel if you have a key
	public static String dontHaveKeyString = "No key..";	// This string is printed to the JPanel if you don't have a key	

	public GameShell()
	{
		Timer time = new Timer(10, this);	// Initialising and setting the refresh rate
		time.start();						// Starting the timer
		this.setFocusable(true);			// Puts the JPanel in the JFrame
		
		gameMusic();	// Plays the game music
		noMonster();	// Clears the monster values to initial ones (if left null it can crash the JPanel because of null reference)
	}					// The JPanel initial construct. 
	
	public void update()
	{
		makeMonsterFloaty();	// Starts the monster floating animation

	} // The main update function acts as a place to update everything at once
	
	public void paintComponent(Graphics g)	// abstract paint method has to be included
	{		
		try
		{
			Image inventoryBar = ImageIO.read(this.getClass().getResource("/Images/CavePanel.jpg")); // sets inventory bar as the cavepanel image from the directory

			g.drawImage(inventoryBar,600, 0, 600, SCREEN_LEN,null); // Draws the image to the screen at this co-ordinates
		}
		catch (IOException ex) {}			// Catches the image not found exceptions etc 	
		
		// Inventory Panel
		
		g.setColor(Color.WHITE);								// Sets the initial colour of the text
		g.setFont(new Font(null, Font.ROMAN_BASELINE , 20));	// Setting the Font size
		g.drawString("Statistics",660, 30);						// Drawing the stats
		g.drawString("Name: " + Player.name,620, 60);			// Drawing the static player name
		g.drawString("Health:       " + RunMe.player.getHealth(),620, 90);	// Drawing the current player health from the player object (non static, from being class)
		g.drawString("Damage:    " + RunMe.player.getDamage(),620, 120);	// Drawing the current player damage from the player object (non static, from being class)
		g.drawString("Stamina:   " + RunMe.player.getStamina(),620, 150);	// Drawing the current player stamina from the player object (non static, from being class)
		g.drawString("Gold:        " + Player.gold,620, 180);				// Drawing the static gold count for the player
		g.drawString("Move Count: " + RunMe.moveCount,620, 210);			// Drawing the static move/turn count (each time the player moves)
														
		g.drawString("Monster: " + monName,620, 280);		//	Drawing the temp monster name from the current monster object
		g.drawString("Health: " + monHealth,620, 300);		//	Drawing the temp monster health from the current monster object
		g.drawString("Damage: " + monDamage,620, 320);		//	Drawing the temp monster damage from the current monster object
		g.drawString("Stamina: " + monStamina,620, 340);	//	Drawing the temp monster stamina from the current monster object
		g.drawString("Gold: " + monGold,620, 360);			//	Drawing the temp monster gold from the current monster object
		
		
		g.setColor(Color.RED);	
		g.drawString("Score:  " + RunMe.playerScore,620,430);
		g.drawString("Monsters dead: " + RunMe.monsterCount, 620, 450);
		
		// Inventory Panel Text
		
		try
		{
			Image caveBack = ImageIO.read(this.getClass().getResource("/Images/Cave.jpg")); // sets caveBack as the Cave image from the directory

			g.drawImage(caveBack,0, 0, 600, 600,null);	// Draw the caveBack image to the screen
		}
		catch (IOException e)		{}			// Catches the image not found exceptions etc		
		
		// Background Panel

		try
		{
			checkMon = RunMe.monsterBesideYou;			// Pulls whether a monster is beside you or not from the RunMe class
			
			if(checkMon)	// If a monster is beside you
			{		

				Image monImg = ImageIO.read(this.getClass().getResource(RunMe.currentMonsterImage));	// Finds the monImg

				g.drawImage(monImg,150, moveVal, SCREEN_WID/2, SCREEN_LEN/2,null);	//  Draws the monImg


				Image battleText = ImageIO.read(this.getClass().getResource("/Images/Battle.png"));		//	Finds the Battle Image

				g.drawImage(battleText,150, 400, SCREEN_WID/2, 100,null);	//	Draws the battleText
				
				yesMonster();	// Passes the monster stats to the temporary strings for the JPanel
			}
			else
			{
				noMonster();	// Clears the old monster stats to blank values for the JPanel
			}
		}
		catch (IOException ex) 		{} 		
		
		// Monster
		
	}	// Paints everything on to the JPanel in order
	
	public void actionPerformed(ActionEvent e)
	{
		update();	// might need a loop in the battle function to stop it from re running
		
		repaint();
	}	// If something happens do stuff.... This acts like a loop for repainting
	
	public void makeMonsterFloaty()
	{
		if(!movey)
		{
			++moveVal;	
			
			if(moveVal > MAX_MOVE)
			{
				movey = true;
			}
		}
		
		if(movey)
		{
			--moveVal;
			
			if(moveVal < MIN_MOVE)
			{
				movey = false;
			}
		}			
	} // Changes the Y Co-ordinates of the monster Image so it has a floating event it increments by being called (update method)	
	
	public static void noMonster()
	{
		monName = "None";
		monHealth = 0;
		monDamage = 0;
	    monStamina = 0;
		monGold = 0;		
	}	// Sets the temp strings used for the monster stats on the JPanel to null values
	
	public void yesMonster()
	{
		monName = RunMe.currentMonster.getMonName();
		monHealth = RunMe.currentMonster.getHealth();
		monDamage = RunMe.currentMonster.getDamage();
	    monStamina = RunMe.currentMonster.getStamina();
		monGold = RunMe.currentMonster.getGold();		
	}	// Pulls the monster stats from the current monster and displays them on the JPanel

	public void gameMusic()
	{
		try
		{
			 URL url = this.getClass().getClassLoader().getResource("Music/Music.mid");	// Locates the file
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);	// Defines an audio stream to the mid file

			 Clip clip = AudioSystem.getClip();	// Defines a clip
			 clip.open(audioIn);				// Opens the stream
			 
			 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // makes a volume control
			 gainControl.setValue(-10.0f);			 	// Sets a volume control
			 
			 clip.start();	// Starts the music
			 clip.loop(Clip.LOOP_CONTINUOUSLY);	// Loops the music when it reaches the end
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}							// Handles wrong file type
		catch (IOException e)
		{
			e.printStackTrace();
		}							// Handles audio errors
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}	// Handles finding and playing the Midi File in the music package
		
	}	// Handles finding and playing the Midi File in the music package
}