package ie.gmit.sw.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ie.gmit.sw.ui.UserUI;

public class UserUITest {

	@Test
	public void test()
	{
		UserUI ui = new UserUI();
		assertNotNull(ui);
	}

}
