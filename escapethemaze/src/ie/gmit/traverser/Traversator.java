package ie.gmit.traverser;

import java.util.ArrayList;
import java.util.HashMap;

import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;

public interface Traversator {
	public HashMap traverse(Maze maze, MazeEntity start);
}
