
  package stepDefinition;
  
  import java.time.Duration;
import java.util.Set;
  
  import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then; 
  import io.cucumber.java.en.When; 
  import pagesTanistha.HomePage; 
  import pagesTanistha.PropertyDetailPage; 
  import pagesTanistha.PropertySellPage;
  
  public class Budget_Step {
  
  WebDriver driver; 
  HomePage home; 
  PropertySellPage sell; 
  PropertyDetailPage detail;
  String parentWindow;
  String propertyTabWindow;
  
  public Budget_Step() {
      try {
    	  driver = CommonSteps.driver;
          }
      catch(Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
    	  e.printStackTrace();
      }
     
  }
  

  @Given("I click on the {string} search tab")
  public void i_click_on_the_buy_search_tab(String string) {
	  home.getBuy(); 
  }
  
  @When("I click on the Budget filter option") 
  public void i_click_on_the_budget_filter_option() { 
	  home.getBudget().click(); 
  }
  
  @When("I select minimum budget as {string}") 
  public void i_select_minimum_budget_as(String string) { 
	  home.selectMinBudget(); 
  }
  
  @When("I select the maximum budget option") 
  public void i_select_the_maximum_budget_option() { 
	  home.selectMaxBudgetOption(); 
  }
  
  @When("I click the Search button") 
  public void i_click_the_search_button() {
  parentWindow=driver.getWindowHandle(); 
  home.getSearchButton().click();
  
  sell=new PropertySellPage(driver); 
  }
  
  @Then("the search results page should display property listings") public void
  the_search_results_page_should_display_property_listings() {
  System.out.println(sell.getProperty().size()); }
  
  @When("I click on the first property in the search results") 
  public void i_click_on_the_first_property_in_the_search_results() {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // ✅ Ensure results page is fully loaded
	    wait.until(ExpectedConditions.urlContains("property-for-sale"));
	  sell.clickFirst(); 
	}
  
  @When("I switch to the property detail tab") 
  public void i_switch_to_the_property_detail_tab() { 
	  Set<String> windowsBefore = driver.getWindowHandles();
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	  wait.until(driver1 -> driver1.getWindowHandles().size() >= windowsBefore.size());
	    
	  Set<String> windows = driver.getWindowHandles(); 
	  for (String w : windows) { 
		  if(!w.equals(parentWindow)) { 
			  driver.switchTo().window(w); 
			  propertyTabWindow =w; 
			  } 
		  } 
	 
	  
	  // Initialise PropertyDetailPage POM after switching tab detail = new
	  detail=new PropertyDetailPage(driver); 
  }
  
  @When("I click on the {string} link in the description section") public void
  i_click_on_the_link_in_the_description_section(String string) {
  
  }
  
  @Then("the page title should be displayed") 
  public void the_page_title_should_be_displayed() { 
	  String title = detail.getPageTitle();
	  
	  assert title != null && !title.isEmpty() : "Page title is not displayed!";
	  
	  System.out.println("Page Title: " + title); 
  }
  
  @Then("the full property description should be visible and displayed") public
  void the_full_property_description_should_be_visible_and_displayed() { String
  desc=detail.getDesc(); assert desc != null && !desc.isEmpty() :
  "Property description is not visible!"; System.out.println("Description: " +
  desc); }
  

  }
 