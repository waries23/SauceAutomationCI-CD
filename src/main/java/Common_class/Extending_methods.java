package Common_class;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Extending_methods {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected SoftAssert soft;
	protected JavascriptExecutor js;
	public Extending_methods(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		soft=new SoftAssert();
		js=(JavascriptExecutor)driver;
	}
	
	
public void brokenLinksCheck(List<WebElement> links) throws MalformedURLException, IOException, URISyntaxException {
		
		for(WebElement link:links) {
			String url=link.getAttribute("href");
			HttpURLConnection con=(HttpURLConnection)new URI(url).toURL().openConnection();
			con.setRequestMethod("HEAD");
			con.connect();
			int responseCode=con.getResponseCode();
			if(responseCode<400)
			soft.assertTrue(responseCode<400, "The url "+ url +" is broken");
			else
				System.out.println("The url "+url+" has response code of "+responseCode+" is broken");
		}
	}

public void visibilityOfAllElementsLocated(By waitId) {
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(waitId));

}

public void invisibilityOfElementLocated(By byid) {
	wait.until(ExpectedConditions.invisibilityOfElementLocated(byid));

}

public void visibilityOfElementLocated(By byid1) {
	wait.until(ExpectedConditions.visibilityOfElementLocated(byid1));
}

public void scrollToTop() {
	js.executeScript("window.scrollBy(0,0)");
}

public void scrollIntoView(WebElement element) {
	js.executeScript("arguments[0].scrollIntoView(true);", element);
}

public void setZoom(int zoom) {
	js.executeScript("document.body.style.zoom='"+zoom+"%'");

}

public void alertWait() {
	wait.until(ExpectedConditions.alertIsPresent());
}
}
