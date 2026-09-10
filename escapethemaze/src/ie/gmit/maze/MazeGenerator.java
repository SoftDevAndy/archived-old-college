package ie.gmit.maze;

import ie.gmit.entity.MazeEntity;

public interface MazeGenerator {	
	public abstract MazeEntity[][] generateMaze(MazeEntity[][] maze);
}