package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.Word;
import ie.gmit.sw.words.WordBasic;
import ie.gmit.sw.words.WordType;

public class WordBasicTest {

	@Test
	public void testWordBasic() 
	{
		Word wb = new WordBasic(null, "", 0, WordType.BASIC);
		
		assertNotNull(wb);
	}

}
