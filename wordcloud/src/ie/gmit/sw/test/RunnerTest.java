package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Runner;

public class RunnerTest {

	@Test
	public void test()
	{
		Runner runner = new Runner();
		assertNotNull(runner);
	}

}
