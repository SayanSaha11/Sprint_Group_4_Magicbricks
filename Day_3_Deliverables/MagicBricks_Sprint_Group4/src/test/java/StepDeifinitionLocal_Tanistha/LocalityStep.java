package StepDeifinitionLocal_Tanistha;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagesTanistha.HomePage;
import pagesTanistha.LocalityPage;
import stepDefinition_Tanistha.Common;
import utilities_Tanistha.DriverManager;
import utilities_Tanistha.ExcelUtil;

public class LocalityStep {
	
	  HomePage home; 
	  LocalityPage locality; 
	  
	  
	  private void init() {
		    if (home == null) home = new HomePage(DriverManager.getDriver());
		}
	  
@Given("I navigate to the Localities in Kolkata page")
public void i_navigate_to_the_localities_in_kolkata_page() {
	init();
	
	WebDriver driver=DriverManager.getDriver();
	driver.get("https://www.magicbricks.com/localities-in-Kolkata");
    locality = new LocalityPage(driver);
}

@Given("I click on the locality search input field")
public void i_click_on_the_locality_search_input_field() {
    locality.getLocalitySearch().click();
}

@Given("I type a locality name from the test data sheet")
public void i_type_a_locality_name_from_the_test_data_sheet() throws InterruptedException {
	String name = ExcelUtil.getCellData("Locality", 1, 0);
    System.out.println("Typing locality: " + name);
    WebElement el=locality.getLocalitySearch();
    
    el.sendKeys(name);
    Thread.sleep(2000); 
}

@When("I select the first suggestion from the autosuggestion list")
public void i_select_the_first_suggestion_from_the_autosuggestion_list() throws InterruptedException {
	List<WebElement> suggestions = locality.getsuggestionList();
    suggestions.get(0).click();
    Thread.sleep(2000);
}

@Then("the locality card title should be displayed")
public void the_locality_card_title_should_be_displayed() {
	String title = locality.getDataText();
    assert title != null && !title.isEmpty() : "Locality card title is not displayed!";
    System.out.println("Locality Card Title: " + title);
}

@When("I click the {string} button on the first locality card")
public void i_click_the_button_on_the_first_locality_card(String string) {
    if (string.equalsIgnoreCase("Explore")) {
         locality.getExplore().click();
    }
}

@Then("the properties for the selected locality should be displayed")
public void the_properties_for_the_selected_locality_should_be_displayed() {
//    init();
    
    WebDriver driver=DriverManager.getDriver();
    
    String currentWindow = driver.getWindowHandle();
    for (String window : driver.getWindowHandles()) {
        if (!window.equals(currentWindow)) {
            driver.switchTo().window(window);
            break;
        }
    }
    String currentUrl = driver.getCurrentUrl();
    System.out.println("Current URL after Explore click: " + currentUrl);
}

@Then("the autosuggestion dropdown should appear")
public void the_autosuggestion_dropdown_should_appear() {
	List<WebElement> suggestions=locality.getsuggestionList();
	   
	   assert suggestions != null && !suggestions.isEmpty()
	           : "Autosuggestion dropdown did not appear!";
	       System.out.println("Autosuggestion dropdown appeared with "
	           + suggestions.size() + " suggestions");
}
@Then("the suggestions list should contain relevant locality results")
public void the_suggestions_list_should_contain_relevant_locality_results() {
	List<WebElement> suggestions = locality.getsuggestionList();
    assert suggestions != null && !suggestions.isEmpty()
        : "Suggestions list is empty!";
    System.out.println("Total relevant suggestions found: " + suggestions.size());
}

@Then("each suggestion in the autosuggestion list should be printed and visible")
public void each_suggestion_in_the_autosuggestion_list_should_be_printed_and_visible() {
	List<WebElement> suggestions = locality.getsuggestionList();
    assert suggestions != null && !suggestions.isEmpty()
        : "No suggestions found to print!";
    System.out.println("All autosuggestions:");
    for (WebElement suggestion : suggestions) {
        String text = suggestion.getText();
        assert text != null && !text.isEmpty() : "Suggestion text is empty!";
        System.out.println(" - " + text);
    }
}
}
