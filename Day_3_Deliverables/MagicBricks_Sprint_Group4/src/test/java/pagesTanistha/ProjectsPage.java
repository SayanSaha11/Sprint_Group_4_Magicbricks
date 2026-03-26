package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public ProjectsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(xpath="//div[contains(@class,'featuredDelopBox')]")
	private List<WebElement> developer;
	public List<WebElement> getDeveloper(){
		wait.until(ExpectedConditions.visibilityOfAllElements(developer));
		return developer;
	}
	
	@FindBy(id="btnPropertySearch")
	private WebElement searchButton;
	public void clickSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
	}
	
	@FindBy(id="buy_propertyType")
	private WebElement propertyType;
	public void clickpropertyType() {
		wait.until(ExpectedConditions.elementToBeClickable(propertyType));
		propertyType.click();
	}
	
	@FindBy(xpath="//div[@class=\"col1 columnCommon\"]//ul//li")
	private List<WebElement> residentialDropdown;
	public WebElement getHouse() {
		wait.until(ExpectedConditions.visibilityOfAllElements(residentialDropdown));
		return residentialDropdown.get(2);
	}
}
