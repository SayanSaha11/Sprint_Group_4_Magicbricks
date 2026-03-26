package pagesTanistha;

import java.time.Duration;
import java.util.List;

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
	
	public void clickFirst() {
		wait.until(ExpectedConditions.visibilityOfAllElements(propertyTitle));
		wait.until(ExpectedConditions.elementToBeClickable(propertyTitle.get(0)));
		propertyTitle.get(0).click();
	}
}
