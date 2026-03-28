package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.ContactAgentPage;
import pages.HomePage;
import utils.ExcelUtils;

public class ContactAgentSteps {

    HomePage         homePage    = new HomePage();
    ContactAgentPage contactPage = new ContactAgentPage();

    @Given("user searches properties in Kolkata to contact agent")
    public void searchPropertiesForContact() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
        homePage.enterCityAndSelectDropdown("Kolkata");
        homePage.clickSearch();
    }

    @When("user applies New Town filter and clicks Contact Owner")
    public void applyFilterAndClickContact() throws InterruptedException {
        contactPage.applyNewTownFilter();
        contactPage.clickContactOwner();
    }

//    @When("user fills contact form with name email and mobile")
//    public void fillContactForm() throws InterruptedException {
//        contactPage.fillContactForm(
//            "ankan.nayak.1412@gmail.com",  // email
//            "9732109578",                   // mobile
//            "Ankan"                         // name
//        );
//        contactPage.clickContinue();
//    }
    
//    @When("user fills contact form with name email and mobile")
//    public void fillContactForm() throws InterruptedException {
//
//        String email  = ExcelUtils.getCellData(1, 0);
//        String mobile = ExcelUtils.getCellData(1, 1);
//        String name   = ExcelUtils.getCellData(1, 2);
//
//        contactPage.fillContactForm(email, mobile, name);
//        contactPage.clickContinue();
//    }
    
    @When("user fills contact form using test data {string}")
    public void fillContactForm(String id) throws InterruptedException {

        String[] data = ExcelUtils.getContactDataById(id);

        Assert.assertNotNull(data, "❌ No data found for id: " + id);

        String email  = data[0];
        String mobile = data[1];
        String name   = data[2];

        System.out.println("Using ID: " + id);

        contactPage.fillContactForm(email, mobile, name);
        contactPage.clickContinue();
    }    

    @When("user waits and enters OTP manually")
    public void waitAndEnterOtp() throws InterruptedException {
        contactPage.waitForManualOtpEntry();
    }

    @Then("user clicks verify and contact details should be accessed")
    public void verifyOtpAndAccess() throws InterruptedException {
        contactPage.clickVerify();
    }
}