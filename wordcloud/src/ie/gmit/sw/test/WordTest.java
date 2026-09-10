package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.Bounds;
import ie.gmit.sw.words.Word;

public class WordTest {

	@Test
	public void testGetBounds() 
	{
		Word word = new Word(new Bounds(0, 0, 0, 0), "Test", 0, null);
		
		assertNotNull(word.getBounds());
	}

	@Test
	public void testGetWt()
	{
		Word word = new Word(new Bounds(0, 0, 0, 0), "Test", 0, null);
		
		assertNotNull(word.getWt());
	}

	@Test
	public void testGetColor()
	{
		Word word = new Word(new Bounds(0, 0, 0, 0), "Test", 0, null);
		
		assertNotNull(word.getColor());
	}

	@Test
	public void testGetFont()
	{
		Word word = new Word(new Bounds(0, 0, 0, 0), "Test", 0, null);
		
		assertNotNull(word.getFont());
	}

	@Test
	public void testGetText() {
		Word word = new Word(new Bounds(0, 0, 0, 0), "Test", 0, null);
		
		assertNotNull(word.getText());
	}

	@Test
	public void testGetSize() {
		Word word = new Word(new Bounds(0, 0, 0, 0), "Test", 0, null);
		
		assertNotNull(word.getSize());
	}

}
