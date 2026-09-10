package ie.gmit.maze;

public class MazeGeneratorFactory {
	private static MazeGeneratorFactory mgf = new MazeGeneratorFactory();
	
	private MazeGeneratorFactory(){		
	}
	
	public static MazeGeneratorFactory getInstance(){
		return mgf;
	}
	
	public MazeGenerator getMazeGenerator(GeneratorAlgorithm algorithm)
	{
		if (algorithm == GeneratorAlgorithm.BinaryTree)
		{
			return new BinaryTreeMazeGenerator();			
		}
		
		return null;
	}	
}