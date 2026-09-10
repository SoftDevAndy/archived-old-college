package ie.gmit.sw.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ie.gmit.sw.Worker;

public class WorkerTest {

	@Test
	public void testWorkerTest() 
	{
		Worker worker = new Worker();
		
		assertNotNull(worker);
	}

}
