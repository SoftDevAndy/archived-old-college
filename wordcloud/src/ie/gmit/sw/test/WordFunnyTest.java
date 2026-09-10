package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.Word;
import ie.gmit.sw.words.WordBasic;
import ie.gmit.sw.words.WordType;

public class WordFunnyTest {

	@Test
	public void testWordFunny() 
	{
		Word wb = new WordBasic(null, "", 0, WordType.FUNNY);
		
		assertNotNull(wb);
	}
}
