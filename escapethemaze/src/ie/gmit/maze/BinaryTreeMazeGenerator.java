package ie.gmit.maze;

import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class BinaryTreeMazeGenerator implements MazeGenerator
{		
	public MazeEntity[][] generateMaze(MazeEntity[][] maze)
	{
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze.length; col++)
			{
				maze[row][col] = new MazeEntity(row, col, new TilePiece(TileType.WALL, row, col));
				maze[row][col].setWall(true);		
			}
		}
		
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze.length; col++)
			{
				int num = (int) (Math.random() * 10);
				
				if (col > 0 && (row == 0 || num >= 5))
				{
					try
					{
						maze[row][col - 1] = new MazeEntity(row, col, new TilePiece(TileType.FLOOR, row, col));
						maze[row][col - 1].setWall(false);					
					}
					catch(Exception e){e.toString();}
				}
				else
				{
					try
					{
						maze[row - 1][col] = new MazeEntity(row, col, new TilePiece(TileType.FLOOR, row, col));
						maze[row - 1][col].setWall(false);				
					}
					catch(Exception e){e.toString();}
				}	
			}
		}
		
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze.length; col++)
			{				
				try
				{
					if(!maze[row - 1][col].isWall())
						maze[row][col].addPath(Direction.North);						
						
				}catch(Exception e){e.toString();};
				try
				{
					if(!maze[row + 1][col].isWall())
						maze[row][col].addPath(Direction.South);	
					
				}catch(Exception e){e.toString();};
				try
				{
					if(!maze[row][col - 1].isWall())
						maze[row][col].addPath(Direction.West);	
				}catch(Exception e){e.toString();};
				try
				{
					if(!maze[row][col + 1].isWall())
						maze[row][col].addPath(Direction.East);	
				}catch(Exception e){e.toString();};
			}
		}
		
		return maze;
	}
}