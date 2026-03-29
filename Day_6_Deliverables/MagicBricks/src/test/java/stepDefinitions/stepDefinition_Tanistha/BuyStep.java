package stepDefinitions.stepDefinition_Tanistha;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.pagesTanistha.*;
import utils.utilities_Tanistha.DriverManager;
import utils.utilities_Tanistha.ExcelUtil;

public class BuyStep {
	
	String propertyTabWindow;
	String agentListingWindow;
	  
	AgentPage agent;
	AgentDetailsPage details;
	HomePage home; 
	PropertySellPage sell; 
	PropertyDetailPage detail;
	ProjectsPage projectsPage;
	ProjectsPropertyPage projectsPropertyPage;
	LocalityPage locality; 
	
	List<WebElement> form;
	
	private void init() {
		if (home == null) home = new HomePage(DriverManager.getDriver());
	}
	
	 @Given("I click on the {string} heading")
	    public void i_click_on_the_heading(String string) {
		 	init();
	        if (string.equalsIgnoreCase("Buy")) {
	            home.getBuyClick(); // home is initialized, won't throw NullPointerException
	        }
	    }

	    @When("I click on {string} link")
	    public void i_click_on_link(String string) {
	    	init();
	    	
	    	DriverManager.setParentWindow(DriverManager.getDriver().getWindowHandle()); 

	        if (string.equalsIgnoreCase("Find an Agent")) {
	            home.clickAgent();
	        } 
	        else if (string.equalsIgnoreCase("Localities in Kolkata")) {
	            home.clickLocality();
	        } 
	        else if (string.equalsIgnoreCase("Projects in Kolkata")) {
	            home.clickProjects();
	        }
	    }

	@When("I switch to the new agent listing tab")
	public void i_switch_to_the_new_agent_listing_tab() {
		//init();
		
		WebDriver driver=DriverManager.getDriver();
		Set<String> windows =driver.getWindowHandles(); 
		for (String w : windows) { 
			if(!w.equals(DriverManager.getParentWindow())) { 
				driver.switchTo().window(w); 
				agentListingWindow=w;
				break;
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
		
		WebDriver driver=DriverManager.getDriver();
		Set<String> windows = driver.getWindowHandles();
	    for (String w : windows) {
	        if (!w.equals(DriverManager.getParentWindow()) && !w.equals(agentListingWindow)) {
	            driver.switchTo().window(w);
	            break;
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

	@When("I click on the Property Type filter")
	public void i_click_on_the_property_type_filter() {
		 projectsPage=new ProjectsPage(DriverManager.getDriver());
		 projectsPage.clickpropertyType();
	}
	
	@When("I select a specific property type from the filter options")
	public void i_select_a_specific_property_type_from_the_filter_options() {
		WebElement house=projectsPage.getHouse();
	    house.click();
	}
	
	@When("I click the Search button on the projects page")
	public void i_click_the_search_button_on_the_projects_page() {
		if(projectsPage==null)
	    	projectsPage = new ProjectsPage(DriverManager.getDriver());
	    projectsPage.clickSearch();
	}
	
	@Then("the search results should be displayed with the applied filter")
	public void the_search_results_should_be_displayed_with_the_applied_filter() {
		projectsPropertyPage = new ProjectsPropertyPage(DriverManager.getDriver());
	    List<WebElement> results = projectsPropertyPage.getPorject();
	    assert results != null && !results.isEmpty()
	        : "No search results displayed after applying the property type filter.";
	}
	
	@Then("the selected property type should be shown in the filter display area")
	public void the_selected_property_type_should_be_shown_in_the_filter_display_area() {
		if (projectsPropertyPage == null) {
	        projectsPropertyPage = new ProjectsPropertyPage(DriverManager.getDriver());
	    }
	    WebElement filterDisplay = projectsPropertyPage.getflatFilter();
	    String displayedText = filterDisplay.getText();
	    System.out.println("Filter display area shows: " + displayedText);
	    assert displayedText != null && !displayedText.trim().isEmpty()
	        : "Filter display area is empty after selecting a property type.";
	}
	
	@Then("the featured developer listings should be displayed")
	public void the_featured_developer_listings_should_be_displayed() {
		projectsPage = new ProjectsPage(DriverManager.getDriver());
	    List<WebElement> developers = projectsPage.getDeveloper();
	    assert developers != null && !developers.isEmpty()
	        : "No featured developer listings found on the page.";
	}
	
	@Then("the details of each developer should be printed")
	public void the_details_of_each_developer_should_be_printed() {
		if (projectsPage == null) {
	        projectsPage = new ProjectsPage(DriverManager.getDriver());
	    }
	    List<WebElement> developers = projectsPage.getDeveloper();
	    System.out.println("=== Developer Details ===");
	    for (WebElement dev : developers) {
	        System.out.println(dev.getText());
	        System.out.println();
	    }
	}
	
	@Then("the project search results should be displayed")
	public void the_project_search_results_should_be_displayed() {
		projectsPropertyPage = new ProjectsPropertyPage(DriverManager.getDriver());
	    List<WebElement> results = projectsPropertyPage.getPorject();
	    assert results != null && !results.isEmpty()
	        : "No project search results were displayed after clicking Search.";
	}
	
	@Then("the details of each project in the list should be printed")
	public void the_details_of_each_project_in_the_list_should_be_printed() {
		if (projectsPropertyPage == null) {
	        projectsPropertyPage = new ProjectsPropertyPage(DriverManager.getDriver());
	    }
	    List<WebElement> projects = projectsPropertyPage.getPorject();
	    System.out.println("=== Project Listings ===");
	    for (WebElement project : projects) {
	        System.out.println(project.getText());
	        System.out.println();
	    }
	}

	@When("I click on the Budget filter option")
	public void i_click_on_the_budget_filter_option() {
		init();
	    home.getBudget().click();  
	}
	
	@When("I select minimum budget as {string}")
	public void i_select_minimum_budget_as(String string) {
		init();
		home.selectMinBudget(); 
	}

	@When("I select the maximum budget option")
	public void i_select_the_maximum_budget_option() {
		init();
		home.selectMaxBudgetOption(); 
	}
	
	@When("I click the Search button")
	public void i_click_the_search_button() {
		init();
		
		DriverManager.setParentWindow(DriverManager.getDriver().getWindowHandle()); 
		  home.getSearchButton().click();
		  
		  sell=new PropertySellPage(DriverManager.getDriver()); 
	}
	
	@Then("the search results page should display property listings")
	public void the_search_results_page_should_display_property_listings() {
		System.out.println(sell.getProperty().size());
	}

	@When("I click on the first property in the search results")
	public void i_click_on_the_first_property_in_the_search_results() {
		init();
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

	    // Ensure results page is fully loaded
	    wait.until(ExpectedConditions.urlContains("property-for-sale"));
	  sell.clickFirst();
	}
	
	@When("I switch to the property detail tab")
	public void i_switch_to_the_property_detail_tab() {
	    WebDriver driver = DriverManager.getDriver();

	    int windowCountBefore = driver.getWindowHandles().size();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    try {
	        wait.until(d -> d.getWindowHandles().size() > windowCountBefore);
	    } catch (Exception e) {
	        System.out.println("New tab did not open, retrying property click...");
	        
	        sell.clickFirst();

	        WebDriverWait retryWait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        retryWait.until(d -> d.getWindowHandles().size() > windowCountBefore);
	    }

	    for (String w : driver.getWindowHandles()) {
	        if (!w.equals(DriverManager.getParentWindow())) {
	            driver.switchTo().window(w);
	            propertyTabWindow = w;
	            break;
	        }
	    }

	    WebDriverWait pageWait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    pageWait.until(d -> !d.getCurrentUrl().equals("about:blank"));
	    pageWait.until(d -> ((JavascriptExecutor) d)
	        .executeScript("return document.readyState").equals("complete"));

	    detail = new PropertyDetailPage(driver);
	}
	
	@When("I click on the {string} link in the description section")
	public void i_click_on_the_link_in_the_description_section(String string) {
	}
	
	@Then("the page title should be displayed")
	public void the_page_title_should_be_displayed() {
		String title = detail.getPageTitle();
		  
		  assert title != null && !title.isEmpty() : "Page title is not displayed!";
		  
		  System.out.println("Page Title: " + title); 
	}
	
	@Then("the full property description should be visible and displayed")
	public void the_full_property_description_should_be_visible_and_displayed() {
		String desc=detail.getDesc();
		assert desc != null && !desc.isEmpty() :"Property description is not visible!"; 
		System.out.println("Description: " + desc); 
	}
}
