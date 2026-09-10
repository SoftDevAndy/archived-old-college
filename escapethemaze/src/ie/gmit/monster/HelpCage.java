package ie.gmit.monster;

import java.util.Random;

import ie.gmit.ai.GameRunner;
import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;
import ie.gmit.traverser.AStarTraversator;
import ie.gmit.traverser.BeamTraversator;
import ie.gmit.traverser.BestFirstTraversator;
import ie.gmit.traverser.Traversator;

public class HelpCage extends TilePiece {

	private Traversator t;
	
	public HelpCage(TileType tileType, int x, int z)
	{
		super(tileType, x, z);

		Random rand = new Random();
		int r = rand.nextInt(3);
		
		t = new BeamTraversator(GlobalsVars.GoalNode, 2,false);
		
		switch(r)
		{
			case 0: 
				t = new BeamTraversator(GlobalsVars.GoalNode, 2,false);
			case 1: 
				t = new  AStarTraversator(GlobalsVars.GoalNode,false);
			case 2: 
				t = new BestFirstTraversator(GlobalsVars.GoalNode,false);
		}
	}
	
	public void ShowPathToDoor(Maze maze)
	{		
		GlobalsVars.HINTS_TIME = 0;
		
		t.traverse(maze, maze.getMazeEntity(getX(),getZ()));
	}

}
