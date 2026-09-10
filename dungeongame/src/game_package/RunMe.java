// SoftDevAndy Year 2 Project for Xmas
// The RunMe Class all user input and everything happens in this class
// 28/10/13
// Lines of code 760 Total line count between all programs (including comments and space is 1386)

// Very important note. java.util.prefs.WindowsPreferences <init>
// WARNING: Could not open/create prefs root node Software\JavaSoft\Prefs at root 0x80000002.
// Windows RegCreateKeyEx(...) returned error code 5. Occurs ONLY in the labs in college.

// This happens because I don't have admin permissions.
// http://stackoverflow.com/questions/5354838/java-java-util-preferences-failing
// http://www-01.ibm.com/support/docview.wss?uid=swg21496098

package game_package;	// Part of the overall Game package

import javax.swing.*;				// Needed to use JFrames
import java.io.*;					// Lets me write files
import java.util.*;					// Used for the Random Class

public class RunMe extends JFrame 	// Lets me create a JFrame
{
	static int monsterCount = 0;			// Used to track how many monsters you've killed
	
	private final int SCREEN_LEN = 800;		// Constant value for the screen length
	private final int SCREEN_WID = 600;		// Constant value for the screen width
	private static final int SCORE_MULT = 25;
	
	static int playerScore = 0;
	
	static final int TURN_COST = 2;			// The Stamina cost for each action
	static final int STAM_POINT = 1;		// The amount of stamina given back each turn
	
	private final static char PLAYER_SYMBOL = 'P';	// The constant player character
	private final static char GROUND_SYMBOL = '.';	// The ground symbol in the array
	
	static boolean monsterBesideYou = false;	//	The boolean that tracks whether a monster in beside you or not
	static boolean battleTime = false;			//  The boolean used to set whether a battle is taking place or not
	static boolean battleJustHappened = false;	//  The boolean that is used to track where a battle happened (used for clean up)
	
	static String n;							// Initial name string
	
	static final int minStamina = 7;
	static final int maxStamina = 12;
	static final int minDamage = 3;
	static final int maxDamage = 12;		// Base Player Stats
	static final int minHealth = 15;
	static final int maxHealth = 20;
	
	static final int sMinMon = 4;
	static final int sMaxMon = 8;
	static final int dMinMon = 3;			// Base Monster Stats
	static final int dMaxMon = 6;
	static final int hMinMon = 5;
	static final int hMaxMon = 12;	
	
	static private final int Y_AXIS = 15;			//	The Maximum array value for the Y_Axis used when placing monsters
	static private final int X_AXIS = 25;			//	The Maximum array value for the X_Axis used when placing monsters
	
	static final int MON_MAX = 7;					// Max Monsters
	static final int SPAWN_PROTECT = 3;				// Stops monsters spawning close to player at startup
	static final char MONSTER_SYMBOL = 'M';
	
	static Scanner console = new Scanner(System.in);	// Reads in input
	static Discriber yarn = new Discriber();			// Discriber class holds most of the discriptions
	
	static Player player;								// Player object (see player class)
	
	static Random rand = new Random();					// Random method, used for everything		
	
	static Monster[] monsters = new Monster[MON_MAX];  // Declaring the Monsters Array
		
	static int moveCount = 0;	// The inital move count
	
	static String currentMonsterImage = "";	// The monster image url/uri get's rewritten
	
	static Monster currentMonster;		//	The currentMonster object used as a reference 
	
	static boolean fightHappening = true;	// boolean used to check wheth a fight is happening or not 
	
	static char[][] map = new char [][]    // Array Size 24 x 15
	{
		{'|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|'},
		{'|','.','.','|','.','.','|','.','|','.','|','.','.','.','|','.','.','|','.','.','.','.','.','.','|'},
		{'|','.','.','|','.','.','|','.','|','.','|','.','|','.','.','.','.','|','.','.','.','.','.','.','|'},
		{'|','.','|','|','|','.','|','.','|','.','|','.','|','.','|','.','.','|','.','|','|','.','.','.','|'},
		{'|','.','.','|','.','.','.','.','|','.','|','.','.','.','|','|','|','|','.','.','|','.','.','.','|'},
		{'|','.','.','|','.','.','.','.','|','.','|','.','.','.','|','.','.','|','.','.','|','|','|','|','|'},
		{'|','.','.','|','.','|','|','|','|','.','|','|','|','.','|','.','.','|','.','.','|','.','.','.','|'},
		{'|','.','.','|','.','.','|','.','.','.','|','.','.','.','|','.','.','|','.','.','.','.','.','.','|'},
		{'|','.','|','|','.','.','.','.','.','.','|','.','.','.','.','.','.','|','|','|','.','.','.','.','|'},
		{'|','.','.','.','.','.','.','.','.','.','|','.','|','|','|','|','.','.','.','|','|','|','|','.','|'},
		{'|','.','|','|','|','|','|','.','|','.','|','.','.','.','.','|','.','|','.','.','.','.','|','.','|'},
		{'|','.','.','|','.','.','.','.','|','.','|','.','.','.','.','|','.','|','.','.','.','.','.','.','|'},
		{'|','.','.','|','.','.','.','.','|','.','|','|','|','|','.','|','.','|','.','|','.','.','|','.','|'},
		{'|','.','.','|','.','.','.','.','|','.','.','.','.','.','.','|','.','.','.','|','.','.','|','.','|'},
		{'|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|'},
	};

	// The character map, the map is pre designed,planned out character array.
	
	public RunMe()
	{
		setTitle("Year 2 Project SoftDevAndy - Basic Dungeon Game");		// Title of the JFrame
		setSize(SCREEN_LEN, SCREEN_WID);									// Setting the JFrame Dimensions
		setResizable(false);												// Setting whether the JFrame is resizable
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						// Sets to kill the code when you X out

		add(new GameShell());		// Initialising

		setVisible(true);			// Setting that the JFrame is Visible
	}							// Setting the JFrame
	
	public static void main(String[] args) throws FileNotFoundException		// This exception must be thrown when writing to file
	{	
		boolean gameInPlay = true;			// The boolean that ensures the game loop is constant... until later
		String userInput;					// String used for user input
		
		yarn.WelcomeMessage();				// Displaying welcome message from the Discriber class
		nameCharacter();					// Naming the character
		placePlayer(1,1,map);				// Setting the initial x,y for the player
		populateMonster();					// Populates the map with 7 Monsters
				
		yarn.WelcomeWarning(n);				// Displays the opening method and prompts the user to set a name
		
		displayMap(map);			// Method used to display the character array to screen
		
		yarn.waitPlease();
		
		new RunMe();					// Creating a new RunMe Object		
		
		while(gameInPlay)				// This is the game loop everything happens within this loop 
		{			
			if(battleJustHappened)											// Checks whether you just left battle
			{
				checkIfClose(Player.xCo,Player.yCo,map,MONSTER_SYMBOL);		// Checks whether a monster is on you x + 1, x - 1, y + 1, y - 1 if so you enter a battle and monster is displayed to screen
				
				battleJustHappened = false;									// After the battle it resets the battle just happened to false for the next loop around
			}
			
			System.out.print("\nChoose your command: ");					// Prompts the User for input
			userInput = console.next();										// Takes the input
			
			checkInput(userInput);											// Checks the userInput string in the checkInput method
			
			checkIfClose(Player.xCo,Player.yCo,map,MONSTER_SYMBOL);	// Checking if a monster is close enough to interact with
			
		}	// the game loop
	}	
	 
	public static void checkInput(String s) throws FileNotFoundException	// has to be included when writing to a file
	{		
		if(s.equalsIgnoreCase("Move"))
		{
			movePlr();
		}												// checks if you typed in move and gives you the option to move the player by 1 space
		else if(s.equalsIgnoreCase("Story"))
		{
			yarn.WelcomeMessage();						// Displays the Welcome Message
			yarn.WelcomeWarning(n);						// Displays the second part of the welcome message
		}												// Re displays the two welcome messages
		else if(s.equalsIgnoreCase("Run"))
		{
			runFast();
		}												// Calls the method that lets you run around the map
		else if(s.equalsIgnoreCase("Help"))
		{
			yarn.help();
		}												// Displays all the commands to the user
		else if(s.equalsIgnoreCase("Map"))
		{
			displayMap(map);
		}												// Prints the map to the screen
		else if(s.equalsIgnoreCase("Where"))
		{
			displayCords();
		}												// Displays the x and y co-ordinates of the player
		else if(s.equalsIgnoreCase("PrntMap"))
		{
			prntMap(map);
		}												// Prints the map to a text file
		else if(s.equalsIgnoreCase("PrntStats"))
		{
			prntStats();
		}												// Prints the player stats to a text file
		else if(s.equalsIgnoreCase("Rest"))
		{
			System.out.println("Resting 1 turn for 3 Health and 3 Stamina");
			
			player.passHealing();
			player.passHealing();
			player.passHealing();
		}
		
		++moveCount;									// Healing up counts as 1 turn so it increments it on the move count
	}													// Lets the player "heal up" or "rest" by calling the rest method 3 times.
	
	public static void movePlr()
	{
		int x = 0;
		int y = 0;
		int difference;
		boolean canMove = false;
		
		System.out.println("Current co-ords: X: " + Player.xCo + " Y: " + Player.yCo);			// Prints current co-ordinates
		yarn.giveXPrompMsg();																	// Asks for X value
		x = console.nextInt();																	// Takes X value

		yarn.giveYPrompMsg();																	// Asks for Y value
		y = console.nextInt();																	// Takes Y value
		
		while(!canMove)																			// aslong as can move is false (conditions not met) you wil keep looping until you enter a valid move
		{		
			System.out.println("Current co-ords: X: " + Player.xCo + " Y: " + Player.yCo);		// Prints current co-ordinates
			difference = (Player.xCo - x) + (Player.yCo - y);									// finds the difference space wise
			
			if(Math.abs(difference) == 1 && map[x][y] != '|' && map[x][y] != 'M')	// Checks that you're moving 1 space exactly and not into a wall space or a monster space
			{
				yarn.moveSpaceMsg();						// Displays to screen that you moved 1 space
				
				canMove = true;								// Lets you out of the loop
			}												// Checks that position your moving too is only 1 space in difference away is so it moves you there
			else if(Math.abs(difference) == 0)
			{
				yarn.alreadyHereMsg();
				
				yarn.giveXPrompMsg();
				x = console.nextInt();
		
				yarn.giveYPrompMsg();
				y = console.nextInt();					
			}												// Checks if the co-ordinates entered are the ones you are already at if so asks you to enter x and y again
			else if(Math.abs(difference) > 1)				// Checks if the space you wish to move into is more than 1 space away
			{
				yarn.tooFarMsg();
				
				yarn.giveXPrompMsg();
				x = console.nextInt();
		
				yarn.giveYPrompMsg();
				y = console.nextInt();				
			}												// If it is it will ask you for x and y again
			else
			{				
				yarn.giveWallPrompMsg();				
				
				yarn.giveXPrompMsg();
				x = console.nextInt();
		
				yarn.giveYPrompMsg();
				y = console.nextInt();				
			}
		}													// Default catch orginaly used to catch extra exceptions out of range
		
		map[Player.xCo][Player.yCo] = GROUND_SYMBOL;		// If you meet the conditions is blanks the space you are in to the ground symbol
		map[x][y] = PLAYER_SYMBOL;							// sets the new space you moved into with the player symbol
		Player.xCo = x;										// Assigns the static player x position to the new position
		Player.yCo = y;										// Assigns the static player y position to the new position
		
		displayMap(map);								// prints out he map, newly updated
			
	}// A method that lets the player move exactly one position (if allowed, using the exact co-ordinates). This was the original way to move around but it proved slow. So I made the run method.
	
	public static void runFast()
	{
		boolean emptySpace = true;				// Used to check if the spaces you run through in a certain direction hits a monster or wall
		boolean forward = false;				// Used to track if your moving forward or back
		
		String choice = "";						// String used to take input from the user
		String forBac = "";						// string used to take input from the user
		
		int runDist;							// the amount of distance you wish to run in a direction
		int i;									// counter used numerous times
		
		System.out.print("Choose the direction you wish to run (enter x or y): ");
		choice = console.next();
		
		while(!choice.equalsIgnoreCase("x") && !choice.equalsIgnoreCase("y"))
		{
			System.out.print("Choose the direction you wish to run (enter x or y): ");
			choice = console.next();			
		}	// Takes in the Axis Choice	and ensures the player enters either an x or a y character
		
		while(!forBac.equalsIgnoreCase("for") && !forBac.equalsIgnoreCase("back"))
		{
			System.out.print("Do you wish to run (for)ward or (back)wards: ");
			forBac = console.next();	
		}	// Takes in the forward or back choice and ensures the player enters in either for or back
		
		if(forBac.equalsIgnoreCase("for"))
		{
			forward = true;
		}						// if the player entered for, forward boolean is true (otherwise it is false because you could have only entered back to get to this point)
		
		if(choice.equalsIgnoreCase("x"))
		{
			System.out.print("How many spaces do you wish to run on the X-Axis: ");
			runDist = console.nextInt();													// Prompts the player for run distance on the x axis
			
			runDist = Math.abs(runDist); // incase they enter a number that's negative, forces the number to an absolute
			
			if(forward)			// If they choose to go forward on the X - Axis
			{
				try				// I have to use a try here in case the amount I run by exceeds the character array bounds
				{
					for(i = 0; i <= runDist; ++i)
					{
						if(map[Player.xCo + i][Player.yCo] != '.' && map[Player.xCo + i][Player.yCo] != PLAYER_SYMBOL)	// Interates the amount of run distance from the player on the x - axis forward (which is down)
						{						
							emptySpace = false;						// If all those space leading upto where the player wishes to be aren't ground, you've hit an obstacle and can't move there
						}			
					}	
					
					if(emptySpace)		// If all the spaces leading upto where you wish to run are free (you've run through ground space) then you can move there							
					{
						map[Player.xCo][Player.yCo] = GROUND_SYMBOL;			// Sets the current player position character to '.' (ground)
						map[Player.xCo + i -1][Player.yCo] = PLAYER_SYMBOL;		// Sets the new position in the array to the player character
						Player.xCo = Player.xCo + i -1;							// Sets the characters x position also to that position (because you only moved on the x-axis)
						
						System.out.println("You ran: " + runDist + " on the X-Axis");	// Prints out the distance you ran
						displayMap(map);											// Prints the map to screen
					}					
				}
				catch(ArrayIndexOutOfBoundsException e)			// Catching the character array bounds
				{			
					System.out.println("You can't run there because of a wall or enemy try again...");
				}
			}
			else		// They have chosen to go by the X - Axis but backwards, All the code below is for the reverse function of the above code
			{
				try
				{
					for(i = 0; i <= runDist; ++i)
					{
						if(map[Player.xCo - i][Player.yCo] != '.' && map[Player.xCo - i][Player.yCo] != PLAYER_SYMBOL)
						{						
							emptySpace = false;
						}			
					}	
					
					if(emptySpace)
					{
						map[Player.xCo][Player.yCo] = GROUND_SYMBOL;		
						map[Player.xCo - i +1][Player.yCo] = PLAYER_SYMBOL;
						Player.xCo = Player.xCo - i + 1;			
						
						System.out.println("You ran: " + runDist + " on the X-Axis");
						displayMap(map);							
					}					
				}
				catch(ArrayIndexOutOfBoundsException e)
				{		
					System.out.println("You can't run there because of a wall or enemy try again...");
				}		
				
			} // Backwards Forward is false so backwards is true They have chosen to go by the X - Axis but backwards, All the code below is for the reverse function of the above code
			
		}	// End of the X Choice for moving forward and back below begins the Y - Choice
		
		else 
		{
			System.out.print("How many spaces do you wish to run on the Y-Axis: ");	
			runDist = console.nextInt();
			
			runDist = Math.abs(runDist); 
			
			if(forward)
			{				
				try
				{					
					for(i = 0; i <= runDist; ++i)
					{
						if(map[Player.xCo][Player.yCo + i] != '.' && map[Player.xCo][Player.yCo + i] != PLAYER_SYMBOL)
						{
							emptySpace = false;
						}											
					}	
					
					if(emptySpace)
					{
						map[Player.xCo][Player.yCo] = GROUND_SYMBOL;		
						map[Player.xCo][Player.yCo + i -1] = PLAYER_SYMBOL;
						Player.yCo = Player.yCo + i -1;	
						
						displayMap(map);						
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("You can't run there because of a wall or enemy try again...");
				}	
			}// forward
			else
			{
				try
				{					
					for(i = 0; i <= runDist; ++i)
					{
						if(map[Player.xCo][Player.yCo - i] != '.' && map[Player.xCo][Player.yCo - i] != PLAYER_SYMBOL)
						{
							emptySpace = false;
						}											
					}	
					
					if(emptySpace)
					{
						map[Player.xCo][Player.yCo] = GROUND_SYMBOL;		
						map[Player.xCo][Player.yCo - i + 1] = PLAYER_SYMBOL;
						Player.yCo = Player.yCo - i + 1;	
						
						displayMap(map);						
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("You can't run there because of a wall or enemy try again...");
				}				
				
			} // Backwards Forward is false so backwards is true
			
		}	// This entire else statement is exactly the same as the above X - Axis code but instead using the Y Axis 
			
		if(!emptySpace)
		{
			System.out.println("You can't run there because of a wall or enemy try again...");
		}	// Catches if the player runs into a wall or enemy, Used as a second catch if you failed
	
		
	}// Run Method finished
	
	
	public static void nameCharacter()
	{
		System.out.print("\nMy hero name will be: ");
		n = console.next();								// Prompts the user name and takes the name as string called n
		
		player = new Player(n,minStamina,maxStamina,minDamage,maxDamage,minHealth,maxHealth);	// passed the name and predefined stats to the player object to create the player
		
		System.out.println("He decided " + n + " was an adaquate name.");					// Prints the second welcome message

	}	// Name your character and creates the player object 
	
	public static void displayMap(char[][]map)
	{
		int i,j;
		String str = "";
		
		System.out.println("\n	             Y AXIS\n");
		System.out.println("0 1 2 3 4 5 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5");		// Headers
		
		for(i = 0; i < 15; ++i)
		{
				
			for(j = 0; j < 25; ++j)
			{
				System.out.print(map[i][j] + " ");		// Prints the corresponding character and spaces
			}

			if(i == 7)
			{
				str = "	 X AXIS";						// Prints the Y Header
			}
			
			System.out.println(i + str);				// Prints the numbers on the sides
			
			str = "";									// Sets string back to an empty space
		}		
		
		System.out.println();					
	}	// Displays the map array			Pretty straight forward
	
	public static void populateMonster()
	{
		int x = 0;
		int y = 0;
		int count = 0;
		
		while(count < MON_MAX)					// While 0 - 6 monsters (7) havn't been created keep doing this
		{
			x = rand.nextInt(X_AXIS);
			
			while(x < SPAWN_PROTECT)
			{
				x = rand.nextInt(X_AXIS);
			}									// Gets a random x position not within the spawn range
			
			y = rand.nextInt(Y_AXIS);
			
			while(y < SPAWN_PROTECT)
			{
				y = rand.nextInt(Y_AXIS);
			}									// Gets a random y position not within the spawn range
			
			try
			{
				if(map[x][y] == '.')	// Checks if the space is equal to a ground space (not a wall,player or other monster) if it's empty it sets the monster there
				{
					map[x][y] = MONSTER_SYMBOL;	// Places the monster symbol over the empty space symbol
					
					monsters[count] = new Monster(x,y,sMinMon,sMaxMon,dMinMon,dMaxMon,hMinMon,hMaxMon); // Creates a monster object
			
					++count;					// counts that a monster was created
				}						
			}												
			catch(ArrayIndexOutOfBoundsException e) 
			{}					// Catches any array exceptions
			
		}	// End of the while loop			
		
	}			// Finds 7 unique places to put the monsets
	
	public static void displayCords()
	{
		System.out.println("Current Co-ordinates are X: " + player.getPlayerX() + " Y: " + player.getPlayerY());		
	}	// Displays the players co-ordinates
	
	public static void placePlayer(int x,int y,char[][] map)
	{
		Player.xCo = x;
		Player.yCo = x;					// Sets the players x and y co-ordinates of the player
		
		map[x][y] = PLAYER_SYMBOL;		
	}	// places the player symbol at these co-ordinates at the beginning of the game and assigns the x,y values to the player
	
	public static void checkIfClose(int x,int y,char[][] map,char sym)
	{
		monsterBesideYou = false;
		
		try
		{					
			if(map[x - 1][y] == sym)
			{
				if(sym == MONSTER_SYMBOL )
				{
					monsterBesideYou = true;
					battleTime(map,x - 1,y);				// Important line, Passes the M (x,y) characters position (where a monster object previously was assigned to) to the battleTime method
				}
			}			
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
		
		// Checks if there is a monster symbol above the character
		
		try
		{					
			if(map[x + 1][y] == sym)
			{
				if(sym == MONSTER_SYMBOL )
				{
					monsterBesideYou = true;
					battleTime(map,x + 1,y);				// Important line, Passes the M (x,y) characters position (where a monster object previously was assigned to) to the battleTime method
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
		
		// Checks if there is a monster symbol below the character
		
		try
		{					
			if(map[x][y - 1] == sym)
			{
				if(sym == MONSTER_SYMBOL )
				{
					monsterBesideYou = true;
					battleTime(map,x,y - 1);				// Important line, Passes the M (x,y) characters position (where a monster object previously was assigned to) to the battleTime method
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
		
		// Checks if there is a monster symbol left of the character
		
		try
		{					
			if(map[x][y + 1] == sym)
			{
				if(sym == MONSTER_SYMBOL )
				{
					monsterBesideYou = true;
					battleTime(map,x,y + 1);				// Important line, Passes the M (x,y) characters position (where a monster object previously was assigned to) to the battleTime method
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
		
		// Checks if there is a monster symbol right of the character	
	}
	
	public static boolean isSomeoneThere()
	{
		return monsterBesideYou;
	}	// returns if there is a monster with in range
	
	public static void prntMap(char [][]map) throws FileNotFoundException
	{
		int i,j;
		PrintWriter outMapPrint = new PrintWriter("MapCopy.txt");		// Creates a print writer
		
		for(i = 0; i < 15; ++i)
		{
			for(j = 0; j < 25; ++j)
			{
				outMapPrint.printf(map[i][j] + " ");
			}
			
			outMapPrint.printf("%n");
		}									// Iterates through the array and prints it to a file, nicely formatted too.
		
		System.out.println("Map printed to MapCopy.txt");
		
		outMapPrint.close();	// Closes the PrintWriter stream
	}							// Prints the character map to a file
	
	public static void prntStats() throws FileNotFoundException
	{
		PrintWriter outStatPrint = new PrintWriter("PlayerStats.txt");
		
		outStatPrint.printf("Player Name  : " + Player.name + "%n");
		outStatPrint.printf("Player Health: " + player.health + "%n");
		outStatPrint.printf("Player Stamina: " + player.baseStamina + "%n");
		outStatPrint.printf("Player Damage: " +player.baseDamage + "%n");		
		outStatPrint.printf("Player Gold: " + Player.gold + "%n");
		outStatPrint.printf("Player xCo: " + Player.xCo + "%n");
		outStatPrint.printf("Player yCo: " + Player.yCo + "%n");
		outStatPrint.printf("Player Score: " +  playerScore + "%n");
		
		outStatPrint.close();
		
		System.out.println("Player stats printed to PlayerStats.txt");
	}	// Prints all the player stats to a text file linearly using the same technique as above.
	
	public static void battleTime(char [][]map,int a,int b)				// Reads in the M characters position (x,y) along with the map
	{
		fightHappening = true;											// Sets that a fight is happening for the fight loop
		
		for(Monster mons : monsters)									// Iterates foreach of the monsters in the monster array
		{						
			if(mons.xCo == a && mons.yCo == b && mons.monsterAlive) 	// if the M characters position read in matches the same position as one of the monster objects read in HE IS THAT MONSTER :O
			{
				currentMonster = mons;									// Stores a reference to the monster matching the M char's position
				currentMonsterImage = currentMonster.monsterImg;		// sets the current monster image to the matched current monster object
			}			
		}				
		
		System.out.println("The M character has seen you! You must fight him.");
		yarn.battleHelp();															// Warns you about entering the fight loop 
		
		while(fightHappening)														// You are forced to fight until you either live or die from the fight
		{
			String decide = "";														// Takes the players input in the form of a string
			int shieldOrFight = 0;
			
			System.out.print("\nChoose (Fi)ght,(Sh)ield, (Re)st or (Ba)ttlehelp : ");
			decide = console.next();												// Prompts input from user and takes it
			
			System.out.println("\nThe " + currentMonster.monsterName + " " + yarn.monsterFightDiscription() + " you!");	// Outputs a random attack string !
			
			if(decide.equalsIgnoreCase("Fi"))										// If the player chooses to fight....
			{			
				if(player.getStamina() >= TURN_COST)									// Makes sure the player has more than 2 stamina to make an attack
				{	
					currentMonster.updateHealth(player.getDamage());			// If the player has enough stamina, he attacks the monster and the monster loses the players damage amount
					shieldOrFight = -2; // Assigns 2 stamina to be lost to the player for fighting
					System.out.println("\nYou do " + player.baseDamage + " damage to the monster!");
				}
				else
				{
					System.out.println("You are too tired to attack... wait until your stamina is more than 2 to attack.");
				}	// if the player doesn't have enough stamina he is told to wait 
				
				if(currentMonster.getStamina() >= TURN_COST)							
				{
					player.updateHealth(currentMonster.getDamage());	
					currentMonster.updateStamina(STAM_POINT);				// takes 2 stamina points off the monster (who will always attack)
					System.out.println("\nThe monster does " + currentMonster.getDamage() + " to you!");
				}															// If the monster has enough stamina to attack the player loses the monsters damage amount																
				else
				{
					System.out.println("Monster is too tired to attack");
				}														// If the monster doesn't have enough stamina just say the monster is tired until he saves up enough						
			
			}	// If fight chosen, decrements 2 Stamina from the player and
			
			else if(decide.equalsIgnoreCase("Sh"))
			{
				if(currentMonster.getStamina() > TURN_COST)
				{
					player.updateHealth(currentMonster.getDamage() - TURN_COST);
					shieldOrFight = -1; 													// sets the player to only lose 1 stamina point
				}		
				
				System.out.println("\nYou shield and take only " + (currentMonster.getDamage() - TURN_COST) + " damage instead of " + currentMonster.getDamage());
			}	// Lets the player take less damage in one turn by 2 points instead of the full damage
			
			else if(decide.equalsIgnoreCase("Re"))
			{
				player.updateHealth(currentMonster.getDamage());
				System.out.println("\nThe monster does " + currentMonster.getDamage() + " to you!");
				System.out.println("You rest for 1 turn and regain 2 stamina");
				shieldOrFight = 2;																					// Rests and gain 2 stamina points but..
			}
			
			else if(decide.equalsIgnoreCase("Ba"))
			{
				yarn.battleHelp();
			}							// Displays the battle text options			
			
			player.updateStamina(shieldOrFight);				// takes 1 or 2 stamina points off the player depending on his action					
			
			fightHappening = fightChecker();	// checks (with the fight checker) if the monster is still alive or not
			
		}		
	}	// The battle system!!
	
	public static boolean fightChecker()
	{
		boolean temp = true;
		
		if(player.getHealth() <= 0)
		{
			System.out.println("Player died... try again...");
			System.out.println("Game is closing now...");
			
			System.exit(0);
		}						// Checks if the players health is less than 0, if it is... player dies, game ends, lose text displays.		
		
		if(currentMonster.getHealth() <= 0)			// Checks the monsters health is more than or equal to 0 
		{
			currentMonster.killMonster();			// If it is.. the monsters dead... so kill the monster

			GameShell.noMonster();					// Clear the JPanel monster stats off and the monster image and battle text
			battleJustHappened = true;				// A battle just happened (this is used to send an extra clean up clause to the JPanel)
			
			System.out.println("\nYou destroyed the " + currentMonster.monsterName + " !");		// Print out that you killed the monster
			
			++monsterCount;											// Increment the amount of monsters you killed
			
			if(monsterCount >= MON_MAX)								
			{
				yarn.win();
							
				System.exit(0);
			}														// Checks if you killed all the monsters, if you have, shows the win text and closes the JFrame.
			
			player.addGold(currentMonster.gold);					// Adds the monster gold from the monster to the player
			displayMap(map);
			
			playerScore += Player.gold * SCORE_MULT;
			
			return false;
		}		
		
		return temp;												// Returns if there is a monster nearb
	}		

}// End of the RunMe Class