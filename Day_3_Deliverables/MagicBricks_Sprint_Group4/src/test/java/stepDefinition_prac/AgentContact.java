package stepDefinition_prac;

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
import pagesTanistha.AgentPage;
import pagesTanistha.HomePage;

import utilities.*;

public class AgentContact {
	
	WebDriver driver;
	HomePage home;
	AgentPage agent;
	String parentWindow;
	List<WebElement> form;

@Given("I open the MagicBricks Kolkata residential real estate page")
public void i_open_the_magic_bricks_kolkata_residential_real_estate_page() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
    driver.get("https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");
    home=new HomePage(driver);
}
@Given("I click on the {string} heading")
public void i_click_on_the_heading(String string) {
	 if (string.equalsIgnoreCase("Buy")) {
		 home.getBuyClick();
	 }
}
@When("I click on {string} link")
public void i_click_on_link(String string) {
	parentWindow = driver.getWindowHandle();
    if (string.equalsIgnoreCase("Find an Agent")) {
        home.clickAgent();
    }
}
@When("a new tab opens with the agent listing")
public void a_new_tab_opens_with_the_agent_listing() {
	Set<String> windows = driver.getWindowHandles();
    for (String w : windows) {
        if (!w.equals(parentWindow)) {
            driver.switchTo().window(w);
        }
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.urlContains("agent-in-Kolkata"));
    
    agent=new AgentPage(driver);
}
@When("I click {string} on the first agent card")
public void i_click_on_the_first_agent_card(String string) {
    
    if(string.equalsIgnoreCase("Contact Agent")) {
    	List<WebElement> contact=agent.getcontactAgent();
    	contact.get(0).click();
    }
}

@Then("the contact form should be displayed")
public void the_contact_form_should_be_displayed() {
    form=agent.getForm();
    System.out.println("Contact form is displayed with " + form.size() + " fields.");
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
