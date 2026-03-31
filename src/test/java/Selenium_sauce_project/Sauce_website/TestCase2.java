package Selenium_sauce_project.Sauce_website;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase2 extends BaseTest_Sauce{

	@Test(groups= {"endtoend"})
	public void BrokenLinksCheck() throws MalformedURLException, IOException, URISyntaxException {
		home.checkForBrokenLinks();
		
	}
	
	@Test
	public void SigninTest() {
		String name=home.loginTest("test1@mail.com", "user1@123");
		Assert.assertEquals(name, "test user1");
	}
}
