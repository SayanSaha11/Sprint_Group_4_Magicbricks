
package stepDefinitions.stepDefinitions_ankan;

import io.cucumber.java.en.*;
import pages.pages_ankan.HomePage;
import pages.pages_ankan.SortPage;

import org.testng.Assert;

public class SortSteps {

    HomePage homePage = new HomePage();
    SortPage sortPage = new SortPage();

    @Given("user is on MagicBricks Rent search results page")
    public void userOnRentSearchResults() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
        homePage.enterCityAndSelectDropdown("Kolkata");  // ← renamed
        homePage.clickSearch();
        Thread.sleep(5000);
    }

    @When("user applies locality filter New Town and Posted By Owners")
    public void applyFilters() throws InterruptedException {
        sortPage.applyLocalityFilter();
        sortPage.applyPostedByOwners();
    }

    @When("user selects Sort by Price Low to High")
    public void sortByPrice() throws InterruptedException {
        sortPage.sortByPriceLowToHigh();
    }

    @Then("listings should be displayed in ascending order of price")
    public void verifyAscendingOrder() {
        Assert.assertTrue(sortPage.isPriceAscending(),
            "Prices are NOT in ascending order.");
    }
}