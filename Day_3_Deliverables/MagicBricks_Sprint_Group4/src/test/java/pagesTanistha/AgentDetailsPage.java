package pagesTanistha;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgentDetailsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public AgentDetailsPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "agntbanner__agntname")
    private WebElement agentName;
    public String getAgentName() {
        wait.until(ExpectedConditions.visibilityOf(agentName));
        return agentName.getText();
    }
    
    @FindBy(className = "agntbanner__projname")
    private WebElement agentProject;
    public String getAgentProject() {
        wait.until(ExpectedConditions.visibilityOf(agentProject));
        return agentProject.getText();
    }
    
    @FindBy(className = "agntbanner__reradata")
    private WebElement agentExp;
    public String getAgentExp() {
        wait.until(ExpectedConditions.visibilityOf(agentExp));
        return agentExp.getText();
    }
}
