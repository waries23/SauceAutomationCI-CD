package Selenium_sauce_project.Sauce_website;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Common_class.Extending_methods;

public class Home_page extends Extending_methods{
	
	//WebDriver driver;
	public Home_page(WebDriver driver) {
		super(driver);
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="footer .lower a")
	List<WebElement> footerLinks;
	//List<WebElement> footerLinks=driver.findElements(By.cssSelector("footer .lower a"));
	//List<WebElement> socialLinks=driver.findElements(By.cssSelector("[id=social] a"));
	
	@FindBy(css="[id=social] a")
	List<WebElement> socialLinks;
	
	
	
	@FindBy(css=".animated")
	List<WebElement> items;
	//List<WebElement> items=driver.findElements(By.cssSelector(".animated"));
	
	@FindBy(id="customer_email")
	WebElement emailAddress;
	
	@FindBy(id="customer_password")
	WebElement password;
	
	@FindBy(className="button")
	WebElement signinButton;
	
	@FindBy(css=".customer-name")
	WebElement userName;
	
	@FindBy(id="customer_login_link")
	WebElement signin;
	
	@FindBy(css="#main-menu li:nth-child(2)")
	WebElement catalog;
	
	@FindBy(css=".product-grid a h3")
	List<WebElement> productName;
	
	@FindBy(css=".product-grid a h4")
	List<WebElement> productPrice;
	
	@FindBy(xpath="//section/div/a[.//div[@class='sold-out']]//h3")
	List<WebElement> soldout;
	
	public ProductDetailsAndCheckout_page selectItem(String wanted) {
		visibilityOfAllElementsLocated(By.cssSelector(".animated"));
	WebElement itemCheck=items.stream()
			.filter(x->x.findElement(By.cssSelector("h3")).getText().contains(wanted))
			.findFirst()
			.orElse(null);
		
		//Assert.assertTrue(itemCheck.getText().contains(wanted),"The wanted product is present");
		itemCheck.click();
		ProductDetailsAndCheckout_page product=new ProductDetailsAndCheckout_page(driver);
		return product;
	}
	
	public void checkForBrokenLinks() throws MalformedURLException, IOException, URISyntaxException {
		visibilityOfAllElementsLocated(By.cssSelector(".animated"));
		//setZoom(70);
		brokenLinksCheck(footerLinks);
		brokenLinksCheck(socialLinks);
	}
	
	public String loginTest(String user,String pwd) {
		visibilityOfAllElementsLocated(By.cssSelector(".animated"));
		signin.click();
		visibilityOfElementLocated(By.id("customer_email"));
		emailAddress.sendKeys(user);
		password.sendKeys(pwd);
		signinButton.click();
		visibilityOfElementLocated(By.cssSelector(".customer_name"));
		return userName.getText();
	}
	
	public Map<String, String> getProductsList() {
		visibilityOfAllElementsLocated(By.cssSelector(".animated"));
		catalog.click();
		visibilityOfAllElementsLocated(By.cssSelector(".product-grid a h3"));
		Map<String,String> products=new HashMap<>();
		for(int i=0;i<productName.size();i++) {
			products.put(productName.get(i).getText(), productPrice.get(i).getText());
		}
		return products;
	}
	
	public List<String> getSoldOutProducts() {
		visibilityOfAllElementsLocated(By.cssSelector(".animated"));
		catalog.click();
		visibilityOfAllElementsLocated(By.cssSelector(".product-grid a h3"));
		List<String> soldoutProducts=new ArrayList<>();
		for(WebElement sout:soldout) {
			soldoutProducts.add(sout.getText());
		}
		return soldoutProducts;
	}
	
}
