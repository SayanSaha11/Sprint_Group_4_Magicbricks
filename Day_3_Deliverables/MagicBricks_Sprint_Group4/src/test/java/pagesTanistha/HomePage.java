package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(id="buyheading")
	private WebElement buy;
	public void getBuyClick() {
		wait.until(ExpectedConditions.elementToBeClickable(buy));
	    buy.click();
		//return buy;
	}
	
	@FindBy(xpath="(//a[contains(text(),'Localities in Kolkata')])[1]")
	private WebElement locality;
	public void getLocality() {
	    wait.until(ExpectedConditions.elementToBeClickable(locality));
	    locality.click();
	}
	
	@FindBy(xpath="(//a[contains(text(),'Projects in Kolkata')])[1]")
	private WebElement projects;
	public WebElement getpProjects() {
		return projects;
	}
	
	@FindBy(xpath="(//a[contains(text(),'Find an Agent')])[1]")
	private WebElement agent;
	public WebElement getAgent() {
		return agent;
	}
	
	@FindBy(css=".mb-search__tab__item")
	private List<WebElement> searchTab;
	public WebElement getBuy() {
		wait.until(ExpectedConditions.elementToBeClickable(searchTab.get(0)));
		return searchTab.get(0);
	}
	public WebElement getRent() {
		wait.until(ExpectedConditions.elementToBeClickable(searchTab.get(1)));
		return searchTab.get(1);
	}
	public WebElement getNewProjects() {
		wait.until(ExpectedConditions.elementToBeClickable(searchTab.get(2)));
		return searchTab.get(2);
	}
	 
	
	@FindBy(css=".mb-search__title")
	private List<WebElement> searchBar_options;
	public WebElement getType() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBar_options.get(0)));
		return searchBar_options.get(0);
	}
	public WebElement getBudget() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBar_options.get(1)));
		return searchBar_options.get(1);
	}
	
	// Min budget option (example ₹10 Lac)
    @FindBy(xpath = "(//div[contains(text(),'₹10 Lac')])[1]")
    private WebElement minBudgetOption;
    public void selectMinBudget() {
    	minBudgetOption.click();
    }
    
    @FindBy(id="maxBhkIndex_8")
    private WebElement maxBudgetOption;
    public void selectMaxBudgetOption() {
    	maxBudgetOption.click();
    }
    
	
	@FindBy(css=".mb-search__btn")
	private WebElement searchButton;
	public WebElement getSearchButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		return searchButton;
	}
}
