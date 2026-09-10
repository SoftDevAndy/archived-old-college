package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.Bounds;
import ie.gmit.sw.words.SingleWordFactory;

public class SingleWordFactoryTest {

	@Test
	public void testGetInstance()
	{		
		assertNotNull(SingleWordFactory.getInstance());
	}

	@Test
	public void testCreateWord() 
	{
		assertNotNull(SingleWordFactory.getInstance().CreateWord(new Bounds(0,0,0,0), "Test", 0));
	}

}
