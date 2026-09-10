package ie.gmit.sw.words;

import java.util.Random;

public enum WordType
{
	BASIC,FANCY,FUNNY;
	
	private static final WordType[] vals = values();
	private static final Random random = new Random();

	public static WordType getRandomWordType() 
	{
		return vals[random.nextInt(vals.length)];
	}
}
