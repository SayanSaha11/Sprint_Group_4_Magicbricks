package pagesTanistha;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsPage {
	WebDriver driver;
	
	public ProjectsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'featuredDelopBox')]")
	private List<WebElement> developer;
	public List<WebElement> getDeveloper(){
		return developer;
	}
	
	@FindBy(id="btnPropertySearch")
	private WebElement searchButton;
	public void clickSearch() {
		searchButton.click();
	}
	
	@FindBy(id="buy_propertyType")
	private WebElement propertyType;
	public WebElement getpropertyType() {
		return propertyType;
	}
	
	@FindBy(xpath="//div[@class=\"col1 columnCommon\"]//ul//li")
	private List<WebElement> residentialDropdown;
	public WebElement getHouse() {
		return residentialDropdown.get(2);
	}
}
