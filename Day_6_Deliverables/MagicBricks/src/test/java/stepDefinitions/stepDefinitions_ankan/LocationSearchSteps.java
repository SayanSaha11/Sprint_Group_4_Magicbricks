package stepDefinitions.stepDefinitions_ankan;

import io.cucumber.java.en.*;
import pages.pages_ankan.HomePage;
import pages.pages_ankan.LocationSearchPage;
import utils.utils_ankan.ExcelUtils;

import org.testng.Assert;

public class LocationSearchSteps {

    HomePage           homePage     = new HomePage();
    LocationSearchPage locationPage = new LocationSearchPage();

    // ─── SHARED ──────────────────────────────────────────────
    @Given("user opens MagicBricks and goes to Rent tab")
    public void openMagicBricksRent() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
    }

    @And("user clicks search button")
    public void clickSearch() throws InterruptedException {
        homePage.clickSearch();
    }

    // ─── TC_02 ───────────────────────────────────────────────
    @When("user types {string} and selects from dropdown")
    public void userTypesAndSelectsDropdown(String city) throws InterruptedException {
        homePage.enterCityAndSelectDropdown(city);
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() throws InterruptedException {
        Assert.assertTrue(locationPage.areResultsDisplayed(),
            "Results were not displayed after search.");
    }

    // ─── TC_04 ───────────────────────────────────────────────
    @When("user types invalid city using test data {string}")
    public void userTypesInvalidCityUsingTestData(String id) throws Exception {

        String city = ExcelUtils.getInvalidCityById(id);

        System.out.println("Using invalid city: " + city);

        homePage.enterInvalidCity(city);
    }

    @Then("an error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        String msg = locationPage.getErrorMessageText();
        System.out.println("Error Message: " + msg);
        Assert.assertFalse(msg.isEmpty(),
            "Error message was not displayed.");
    }
}