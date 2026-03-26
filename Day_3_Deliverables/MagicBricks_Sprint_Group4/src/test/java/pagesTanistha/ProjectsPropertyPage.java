package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPropertyPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public ProjectsPropertyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@FindBy(xpath="//div[@class=\"srpBlockListRow\"]")
	List<WebElement> projectproperty;
	public List<WebElement> getPorject(){
		wait.until(ExpectedConditions.visibilityOfAllElements(projectproperty));
		return projectproperty;
	}
	
	@FindBy(id="proertyTypeDefault")
	private WebElement flatFilter;
	public WebElement getflatFilter() {
		wait.until(ExpectedConditions.elementToBeClickable(flatFilter));
		return flatFilter;
	}
}
