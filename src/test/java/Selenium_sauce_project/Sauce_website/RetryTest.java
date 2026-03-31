package Selenium_sauce_project.Sauce_website;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{

	int retry=1;
	int count=0;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<retry) {
			count++;
			return true;
		}
		return false;
	}

}
