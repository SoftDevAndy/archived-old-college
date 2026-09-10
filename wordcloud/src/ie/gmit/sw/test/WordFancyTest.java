package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.Word;
import ie.gmit.sw.words.WordBasic;
import ie.gmit.sw.words.WordType;

public class WordFancyTest {

	@Test
	public void testWordFancy() 
	{
		Word wb = new WordBasic(null, "", 0, WordType.FANCY);
		
		assertNotNull(wb);
	}
}
