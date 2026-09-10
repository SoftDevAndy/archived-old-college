package ie.gmit.sw.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ie.gmit.sw.ui.UserUIDialog;

public class UserUIDialogTest {

	@Test
	public void test() 
	{
		UserUIDialog ui = new UserUIDialog("", "");
		assertNotNull(ui);
	}

}
