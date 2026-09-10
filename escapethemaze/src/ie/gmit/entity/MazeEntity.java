package ie.gmit.entity;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.axis.SymbolicTickUnit;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.maze.Direction;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class MazeEntity {
	
	private TilePiece tilePiece;
	private int x,z;
	boolean isWall = false;
	boolean isGoalNode = false;
	boolean isVisted = false;
	private int distance;
	private Direction[] paths = null;

	private MazeEntity parent;	
	
	private int vistedNumber = -1;
	
	public MazeEntity(int x,int z, TilePiece tile)
	{
		this.x = x;
		this.z = z;
		this.tilePiece = tile;
	}

	public int getHeuristic(MazeEntity goal){
		double x1 = this.x;
		double y1 = this.z;
		double x2 = goal.getX();
		double y2 = goal.getZ();
		return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	public boolean hasDirection(Direction direction)
	{	
		if(paths == null)
			return false;
		
		for (int i = 0; i < paths.length; i++) 
		{
			if (paths[i] == direction)
			{
				return true;
			}
		}
		
		return false;
	}
	public MazeEntity[] children(Maze maze)
	{		
		List<MazeEntity> children = new ArrayList<MazeEntity>();
		
		if (x > 0 && maze.getMazeEntity(x,z).hasDirection(Direction.North))
		{
			try
			{
				if(!maze.getMazeEntity(x - 1,z).isWall())
					children.add(maze.getMazeEntity(x - 1,z));				
			}
			catch(Exception e){	}

		}
		
		if (x > 0 && maze.getMazeEntity(x,z).hasDirection(Direction.South))
		{
			try
			{
				if(!maze.getMazeEntity(x + 1,z).isWall())
					children.add(maze.getMazeEntity(x + 1,z));				
			}
			catch(Exception e){	}

		}
		
		if (x > 0 && maze.getMazeEntity(x,z).hasDirection(Direction.West))
		{	
			try
			{
				if(!maze.getMazeEntity(x,z - 1).isWall())
					children.add(maze.getMazeEntity(x,z - 1));				
			}
			catch(Exception e){	}

		}
		
		if (x > 0 && maze.getMazeEntity(x,z).hasDirection(Direction.East))
		{
			try
			{
				if(!maze.getMazeEntity(x,z - 1).isWall())
					children.add(maze.getMazeEntity(x,z + 1));				
			}
			catch(Exception e){	}

		}
		
		return (MazeEntity[]) children.toArray(new MazeEntity[children.size()]);
	}
	
	public void addPath(Direction direction) 
	{
		int index = 0;
		
		if (paths == null)
		{
			paths = new Direction[index + 1];		
		}
		else
		{
			index = paths.length;
			Direction[] temp = new Direction[index + 1];
			for (int i = 0; i < paths.length; i++) 
				temp[i] = paths[i];
			paths = temp;
		}
		
		paths[index] = direction;
	}
	
	public void getPathsString()
	{
		System.out.println("---");
		for (int i = 0; i < paths.length; i++) 
		{
			System.out.println(paths[i]);
		}
	}
	
	public MazeEntity[] adjacentNodes(Maze maze)
	{
		List<MazeEntity> adjacents = new ArrayList<MazeEntity>();
		
		if (x > 0) 
		{
			adjacents.add(maze.getMazeEntity(x - 1,z)); //Add North
		}
		
		
		if (x < maze.getMaze().length - 1) 
		{
			adjacents.add(maze.getMazeEntity(x + 1,z)); //Add South
		}
		
		
		if (z > 0)
		{
			adjacents.add(maze.getMazeEntity(x,z - 1)); //Add West
		}
		
		
		if (z < maze.getMaze()[x].length - 1) 
		{
			adjacents.add(maze.getMazeEntity(x,z + 1)); //Add East
		}		
		
		return (MazeEntity[]) adjacents.toArray(new MazeEntity[adjacents.size()]);
	}	
	
	public MazeEntity getParent() 
	{
		return this.parent;
	}

	public void setParent(MazeEntity parent) 
	{
		this.parent = parent;
	}
	
	public boolean containsType(TileType tileType)
	{
		if(tilePiece.getTileType() == tileType)
			return true;
		
		return false;
	}
	
	public void setTilepiece(TilePiece piece)
	{
		this.tilePiece = piece;
	}
	
	public boolean isGoalNode()
	{
		return this.isGoalNode;
	}
	
	public void setGoalNode(boolean flag)
	{
		GlobalsVars.GoalNode = this;
		this.isGoalNode = flag;
	}
	
	public void setVisited(boolean visted)
	{
		this.isVisted = visted;
	}
	
	public void visitedNode()
	{
		isVisted = true;
	}
	
	public boolean isVisited()
	{
		return this.isVisted;
	}
	
	public TilePiece getTilePiece()
	{
		return this.tilePiece;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getZ()
	{
		return this.z;
	}
		
	public Direction[] getPaths() {
		return paths;
	}
	
	public void setWall(boolean wallType)
	{
		this.isWall = wallType;
	}
	
	public boolean isWall()
	{
		return this.isWall;
	}
	
	public void setPathCost(int distance) {
		this.distance = distance;
	}
	
	public int getPathCost() 
	{
		return this.distance;
	}

	public int getVistedNumber() {
		return vistedNumber;
	}

	public void setVistedNumber(int vistedNumber) {
		this.vistedNumber = vistedNumber;
	}
}
