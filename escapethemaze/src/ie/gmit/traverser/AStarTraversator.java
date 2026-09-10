package ie.gmit.traverser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import ie.gmit.ai.GameRunner;
import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class AStarTraversator implements Traversator
{
	private MazeEntity goal;
	
	public AStarTraversator(MazeEntity goal,boolean findPlayer)
	{		
		if(!findPlayer)
			this.goal = goal;
		else
			this.goal = GameRunner.getMaze().getMazeEntity(GlobalsVars.player.getX(),GlobalsVars.player.getZ());
		
	}// constructor
	
	public HashMap traverse(Maze maze, MazeEntity node) 
	{    			
		PriorityQueue<MazeEntity> open = new PriorityQueue<MazeEntity>(20, (MazeEntity current, MazeEntity next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
		List<MazeEntity> closed = new ArrayList<MazeEntity>();
    	
		HashMap<Integer,MazeEntity> vistedHp = new HashMap<Integer,MazeEntity>();
		int visitedCount = 0;
		
		open.offer(node);
		node.setPathCost(0);
		
		while(!open.isEmpty())
		{
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			
			node.setVistedNumber(visitedCount);
			visitedCount++;
			
			if (node.isGoalNode()){
				break;
			}
						
			MazeEntity[] children = node.children(maze);
			
			for (int i = 0; i < children.length; i++) 
			{
				MazeEntity child = children[i];
				int score = node.getPathCost() + 1 + child.getHeuristic(goal);
				int existing = child.getPathCost() + child.getHeuristic(goal);
				
				if ((open.contains(child) || closed.contains(child)) && existing < score)
				{
					continue;
				}
				else
				{
					open.remove(child);
					closed.remove(child);
					child.setParent(node);
					child.setPathCost(node.getPathCost() + 1);
					open.add(child);
				}
			}									
		}

		int a = 0;
		
		while(a < closed.size())
		{		
			closed.get(a).setTilepiece(new TilePiece(TileType.HINT, closed.get(a).getX(), closed.get(a).getX()));
			closed.get(a).setWall(false);
			a++;
		}
		
		System.out.println(vistedHp.size());
		
		return vistedHp;
		
	}//traverse
	
}//class
