package stepDefinition;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagesTanistha.AgentDetailsPage;
import pagesTanistha.AgentPage;
import pagesTanistha.HomePage;
import utilities.ExcelUtil;

public class Agents_Step {
	
	WebDriver driver; 
	HomePage home; 
	AgentPage agent; 
	String parentWindow;
	String agentListingWindow;
	List<WebElement> form;
	AgentDetailsPage details;

	public Agents_Step() {
	try {
		home = new HomePage(CommonSteps.driver);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver = CommonSteps.driver;
	}

@When("I switch to the new agent listing tab")
public void i_switch_to_the_new_agent_listing_tab() {
	Set<String> windows =driver.getWindowHandles(); 
	for (String w : windows) { 
		if(!w.equals(parentWindow)) { 
			driver.switchTo().window(w); 
			agentListingWindow=w;
			} 
		} 
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.urlContains("agent-in-Kolkata"));
			  
	agent=new AgentPage(driver);
}

@When("I click {string} on the first agent card")
public void i_click_on_the_first_agent_card(String string) {
	if(string.equalsIgnoreCase("Contact Agent")) 
	{ 
		List<WebElement> contact=agent.getcontactAgent(); 
		contact.get(0).click(); 
		//form=agent.getForm();
	} 
	else if(string.equalsIgnoreCase("View Details")) {
		List<WebElement> viewDetails=agent.getAgentDetails();
		viewDetails.get(0).click();
	}
}

@When("I switch to the agent detail tab")
public void i_switch_to_the_agent_detail_tab() {
	Set<String> windows = driver.getWindowHandles();
    for (String w : windows) {
        if (!w.equals(parentWindow) && !w.equals(agentListingWindow)) {
            driver.switchTo().window(w);
        }
    }
    
    details = new AgentDetailsPage(driver);
}

@Then("the agent name should be displayed")
public void the_agent_name_should_be_displayed() {
	System.out.println(details.getAgentName());
}
@Then("the agent company or project name should be displayed")
public void the_agent_company_or_project_name_should_be_displayed() {
   System.out.println(details.getAgentProject());
}
@Then("the agent experience details should be displayed")
public void the_agent_experience_details_should_be_displayed() {
    System.out.println(details.getAgentExp());
}

@Then("the contact form should be displayed")
public void the_contact_form_should_be_displayed() {
    form = agent.getForm();
    assert form != null && form.size() > 0 : "Contact form is not displayed!";
    System.out.println("Contact form displayed with " + form.size() + " fields.");
}

@When("I enter the name from the test data sheet")
public void i_enter_the_name_from_the_test_data_sheet() {
	String name=ExcelUtil.getCellData("Agent", 1, 0); 
	form.get(0).sendKeys(name);
}

@When("I enter the email from the test data sheet")
public void i_enter_the_email_from_the_test_data_sheet() {
	String email=ExcelUtil.getCellData("Agent", 1, 1); 
	form.get(1).sendKeys(email);
}

@When("I enter the mobile number from the test data sheet")
public void i_enter_the_mobile_number_from_the_test_data_sheet() {
	String mobile=ExcelUtil.getCellData("Agent", 1, 2);
	agent.getMobile().sendKeys(mobile.substring(0,10));
}

@When("I click the submit button")
public void i_click_the_submit_button() {
	agent.clickContact();
}

@Then("I complete the OTP verification")
public void i_complete_the_otp_verification() throws InterruptedException {
    agent.handleOTP();
}

}
