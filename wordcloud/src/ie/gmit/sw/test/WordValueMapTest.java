package ie.gmit.sw.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import ie.gmit.sw.WordValueMap;

public class WordValueMapTest {

	@Test
	public void testSize() 
	{
		WordValueMap wvp = new WordValueMap();
		
		wvp.add("Test");
		
		assertNotNull(wvp.size());
	}

	@Test
	public void testGetOrderedList()
	{
		WordValueMap wvp = new WordValueMap();
		
		wvp.add("Test");
		
		assertNotNull(wvp.getOrderedList());
	}

	@Test
	public void testGetOrderedKeys()
	{
		WordValueMap wvp = new WordValueMap();
		
		HashMap<String, Integer> map = new HashMap<>();
		
		wvp.add("Test");
		
		assertNotNull(wvp.getOrderedKeys(map));
	}

	@Test
	public void testHighestKey()
	{
		WordValueMap wvp = new WordValueMap();
		
		HashMap<String, Integer> map = new HashMap<>();
		
		wvp.add("Test");
		
		assertNotNull(wvp.highestKey(map));
	}

	@Test
	public void testMostUsedCount()
	{
		WordValueMap wvp = new WordValueMap();
		
		wvp.add("Test");
		
		assertNotNull(wvp.mostUsedCount());
	}

	@Test
	public void testMostUsedWord()
	{
		WordValueMap wvp = new WordValueMap();
		
		wvp.add("Test");
		
		assertNotNull(wvp.mostUsedCount());
	}

	@Test
	public void testGetStrValMap()
	{
		WordValueMap wvp = new WordValueMap();
		
		assertNotNull(wvp.getStrValMap());
	}

	@Test
	public void testGetValueForKey()
	{
		WordValueMap wvp = new WordValueMap();
		
		wvp.add("Test");
		
		assertNotNull(wvp.getValueForKey("Test"));
	}

}
