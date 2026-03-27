package stepDefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagesTanistha.HomePage;
import pagesTanistha.ProjectsPage;
import pagesTanistha.ProjectsPropertyPage;

public class ProjectStep {
	
	WebDriver driver; 
	  HomePage home; 
	  String parentWindow;
	  String propertyTabWindow;
	  ProjectsPage projectsPage;
	  ProjectsPropertyPage projectsPropertyPage;
	  
	  public ProjectStep() {
	      try {
	    	  driver = CommonSteps.driver;
	          }
	      catch(Exception e) { 
	    	  e.printStackTrace();
	      }
	      home = new HomePage(CommonSteps.driver);
	  } 

@When("I click on the Property Type filter")
public void i_click_on_the_property_type_filter() {
    projectsPage=new ProjectsPage(driver);
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
    	projectsPage = new ProjectsPage(driver);
    projectsPage.clickSearch();
}

@Then("the search results should be displayed with the applied filter")
public void the_search_results_should_be_displayed_with_the_applied_filter() {
	projectsPropertyPage = new ProjectsPropertyPage(driver);
    List<WebElement> results = projectsPropertyPage.getPorject();
    assert results != null && !results.isEmpty()
        : "No search results displayed after applying the property type filter.";
}

@Then("the selected property type should be shown in the filter display area")
public void the_selected_property_type_should_be_shown_in_the_filter_display_area() {
	if (projectsPropertyPage == null) {
        projectsPropertyPage = new ProjectsPropertyPage(driver);
    }
    WebElement filterDisplay = projectsPropertyPage.getflatFilter();
    String displayedText = filterDisplay.getText();
    System.out.println("Filter display area shows: " + displayedText);
    assert displayedText != null && !displayedText.trim().isEmpty()
        : "Filter display area is empty after selecting a property type.";
}

@Then("the featured developer listings should be displayed")
public void the_featured_developer_listings_should_be_displayed() {
	projectsPage = new ProjectsPage(driver);
    List<WebElement> developers = projectsPage.getDeveloper();
    assert developers != null && !developers.isEmpty()
        : "No featured developer listings found on the page.";
}

@Then("the details of each developer should be printed")
public void the_details_of_each_developer_should_be_printed() {
	if (projectsPage == null) {
        projectsPage = new ProjectsPage(driver);
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
	projectsPropertyPage = new ProjectsPropertyPage(driver);
    List<WebElement> results = projectsPropertyPage.getPorject();
    assert results != null && !results.isEmpty()
        : "No project search results were displayed after clicking Search.";
}

@Then("the details of each project in the list should be printed")
public void the_details_of_each_project_in_the_list_should_be_printed() {
	if (projectsPropertyPage == null) {
        projectsPropertyPage = new ProjectsPropertyPage(driver);
    }
    List<WebElement> projects = projectsPropertyPage.getPorject();
    System.out.println("=== Project Listings ===");
    for (WebElement project : projects) {
        System.out.println(project.getText());
        System.out.println();
    }
}
}