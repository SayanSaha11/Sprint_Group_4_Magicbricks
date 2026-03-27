package stepDefinitions;

import io.cucumber.java.en.*;
import pages.HomePage;
import pages.LocationDetailsPage;

import org.testng.Assert;

public class LocationDetailsSteps {

    HomePage            homePage     = new HomePage();
    LocationDetailsPage locationPage = new LocationDetailsPage();

    @Given("user searches properties in Kolkata for location details")
    public void searchForLocationDetails() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
        homePage.enterCityAndSelectDropdown("Kolkata");  // ← renamed
        homePage.clickSearch();
    }

    @When("user clicks on first property to view location")
    public void clickPropertyForLocation() throws InterruptedException {
        locationPage.clickFirstProperty();
        locationPage.switchToNewTab();
    }

    @Then("location details should contain Kolkata")
    public void verifyLocationDetails() {
        Assert.assertTrue(locationPage.isLocationContainsKolkata(),
            "Location does NOT contain Kolkata!");
    }
}