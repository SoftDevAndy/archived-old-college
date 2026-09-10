package ie.gmit.ai;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.entity.MazeEntity;
import ie.gmit.monster.Skeleton;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class MonsterManager extends Thread implements Runnable{
	
	List<MazeEntity> allMonster;
	
	public void setMonsterManager(ArrayList<MazeEntity> monster)
	{		
		allMonster = monster;
	}
	
	public void run()
	{
		/*
		while(GlobalsVars.player.isAlive())
		{		
			for(MazeEntity mons : allMonster)
			{
				System.out.println("Moving");
				Skeleton temp = (Skeleton)mons.getTilePiece();
				temp.move(GameRunner.getMaze(),mons.getX(),mons.getZ());
			}
			
			try 
			{
				Thread.sleep(6000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}		
		}
		*/
	}
}
 