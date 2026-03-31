package Selenium_sauce_project.Sauce_website;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common_class.Extending_methods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Sauce_page_og extends BaseTest_Sauce{

	@Test
	public void EndToEnd() throws MalformedURLException, IOException, URISyntaxException, InterruptedException  {
		// TODO Auto-generated method stub

		/*WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		Extending_methods methods=new Extending_methods(driver);
		driver.get("https://sauce-demo.myshopify.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		JavascriptExecutor js=(JavascriptExecutor)driver;*/
		//js.executeScript("document.body.style.zoom='70%'");
		String wanted="Noir jacket";
		String size="L";
		String color="Blue";
		
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".animated")));
		
		home.checkForBrokenLinks();
		ProductDetailsAndCheckout_page product=home.selectItem(wanted);
		product.selectSizeAndColor(size,color);
		product.addToCart();
		PaymentDetails_page payment=product.checkout(wanted);
		payment.personalDetails();
		payment.cardDetails(0, 0, 0, color);
		/*List<WebElement> footerLinks=driver.findElements(By.cssSelector("footer .lower a"));
		List<WebElement> socialLinks=driver.findElements(By.cssSelector("[id=social] a"));
		Sauce_page page=new Sauce_page();
		page.brokenLinksCheck(footerLinks);
		page.brokenLinksCheck(socialLinks);
		List<WebElement> items=driver.findElements(By.cssSelector(".animated"));*/
		
		/*WebElement itemCheck=items.stream()
			.filter(x->x.findElement(By.cssSelector("h3")).getText().contains(wanted))
			.findFirst()
			.orElse(null);
		
		Assert.assertTrue(itemCheck.getText().contains(wanted),"The wanted product is present");
		itemCheck.click();*/
		/*WebElement sizeOption=driver.findElement(By.id("product-select-option-0"));
		Select selectedSize=new Select(sizeOption);
		selectedSize.selectByVisibleText(size);
		WebElement colorOption=driver.findElement(By.id("product-select-option-1"));
		Select selectedColor=new Select(colorOption);
		selectedColor.selectByValue(color);*/
		
		//driver.findElement(By.id("add")).click();
		/*try {
			driver.findElement(By.id("add")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cart-animation")));
		}
		catch (Exception e) {
			driver.findElement(By.id("add")).click();
		}
		js.executeScript("window.scrollBy(0,0)");
		driver.findElement(By.cssSelector(".toggle-drawer")).click();
		
		try {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".spinner")));
		}catch (Exception e) {
			
			driver.navigate().refresh();
			driver.findElement(By.cssSelector(".toggle-drawer")).click();
		}*/
		/*List<WebElement> cart=driver.findElements(By.xpath("//*[@action='/cart']/div"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@action='/cart']/div")));
		Boolean cartCheck=cart.stream()
			.anyMatch(a->a.findElement(By.xpath("//*[@class='info']/h3")).getText().contains(wanted));
		Assert.assertTrue(cartCheck,"The item is present in cart");
		driver.findElement(By.xpath("//*[@value='Check Out']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
		driver.findElement(By.id("checkout")).click();*/
		
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastName")));
		driver.findElement(By.id("email")).sendKeys("test1@mail.com");
		driver.findElement(By.name("lastName")).sendKeys("user1");
		driver.findElement(By.id("shipping-address1")).sendKeys("Dr. Abdul Kalam Nagar, Nivetha Illam");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._1tnwc9f1")));
		List<WebElement> listOfAddresses=driver.findElements(By.xpath("//ul/li/span"));
		WebElement addressSelected=listOfAddresses.stream()
			.filter(x->x.getText().contains("Dr. Abdul Kalam Nagar"))
			.findFirst()
			.orElse(null);
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("shipping-address1")));
		addressSelected.click();
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("number")));
		driver.findElement(By.id("number")).sendKeys("409457834762349");
		int mmyy=112030;
		int mm=(mmyy/10000)%100;
		int yy=mmyy%10000;
		int presentYear=LocalDate.now().getYear();
		if(mm>12) {
			System.out.println("input greater than 12 please enter within 12");
		}
		if(yy<(presentYear%100)) {
			System.out.println("input a future year");
		}
		
		int cvv=123;
		if(cvv>999) {
			System.out.println("enter 3 digit cvv number");
		}
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'expiry')]")));
		WebElement expiry=driver.findElement(By.name("expiry"));
		expiry.sendKeys(String.valueOf(mm));
		expiry.sendKeys(String.valueOf(yy % 100));
		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'verification')]")));
		driver.findElement(By.name("verification_value")).sendKeys("546");
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'name')]")));
		driver.findElement(By.id("name")).sendKeys("testuser1");
		driver.switchTo().defaultContent();

		driver.findElement(By.id("checkout-pay-button"));*/
		//driver.findElement(By.id("billingAddressCheckbox")).click();

		//driver.close();
		
		
		
		
		}
	/*public void brokenLinksCheck(List<WebElement> links) throws MalformedURLException, IOException, URISyntaxException {
		
		for(WebElement link:links) {
			String url=link.getAttribute("href");
			HttpURLConnection con=(HttpURLConnection)new URI(url).toURL().openConnection();
			con.setRequestMethod("HEAD");
			con.connect();
			int responseCode=con.getResponseCode();
			soft.assertTrue(responseCode<400, "The url "+ url +" is broken");
		}
	}*/

}
