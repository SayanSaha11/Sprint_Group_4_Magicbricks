package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertySellPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public PropertySellPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(css=".mb-srp__card--title")
	private List<WebElement> propertyTitle;
	public List<WebElement> getProperty(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    return wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.cssSelector(".mb-srp__card--title")
	        )
	    );
	}
	
	public void clickFirst() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    for (int i = 0; i < 3; i++) {
	        try {
	            List<WebElement> properties = wait.until(
	                ExpectedConditions.presenceOfAllElementsLocatedBy(
	                    By.cssSelector(".mb-srp__card--title")
	                )
	            );
	            //  JS click — avoids interception by overlays/cookie banners
	            ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", properties.get(0)
	            );
	            break;
	        } catch (StaleElementReferenceException e) {
	            System.out.println("Retrying due to stale element...");
	        }
	    }
	}
}
