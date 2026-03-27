package pagesTanistha;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgentPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public AgentPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='prefagent']")
	private List<WebElement> agentData;
	public List<WebElement> getAgentData() {
	    wait.until(
	        ExpectedConditions.visibilityOfAllElements(agentData)
	    );
	    return agentData;
	}
	
	@FindBy(xpath="//a[contains(text(),\"View Details\")]")
	private List<WebElement> viewAgentDetails;
	public List<WebElement> getAgentDetails() {
		wait.until(
		        ExpectedConditions.visibilityOfAllElements(viewAgentDetails)
		    );
		return viewAgentDetails;
	}
	
	@FindBy(xpath=".//a[contains(text(),'Contact Agent')]")
	private List<WebElement> contactAgent;
	public List<WebElement> getcontactAgent(){
		wait.until(
		        ExpectedConditions.visibilityOfAllElements(contactAgent)
		    );
		return contactAgent;
	}
	
	@FindBy(xpath="//input[@class='m-contact__input']")
	private List<WebElement> formData;
	public List<WebElement> getForm() {
		wait.until(
		        ExpectedConditions.visibilityOfAllElements(formData)
		    );
		return formData;
	}
	
	@FindBy(xpath="//input[@class='m-contact__input m-contact__mobile-no']")
	private WebElement mobile;
	public WebElement getMobile() {
		wait.until(
		        ExpectedConditions.visibilityOf(mobile)
		    );
		return mobile;
	}
	
	@FindBy(id="contactObjButton")
	private WebElement contactButton;
	public void clickContact() {
		wait.until(
		        ExpectedConditions.elementToBeClickable(contactButton)
		    );
		contactButton.click();
	}
	
	@FindBy(id="smsNo")
	private WebElement otpField;
	public void handleOTP() throws InterruptedException {
	    // Wait for OTP field
	    wait.until(ExpectedConditions.visibilityOf(otpField));
	    System.out.println("Enter OTP manually...");
	    
	    // Give user time to enter OTP
	    Thread.sleep(60000);
	    
	    // ✅ Re-locate verify button fresh after sleep — avoids stale element
	    wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//a[contains(text(),'Verify')]")
	    ));
	    driver.findElement(By.xpath("//a[contains(text(),'Verify')]")).click();
	}	 
}
