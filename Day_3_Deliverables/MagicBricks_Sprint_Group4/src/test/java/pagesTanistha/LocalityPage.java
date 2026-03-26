package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalityPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LocalityPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@FindBy(id="cityLocalityInput")
	private WebElement localitySearch;
	public WebElement getLocalitySearch(){
		wait.until(ExpectedConditions.visibilityOf(localitySearch));
		return localitySearch;
	}
	
	@FindBy(id="localityTypeValueSuggest")
	private List<WebElement> suggestionList;
	public List<WebElement> getsuggestionList(){
		wait.until(ExpectedConditions.visibilityOfAllElements(suggestionList));
		return suggestionList;
	}
	
	@FindBy(xpath="//a[@class='loc-card__title']")
	private WebElement data;
	public String getDataText() {
		wait.until(ExpectedConditions.visibilityOf(data));
		return data.getText();
	}
	
	@FindBy(xpath="(//a[@class='loc-card__explore'])[1]")
	private WebElement projectExploreLocality;
	public WebElement getExplore() {
		wait.until(ExpectedConditions.elementToBeClickable(projectExploreLocality));
		return projectExploreLocality;
	}
}
