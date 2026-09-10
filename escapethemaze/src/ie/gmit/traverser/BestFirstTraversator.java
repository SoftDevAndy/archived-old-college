package ie.gmit.traverser;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ie.gmit.ai.GameRunner;
import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class BestFirstTraversator implements Traversator{

	private MazeEntity goal;
	
	public BestFirstTraversator(MazeEntity goal,boolean findPlayer){
		
		if(!findPlayer)
			this.goal = goal;
		else
			this.goal = GameRunner.getMaze().getMazeEntity(GlobalsVars.player.getX(),GlobalsVars.player.getZ());
	}
	
	public HashMap traverse(Maze maze, MazeEntity node) {

		HashMap<Integer,MazeEntity> vistedHp = new HashMap<Integer,MazeEntity>();
		
		maze.unvistMaze();
		
		int visitedCount = 0;
		
		LinkedList<MazeEntity> queue = new LinkedList<MazeEntity>();
		queue.addFirst(node);
    	
		while(!queue.isEmpty()){
			node = queue.poll();
			node.setVisited(true);
			node.setVistedNumber(visitedCount);
			
			if (node.isGoalNode()){
				break;
			}
			
			MazeEntity[] children = node.children(maze);
			for (int i = 0; i < children.length; i++) {
				if (children[i] != null && !children[i].isVisited()){
					children[i].setParent(node);
					queue.addFirst(children[i]);
				}
			}
			
			Collections.sort(queue,(MazeEntity current, MazeEntity next) -> current.getHeuristic(goal) - next.getHeuristic(goal));		
		}
		
		for(int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++) 
        {
        	for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
        	{    
        		MazeEntity me = maze.getMazeEntity(row, col);
        		
        		if(maze.getMazeEntity(row, col).isVisited())
        		{
        			vistedHp.put(maze.getMazeEntity(row, col).getVistedNumber(),maze.getMazeEntity(row, col));
        		}
        		
        		if(maze.getMazeEntity(row, col).isVisited() && !maze.getMazeEntity(row, col).isGoalNode())
        		{        		
					me.setTilepiece(new TilePiece(TileType.HINT, me.getX(), me.getZ()));
					me.setWall(false);
        		}
        	}
		}
		
		System.out.println(vistedHp.size());
		
		return vistedHp;
	}
}
