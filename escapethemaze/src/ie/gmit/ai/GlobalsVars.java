package ie.gmit.ai;

import java.util.Random;

import ie.gmit.entity.MazeEntity;
import ie.gmit.entity.Player;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class GlobalsVars
{
	static Random rand = new Random();
	
	// Global Variables

	public static final int drawCols = 5;
	public static final int drawRows = 4;
	
	public static final String title = "AI Project 0.1";
	public static final int MAZE_DIMENSION = 60;
	public static final int DEFAULT_VIEW_SIZE = 800;
	public static final int INFO_PANEL_SIZE = DEFAULT_VIEW_SIZE - (GlobalsVars.ImageSize * GlobalsVars.drawRows);
	public static final int ImageSize = 160;

	public static boolean MUSIC_PLAYING = true;
	
	public static boolean HELP_SCREEN = true;
	public static boolean ZOOM_OUT = false;
	public static boolean WON_GAME = false; 
	
	public static double SMALL_IMAGE_SIZE_WIDTH = (double)GlobalsVars.DEFAULT_VIEW_SIZE / (double)GlobalsVars.MAZE_DIMENSION;
	public static double SMALL_IMAGE_SIZE_HEIGHT = (double)(GlobalsVars.DEFAULT_VIEW_SIZE - (double)GlobalsVars.INFO_PANEL_SIZE) / (double)GlobalsVars.MAZE_DIMENSION;
	
	public static int WEAPON_SPAWN_COUNT = 20;
	public static int FOOD_SPAWN_COUNT = 20;
	public static int CAGE_SPAWN_COUNT = 15;
	
	public static int TIMER_SPEED = 200;
	
	public static int HINTS_MAX_TIME = 5000;
	public static int HINTS_TIME = 0;
	
	// Global Player Variables

	public static final int PLAYER_BASE_HEALTH = 60;
	
	public static int playerPositionX;
	public static int playerPositionZ;	
	public static Player player;

	public static int TurnCount = 0;
	
	public static MazeEntity GoalNode;
	
	// Player Tracking

	public static long startTime = 0;
	
	public static String currentTime()
	{
		return ("Time: " + (System.currentTimeMillis() - startTime) / 1000);		
	}
	
	// Timer
	
	public static void firstPositionPlayer(int x,int z, Maze maze)
	{	
		player.setX(x);
		player.setZ(z);
		playerPositionX = x;
		playerPositionZ = z;		
		maze.getMazeEntity(x, z).setTilepiece(player);
	}
	
	public static void updatePlayer(int oldX,int oldZ,int x,int z, Maze maze)
	{	
		TilePiece floor = new TilePiece(TileType.FLOOR, oldX, oldZ);

		player.setX(x);
		player.setZ(z);
		playerPositionX = x;
		playerPositionZ = z;	
		maze.getMazeEntity(oldX, oldZ).setTilepiece(floor);
		maze.getMazeEntity(x, z).setTilepiece(player);
	}
	
	public static void toggleZoom()
	{
		ZOOM_OUT = !ZOOM_OUT;		
	}
	
	public static void toggleHelp()
	{
		HELP_SCREEN = !HELP_SCREEN;		
	}
	
	// Random Numbers
	
	public static int RandomNumber(int max)
	{
		return rand.nextInt(max);
	}	
	
	// Singleton Class Stuff
	
	private static GlobalsVars instance = null;
	
	protected GlobalsVars()	{}
   
    public static GlobalsVars getInstance() 
    {
       if(instance == null) 
       {
          instance = new GlobalsVars();
       }
       
       return instance;
   }	
}
