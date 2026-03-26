package pagesTanistha;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsPropertyPage {
	WebDriver driver;
	
	public ProjectsPropertyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class=\"srpBlockListRow\"]")
	List<WebElement> projectproperty;
	public List<WebElement> getPorject(){
		return projectproperty;
	}
	
	@FindBy(id="proertyTypeDefault")
	private WebElement flatFilter;
	public WebElement getflatFilter() {
		return flatFilter;
	}
}
