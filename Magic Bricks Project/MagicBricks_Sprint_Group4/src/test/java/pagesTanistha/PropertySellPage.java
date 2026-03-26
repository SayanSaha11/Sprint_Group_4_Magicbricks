package pagesTanistha;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertySellPage {
	WebDriver driver;
	
	public PropertySellPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-srp__card--title")
	private List<WebElement> propertyTitle;
	
	public void clickFirst() {
		propertyTitle.get(0).click();
	}
}
