package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertyDetailPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public PropertyDetailPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(xpath="//span[@class='mb-header__main__link']")
	private List<WebElement> navigationBar;
	public void clickBuyTag() {
		//wait.until(ExpectedConditions.visibilityOfAllElements(navigationBar));
		wait.until(ExpectedConditions.elementToBeClickable(navigationBar.get(0)));
		navigationBar.get(0).click();
	}
	
	@FindBy(css=".drop-call")
	private List<WebElement> functionBuy;
	public void clickBudgetTag() {
		wait.until(ExpectedConditions.visibilityOfAllElements(functionBuy));
		wait.until(ExpectedConditions.elementToBeClickable(functionBuy.get(2)));
		functionBuy.get(2).click();
	}
	
	@FindBy(xpath="(//div[contains(@class,\"drop-call\")])[3]//ul[contains(@class,\"drop-links\")]/li")
	private List<WebElement> budgetCategory;
	public void clickCategory() {
		wait.until(ExpectedConditions.visibilityOfAllElements(budgetCategory));
		wait.until(ExpectedConditions.elementToBeClickable(budgetCategory.get(2)));
	    budgetCategory.get(2).click();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
}
