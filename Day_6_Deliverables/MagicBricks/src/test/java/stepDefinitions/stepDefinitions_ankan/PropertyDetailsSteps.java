package stepDefinitions.stepDefinitions_ankan;

import io.cucumber.java.en.*;
import pages.pages_ankan.HomePage;
import pages.pages_ankan.PropertyDetailsPage;

import org.testng.Assert;

public class PropertyDetailsSteps {

    HomePage           homePage     = new HomePage();
    PropertyDetailsPage propPage    = new PropertyDetailsPage();

    @Given("user searches for properties in Kolkata on Rent page")
    public void searchKolkataRent() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
        homePage.enterCityAndSelectDropdown("Kolkata");  // ← renamed
        homePage.clickSearch();
    }

    @When("user clicks on the first property listing")
    public void clickFirstProperty() throws InterruptedException {
        propPage.clickFirstProperty();
        propPage.switchToNewTab();
    }

    @Then("full property details should be displayed")
    public void verifyPropertyDetails() throws InterruptedException {
        Assert.assertTrue(propPage.arePropertyDetailsDisplayed(),
            "Property details are not fully displayed.");
    }
}