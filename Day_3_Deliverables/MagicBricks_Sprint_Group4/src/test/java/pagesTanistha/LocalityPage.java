package pagesTanistha;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocalityPage {
	WebDriver driver;
	
	public LocalityPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="cityLocalityInput")
	private WebElement localitySearch;
	public WebElement getLocalitySearch(){
		return localitySearch;
	}
	
	@FindBy(id="localityTypeValueSuggest")
	private List<WebElement> suggestionList;
	public List<WebElement> getsuggestionList(){
		return suggestionList;
	}
	
	@FindBy(xpath="//a[@class=\\\"loc-card__title\\\"]\"")
	private WebElement data;
	public String getDataText() {
		return data.getText();
	}
	
	@FindBy(xpath="(//a[@class=\\\"loc-card__explore\\\"])[1]")
	private WebElement projectExploreLocality;
	public WebElement getExplore() {
		return projectExploreLocality;
	}
}
