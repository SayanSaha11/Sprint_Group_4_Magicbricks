package stepDefinition_Tanistha;

import io.cucumber.java.en.*;

public class Tanistha_Buy2 {

    @Given("I open the MagicBricks website for {string}")
    public void i_open_the_magic_bricks_website_for(String city) {
        // Code to open MagicBricks website for the given city
        // Example: driver.get("https://www.magicbricks.com/properties-in-" + city.toLowerCase());
    }

    @When("I navigate to {string} page")
    public void i_navigate_to_page(String pageName) {
        // Generic navigation method
        switch (pageName) {
            case "Localities":
                // navigate to Localities page
                break;
            case "Projects":
                // navigate to Projects page
                break;
            case "Find an Agent":
                // navigate to Find an Agent page
                break;
            case "property details":
                // navigate to first property details page
                break;
        }
    }

    @When("I apply budget filter from {string} to {string}")
    public void i_apply_budget_filter_from_to(String minPrice, String maxPrice) {
        // Apply budget filter code
    }

    @When("I click the search button")
    public void i_click_the_search_button() {
        // Click search button
    }

    @Then("I should see the property listings")
    public void i_should_see_the_property_listings() {
        // Validate property listings are displayed
    }

    @When("I open the first property")
    public void i_open_the_first_property() {
        // Open first property from listings
    }

    @When("I read full property description")
    public void i_read_full_property_description() {
        // Read full description
    }

    @When("I search for locality {string}")
    public void i_search_for_locality(String locality) {
        // Enter locality in search box
    }

    @When("I select the first suggestion")
    public void i_select_the_first_suggestion() {
        // Select first suggestion from dropdown
    }

    @Then("I should see the title of the first locality card")
    public void i_should_see_the_title_of_the_first_locality_card() {
        // Print / validate title
    }

    @When("I explore the first locality card")
    public void i_explore_the_first_locality_card() {
        // Click Explore link
    }

    @When("I select property type {string}")
    public void i_select_property_type(String propertyType) {
        // Select property type filter
    }

    @Then("I should see the selected property type in results")
    public void i_should_see_the_selected_property_type_in_results() {
        // Validate filtered property type
    }

    @When("I select the first agent and {string}")
    public void i_select_the_first_agent_and(String action) {
        // action = "Contact" or "View Details"
    }

    @When("I fill contact form with name {string}, email {string}, mobile {string}")
    public void i_fill_contact_form_with_name_email_mobile(String name, String email, String mobile) {
        // Fill form
    }

    @When("I submit contact form")
    public void i_submit_contact_form() {
        // Submit form
    }

    @Then("I wait for OTP field")
    public void i_wait_for_otp_field() {
        // Wait for OTP input
    }

    @Then("I enter OTP manually")
    public void i_enter_otp_manually() {
        // Manual entry
    }

    @When("I verify OTP")
    public void i_verify_otp() {
        // Click verify
    }
}