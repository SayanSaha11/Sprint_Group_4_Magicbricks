package stepDefinitions;

import com.aventstack.extentreports.util.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FeedbackReviewPage;
import pages.HomePage;
import utils.ExcelUtils;

public class FeedbackReviewSteps {

    HomePage           homePage   = new HomePage();
    FeedbackReviewPage reviewPage = new FeedbackReviewPage();

    @Given("user searches properties in Kolkata for review")
    public void searchForReview() throws InterruptedException {
        homePage.openMagicBricks();
        homePage.clickRentTab();
        homePage.enterCityAndSelectDropdown("Kolkata");  // ← fixed method name
        homePage.clickSearch();
    }

//    @When("user clicks on a property and writes a review")
//    public void writeReview() throws InterruptedException {
//        reviewPage.clickFirstProperty();
//        reviewPage.switchToNewTab();
//        reviewPage.clickWriteReview();
//
//        String reviewText = "Nice project with good ventilation and decent natural light. "
//            + "The rooms are well-sized and suitable for small families or working professionals. "
//            + "Basic amenities are available, and the locality is fairly peaceful. "
//            + "Maintenance could be slightly better, but overall it offers good value for money.";
//
//        reviewPage.fillReview(reviewText, "Great Property");
//        reviewPage.selectRelation();
//        reviewPage.selectRating();
//    }
    
    @When("user clicks on a property and writes a review using test data {string}")
    public void writeReview(String id) throws InterruptedException {

        reviewPage.clickFirstProperty();
        reviewPage.switchToNewTab();
        reviewPage.clickWriteReview();

        // 🔥 Fetch data from Excel
        String[] data = ExcelUtils.getReviewDataById(id);

        // ✅ NULL check (VERY IMPORTANT)
        Assert.notNull(data, "❌ No data found in Excel for id: " + id);

        String title  = data[0];
        String review = data[1];

        // ✅ Debug (helps a lot)
        System.out.println("Using ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Review: " + review);

        // ⚠️ Make sure order matches method
        reviewPage.fillReview(review, title); 
        // OR if method expects (title, review) → swap

        reviewPage.selectRelation();
        reviewPage.selectRating();
    }

    @Then("the review should be submitted successfully")
    public void verifyReviewSubmitted() throws InterruptedException {
        reviewPage.submitReview();
    }
}