package ie.gmit.ai;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import ie.gmit.entity.MazeEntity;
import ie.gmit.entity.Player;
import ie.gmit.maze.GeneratorAlgorithm;
import ie.gmit.monster.HelpCage;
import ie.gmit.monster.Skeleton;
import ie.gmit.tile.TileType;

public class GameRunner implements KeyListener
{
	private static Maze maze;
	private static ArrayList<MazeEntity> monstersBtt = new ArrayList<MazeEntity>();
	private GameView view;
	private SoundManager soundManager;
	
	public GameRunner(GeneratorAlgorithm algorithm) throws Exception
	{
		soundManager = new SoundManager();
		
		maze = new Maze(algorithm,GlobalsVars.MAZE_DIMENSION, GlobalsVars.MAZE_DIMENSION);

    	view = new GameView(maze);
    	
    	placePlayer();
    	
    	//System.out.println(maze.printMaze());
    	
    	setupApplication();
    	
		GlobalsVars.startTime = System.currentTimeMillis();	
		
		CreateMonster();
		
		MonsterManager monster = new MonsterManager();
		monster.setMonsterManager(monstersBtt);
		monster.start();
	}
	
	private void CreateMonster()
	{
		int row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		int col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);		
		int count = 0;
		int tooLong = 0;
		final int LIMIT = 200;		
		
		while(count < 3 && tooLong < LIMIT)
		{		
			while(maze.getMazeEntity(row, col).isWall())
			{
				row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
				col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			}		
			
			Skeleton skele = new Skeleton(TileType.ENEMY, GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION), GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION));
			
			maze.getMaze()[row][col] = new MazeEntity(row, col, skele);
			maze.getMazeEntity(row, col).setTilepiece(skele);
			maze.getMazeEntity(row, col).setWall(false);
						
			monstersBtt.add(maze.getMazeEntity(row, col));
						
			row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			
			++count;
		}
		
	}
	
	
	private void setupApplication()
	{
    	Dimension d = new Dimension(GlobalsVars.DEFAULT_VIEW_SIZE, GlobalsVars.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame(GlobalsVars.title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
	}
	
	private void placePlayer()
	{   	
		int row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		int col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		
		while(maze.getMazeEntity(row, col).isWall())
		{
			row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		}
		
		GlobalsVars.player = new Player(GlobalsVars.PLAYER_BASE_HEALTH,row,col);
		
		GlobalsVars.firstPositionPlayer(row,col, maze);
	}
	
	public void playMusic()
	{
		soundManager.playMusic();	
	}

    public void keyPressed(KeyEvent e) 
    { 
    	view.repaint();
    	
        if(!GlobalsVars.HELP_SCREEN)
        {    	        
	        if (e.getKeyCode() == KeyEvent.VK_Z)
		    {
		    	GlobalsVars.toggleZoom();
		    }		    	
	        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D && GlobalsVars.playerPositionZ < GlobalsVars.MAZE_DIMENSION - 1) 
	        {    		
	    		if(isExit(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1))
	    			GlobalsVars.WON_GAME = true;
	    		
	    		if(isHelp(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1))
	    		{
	    			HelpCage help = (HelpCage) maze.getMazeEntity(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1).getTilePiece();
	    			help.ShowPathToDoor(maze);
	    		}		    			
	    			
	        	if (isValidMove(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1))
	        	{
	        		if (isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1) == TileType.FOOD)
	        		{
	        			GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ + 1].getTilePiece());
	        		}
	        		else if(isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1) == TileType.WEAPON)
	        		{
	        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ + 1].getTilePiece());
	        		}
	        		
	        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1, maze);  		
	        	        	        		
	        		if(GlobalsVars.TurnCount % 20 == 0)
	        		{
	        			GlobalsVars.player.feelHunger();
	        		}
	        		
	        		GlobalsVars.TurnCount++;
	        	}
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A && GlobalsVars.playerPositionZ > 0)
	        {         		
	    		if(isExit(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1))
	    			GlobalsVars.WON_GAME = true;
	    		
	    		if(isHelp(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1))
	    		{
	    			HelpCage help = (HelpCage) maze.getMazeEntity(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1).getTilePiece();
	    			help.ShowPathToDoor(maze);
	    		}	
	        	
	        	if (isValidMove(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1))
	        	{
	            	if(isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1) == TileType.FOOD)
	            	{
	            		GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ - 1].getTilePiece());
	            	}
	        		else if(isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1) == TileType.WEAPON)
	        		{
	        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ - 1].getTilePiece());
	        		}				
	        		
	        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1, maze);  		
		        	        		
	        		if(GlobalsVars.TurnCount % 20 == 0)
	        		{
	        			GlobalsVars.player.feelHunger();
	        		}
	        		
	        		GlobalsVars.TurnCount++;
	        	}
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W && GlobalsVars.playerPositionX > 0)
	        {     
	    		if(isExit(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ))
	    			GlobalsVars.WON_GAME = true;
	    		
	    		if(isHelp(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ))
	    		{
	    			HelpCage help = (HelpCage) maze.getMazeEntity(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ).getTilePiece();
	    			help.ShowPathToDoor(maze);
	    		}	
	        	
	        	if (isValidMove(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ)) 
	        	{
	            	if(isSomethingPickupable(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ) == TileType.FOOD)
	            	{
	            		GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX - 1][GlobalsVars.playerPositionZ].getTilePiece());
	            	}
	        		else if(isSomethingPickupable(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ) == TileType.WEAPON)
	        		{
	        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX - 1][GlobalsVars.playerPositionZ].getTilePiece());
	        		}	
	        		
	        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ, maze);
	        		        		
	        		if(GlobalsVars.TurnCount % 20 == 0)
	        		{
	        			GlobalsVars.player.feelHunger();
	        		}      		
	       		
	        		GlobalsVars.TurnCount++;
	        	}
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S && GlobalsVars.playerPositionX < GlobalsVars.MAZE_DIMENSION  - 1) 
	        {        
	    		if(isExit(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ))
	    			GlobalsVars.WON_GAME = true;
	    		
	    		if(isHelp(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ))
	    		{
	    			HelpCage help = (HelpCage) maze.getMazeEntity(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ).getTilePiece();
	    			help.ShowPathToDoor(maze);
	    		}
	        	
	        	if (isValidMove(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ)) 
	        	{
	            	if(isSomethingPickupable(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ) == TileType.FOOD) 
	            	{
	            		GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX + 1][GlobalsVars.playerPositionZ].getTilePiece());
	            	}
	        		else if(isSomethingPickupable(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ) == TileType.WEAPON)
	        		{
	        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX + 1][GlobalsVars.playerPositionZ].getTilePiece());
	        		}	
	        		
	        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ, maze);
	        		        		
	        		if(GlobalsVars.TurnCount % 20 == 0)
	        		{
	        			GlobalsVars.player.feelHunger();
	        		}        		
	        		
	        		GlobalsVars.TurnCount++;
	        	}
	        }
	        else if(e.getKeyCode() == KeyEvent.VK_E)
	        {
	        	GlobalsVars.player.eatCurrentFood();
	        }
	        else if(e.getKeyCode() == KeyEvent.VK_SLASH) 
	        {
	        	GlobalsVars.player.cycleFood();
	        }
	        else if(e.getKeyCode() == KeyEvent.VK_PERIOD) 
	        {
	        	GlobalsVars.player.cycleWeapons();
	        }  
        }  	
        
        if(e.getKeyCode() == KeyEvent.VK_H) 
        {
        	GlobalsVars.toggleHelp();
        	view.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_M)
        {
        	soundManager.playMusic();
        }
    }
    
    private boolean isHelp(int x,int z)
    {
    	try
    	{
	    	if(maze.getMazeEntity(x, z).getTilePiece().getTileType() == TileType.HELP)
	    		return true;
	    	else 
	    		return false;
    	}
    	catch(Exception e){e.toString();};
    	
    	return false;
    }
    
	private boolean isValidMove(int r, int c)
	{
		try
		{
		
			if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && !maze.getMaze()[r][c].isWall())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception eCcC)
		{
			return false;
		}
	}
	
	private boolean isExit(int r,int c)
	{
		try
		{		
			if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && maze.getMaze()[r][c].isGoalNode())
			{
				GlobalsVars.TIMER_SPEED = 1;
				return true;
			}
			else
				return false;
		}catch(Exception e ){e.toString();}
		
		return false;
	}
	
	private TileType isSomethingPickupable(int r, int c)
	{
		if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && maze.getMaze()[r][c].containsType(TileType.FOOD))
		{
			return TileType.FOOD;
		}
		else if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && maze.getMaze()[r][c].containsType(TileType.WEAPON))
		{
			return TileType.WEAPON;
		}
		else
		{
			return TileType.DEBUG;
		}
	}
	
	public static Maze getMaze()
	{
		return maze;
	}
    
    public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}