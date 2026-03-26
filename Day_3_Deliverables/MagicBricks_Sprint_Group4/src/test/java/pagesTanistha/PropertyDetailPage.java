package pagesTanistha;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertyDetailPage {
WebDriver driver;
	
	public PropertyDetailPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class=\"mb-header__main__link \"]")
	private List<WebElement> navigationBar;
	public WebElement getBuyTag() {
		return navigationBar.get(0);
	}
	
	@FindBy(css=".drop-call")
	private List<WebElement> functionBuy;
	public void getBudgetTag() {
		functionBuy.get(2).click();
	}
	
	@FindBy(xpath="(//div[contains(@class,\"drop-call\")])[3]//ul[contains(@class,\"drop-links\")]/li")
	private List<WebElement> budgetCategory;
	public void getCategory() {
	    budgetCategory.get(2).click();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
}
