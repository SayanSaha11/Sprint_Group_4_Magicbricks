package stepDefinitions;

import io.cucumber.java.en.*;
import pages.AgentDetailsPage;
import pages.HomePage;

import org.testng.Assert;

public class AgentDetailsSteps {

    HomePage         homePage  = new HomePage();
    AgentDetailsPage agentPage = new AgentDetailsPage();

    @Given("user is on Rent search results with New Town filter applied")
    public void userOnResultsWithFilter() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
        homePage.enterCityAndSelectDropdown("Kolkata");  // ← renamed
        homePage.clickSearch();
        Thread.sleep(5000);
        agentPage.applyLocalityFilter();
    }

    @When("user clicks Top Agents and views first agent details")
    public void clickTopAgentAndView() throws InterruptedException {
        agentPage.clickTopAgents();
        agentPage.clickViewDetails();
    }

    @Then("agent name photo contact number and listing history should be displayed")
    public void verifyAgentDetails() {
        Assert.assertTrue(agentPage.areAgentDetailsDisplayed(),
            "Agent details are not fully displayed.");
    }
}