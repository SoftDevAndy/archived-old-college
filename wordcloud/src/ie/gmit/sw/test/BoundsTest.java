package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.words.Bounds;

public class BoundsTest {

	@Test
	public void testDoIAvoidCollision() 
	{
		Bounds bounds = new Bounds(0, 0, 0, 0);
		
		assertTrue(bounds.doIAvoidCollision(new Bounds(9,9,9,9), 1));
	}

	@Test
	public void testGetX() 
	{
		Bounds bounds = new Bounds(0, 0, 0, 0);
		assertSame(bounds.getX(), 0);
	}

	@Test
	public void testGetY() 
	{
		Bounds bounds = new Bounds(0, 0, 0, 0);
		assertSame(bounds.getY(), 0);
	}

	@Test
	public void testGetWidth() 
	{
		Bounds bounds = new Bounds(0, 0, 0, 0);
		assertSame(bounds.getWidth(), 0);
	}

	@Test
	public void testGetHeight() 
	{
		Bounds bounds = new Bounds(0, 0, 0, 0);
		assertSame(bounds.getHeight(), 0);
	}

}
