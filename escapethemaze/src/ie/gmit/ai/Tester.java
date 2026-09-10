package ie.gmit.ai;

import ie.gmit.maze.GeneratorAlgorithm;

public class Tester
{
	public static void main(String[] args)
	{		
		try 
		{
			new GameRunner(GeneratorAlgorithm.BinaryTree);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
}
