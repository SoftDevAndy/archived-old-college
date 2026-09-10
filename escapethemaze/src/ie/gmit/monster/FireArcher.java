package ie.gmit.monster;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import ie.gmit.ai.GameRunner;
import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;
import ie.gmit.traverser.AStarTraversator;
import ie.gmit.traverser.BeamTraversator;
import ie.gmit.traverser.BestFirstTraversator;
import ie.gmit.traverser.Traversator;

public class FireArcher extends TilePiece implements Fightable,Decideable {

	private int health;
	private boolean isDead = false;
	private int attack = 10;
	private List<BufferedImage> allFrames;
	private BufferedImage imageFrame1;
	private BufferedImage imageFrame2;
	private BufferedImage imageFrame3;
	private int imagePosition = 0;
	
	private Traversator t;
	
	public FireArcher(TileType tileType, int x, int z) {
		super(tileType, x, z);
		
		try 
		{
			imageFrame1 = ImageIO.read(new java.io.File("Images/enemy2_1.png"));
			imageFrame2 = ImageIO.read(new java.io.File("Images/enemy2_2.png"));
			imageFrame3 = ImageIO.read(new java.io.File("Images/enemy2_3.png"));
		}
		catch (IOException e){e.toString();}	
		
		allFrames = new ArrayList<BufferedImage>();
		
		if(imageFrame1 != null && imageFrame2 != null && imageFrame3 != null)
		{
			allFrames.add(imageFrame1);
			allFrames.add(imageFrame2);
			allFrames.add(imageFrame3);
		}
		
		t = randomTraverser();
	}
	
	public Traversator randomTraverser()
	{
		Random rand = new Random();
		int x = rand.nextInt(2);
		
		switch(x)
		{
			case 0: 
				return new BestFirstTraversator(GameRunner.getMaze().getMazeEntity(GlobalsVars.player.getX(),GlobalsVars.player.getZ()),true);
			case 1: 
				return new AStarTraversator(GameRunner.getMaze().getMazeEntity(GlobalsVars.player.getX(),GlobalsVars.player.getZ()),true);
			case 2: 
				return new BeamTraversator(GameRunner.getMaze().getMazeEntity(GlobalsVars.player.getX(),GlobalsVars.player.getZ()),2,true);
		}
		
		return new BeamTraversator(GlobalsVars.GoalNode, 2,true);
	}

	public void deprecateHealth(int val) {
		this.health -= val;		
		
		if(health <= 0)
			makeDead(true);
	}

	public int getAttack() {
		return this.attack;
	}

	public void makeDead(boolean flag) {
		this.isDead = flag;		
	}
	
	public int getHealth(){
		return this.health;
	}

	public boolean isDead() {
		return this.isDead;
	}
	
	public BufferedImage getTileImage()
	{		imagePosition++;
		
		if(imagePosition == allFrames.size())
			imagePosition = 0;
		
		return allFrames.get(imagePosition);
	}
	
	public void moveSentient (int oldX, int oldZ, int x, int z, Maze maze) {
		setX(x);
		setZ(z);	
		maze.getMazeEntity(oldX, oldZ).setTilepiece(new TilePiece(TileType.FLOOR, oldX, oldZ));
		maze.getMazeEntity(oldX, oldZ).setTilepiece(this);		
	}
	
	public void move(Maze maze,int x,int z)
	{
		t.traverse(maze, maze.getMazeEntity(getX(), getZ()));
	}

}
