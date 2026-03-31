package Selenium_sauce_project.Sauce_website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common_class.Extending_methods;

public class ProductDetailsAndCheckout_page extends Extending_methods{

	//WebDriver driver;
	public ProductDetailsAndCheckout_page(WebDriver driver) {
		super(driver);
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="product-select-option-0")
	WebElement sizeOption;
	//WebElement sizeOption=driver.findElement(By.id("product-select-option-0"));
	
	//WebElement colorOption=driver.findElement(By.id("product-select-option-1"));
	@FindBy(id="product-select-option-1")
	WebElement colorOption;
	
	@FindBy(id="add")
	WebElement addToCart;
	
	@FindBy(css=".toggle-drawer")
	WebElement cartIcon;
	
	@FindBy(xpath="//*[@action='/cart']/div")
	List<WebElement> cart;
	//List<WebElement> cart=driver.findElements(By.xpath("//*[@action='/cart']/div"));
	
	@FindBy(xpath="//*[@value='Check Out']")
	WebElement checkoutBtn1;
	
	@FindBy(id="checkout")
	WebElement checkoutBtn2;
	
	public PaymentDetails_page checkout(String wanted) {
		visibilityOfAllElementsLocated(By.xpath("//*[@action='/cart']/div"));
	Boolean cartCheck=cart.stream()
		.anyMatch(a->a.findElement(By.xpath("//*[@class='info']/h3")).getText().contains(wanted));
	Assert.assertTrue(cartCheck,"The item is present in cart");
	checkoutBtn1.click();
	visibilityOfElementLocated(By.id("checkout"));
	checkoutBtn2.click();
	PaymentDetails_page payment=new PaymentDetails_page(driver);
	return payment;
	}
	
	public void addToCart() {
		try {
			addToCart.click();
			invisibilityOfElementLocated(By.id("cart-animation"));
		}
		catch (Exception e) {
			addToCart.click();
		}
		
		scrollToTop();
		cartIcon.click();
		
		try {
		invisibilityOfElementLocated(By.xpath(".spinner"));
		}catch (Exception e) {
			
			driver.navigate().refresh();
			cartIcon.click();
		}
	}
	
	public void selectSizeAndColor(String size,String color) {
	//setZoom(70);
	Select selectedSize=new Select(sizeOption);
	selectedSize.selectByVisibleText(size);
	Select selectedColor=new Select(colorOption);
	selectedColor.selectByValue(color);
	}
	
	
}
