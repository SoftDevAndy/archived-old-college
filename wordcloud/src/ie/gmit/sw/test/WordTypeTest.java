package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.WordType;

public class WordTypeTest 
{
	@Test
	public void testetRandomWordType() 
	{
		assertNotNull(WordType.getRandomWordType());
	}
}
