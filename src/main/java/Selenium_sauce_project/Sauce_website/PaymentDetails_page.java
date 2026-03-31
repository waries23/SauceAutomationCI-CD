package Selenium_sauce_project.Sauce_website;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Common_class.Extending_methods;

public class PaymentDetails_page extends Extending_methods{

	//WebDriver driver;
	public PaymentDetails_page(WebDriver driver) {
		super(driver);
		//this.driver=driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(id="email")
	WebElement email;
	
	@FindBy(name="lastName")
	WebElement lastName;
	
	@FindBy(id="shipping-address1")
	WebElement address;
	
	@FindBy(xpath="//ul/li/span")
	List<WebElement> listOfAddresses;
	
	
	public void personalDetails() {
	visibilityOfElementLocated(By.name("lastName"));
	email.sendKeys("test1@mail.com");
	lastName.sendKeys("user1");
	address.sendKeys("Dr. Abdul Kalam Nagar, Nivetha Illam");
	visibilityOfElementLocated(By.cssSelector("._1tnwc9f1"));
	//List<WebElement> listOfAddresses=driver.findElements(By.xpath("//ul/li/span"));
	WebElement addressSelected=listOfAddresses.stream()
		.filter(x->x.getText().contains("Dr. Abdul Kalam Nagar"))
		.findFirst()
		.orElse(null);
	scrollIntoView(address);
	addressSelected.click();
	}
	
	@FindBy(id="number")
	WebElement number;
	
	@FindBy(name="expiry")
	WebElement expiry;
	
	@FindBy(xpath="//iframe[contains(@name,'verification')]")
	WebElement cvvFrame;
	
	@FindBy(xpath="//iframe[contains(@name,'expiry')]")
	WebElement expiryFrame;
	
	@FindBy(xpath="//iframe[contains(@name,'name')]")
	WebElement nameFrame;
	
	@FindBy(name="verification_value")
	WebElement cvvField;
	
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="checkout-pay-button")
	WebElement pay;
	
	public void cardDetails(long num,int mmyy,int cvv,String username) throws InterruptedException {
		
	scrollIntoView(number);
	String str = Long.toString(num);
	number.sendKeys(str);
	//int mmyy=112030;
	int mm=(mmyy/10000)%100;
	int yy=mmyy%10000;
	int presentYear=LocalDate.now().getYear();
	if(mm>12) {
		System.out.println("input greater than 12 please enter within 12");
	}
	if(yy<(presentYear%100)) {
		System.out.println("input a future year");
	}
	
	//int cvv=123;
	
	if(cvv>999) {
		System.out.println("enter 3 digit cvv number");
	}
	driver.switchTo().frame(expiryFrame);
	//WebElement expiry=driver.findElement(By.name("expiry"));
	expiry.sendKeys(String.valueOf(mm));
	expiry.sendKeys(Keys.TAB);
	expiry.sendKeys(String.valueOf(yy % 100));
	driver.switchTo().defaultContent();

	driver.switchTo().frame(cvvFrame);
	cvvField.sendKeys(String.valueOf(cvv));
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame(nameFrame);
	name.sendKeys(username);
	driver.switchTo().defaultContent();

	pay.click();
	}
	
}
