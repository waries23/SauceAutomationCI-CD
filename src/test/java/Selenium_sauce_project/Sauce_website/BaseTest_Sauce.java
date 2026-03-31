package Selenium_sauce_project.Sauce_website;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest_Sauce {

	public WebDriver driver;
	public Home_page home;
	String url;
	public WebDriver initialiseDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\application\\resource\\GlobalUtilities.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null? System.getProperty("browser"): prop.getProperty("browser");
		url=prop.getProperty("url");
		
		if (browserName.contains("chrome")) {
			ChromeOptions option=new  ChromeOptions();
			if(browserName.contains("headless")) {
			option.addArguments("headless");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String,String>> getJsonDataMap(String filePath) throws IOException{
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File trgt=new File(System.getProperty("user.dir")+"//TestFailureScreenshots//"+testCaseName+".png");
		FileUtils.copyFile(source,trgt);
		return System.getProperty("user.dir")+"//TestFailureScreenshots//"+testCaseName+".png";
	}
	@BeforeMethod(alwaysRun = true)
	public void initialiseApp() throws IOException {
		driver = initialiseDriver();
		driver.get(url);
		home = new Home_page(driver);
		home.setZoom(70);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		 driver.close();
	}

}
