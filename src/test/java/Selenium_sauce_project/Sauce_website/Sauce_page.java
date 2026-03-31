package Selenium_sauce_project.Sauce_website;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common_class.Extending_methods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Sauce_page extends BaseTest_Sauce{

	@Test(groups= {"endtoend"},dataProvider="getJsonData",retryAnalyzer=RetryTest.class)
	public void EndToEnd(HashMap<String,String> data) throws MalformedURLException, IOException, URISyntaxException, InterruptedException  {

		

		long num=478654346572l;
		int mmyy=1030;
		int cvv=451;
		String username="testuser1";
		ProductDetailsAndCheckout_page product=home.selectItem(data.get("productName"));
		product.selectSizeAndColor(data.get("size"),data.get("color"));
		product.addToCart();
		PaymentDetails_page payment=product.checkout(data.get("productName"));
		payment.personalDetails();
		payment.cardDetails(num,mmyy,cvv,username);
	}
	
	@Test(enabled=false)
	public void ProductList() {
		System.out.println(home.getProductsList());
		System.out.println(home.getSoldOutProducts());
	}

	@DataProvider
	public Object[][] getJsonData() throws IOException {
		List<HashMap<String,String>> data=getJsonDataMap((System.getProperty("user.dir")+"\\src\\test\\java\\DataReader\\products.json"));
		return new Object[][] {{data.get(0)}};
	}
}
