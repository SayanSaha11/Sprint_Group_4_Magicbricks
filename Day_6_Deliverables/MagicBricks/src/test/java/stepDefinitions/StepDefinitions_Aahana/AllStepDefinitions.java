package StepDefinitions_Aahana;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils_Aahana.DriverManager;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import PagesAahana.HomeLoanPage;
import PagesAahana.KolkataRealEstatePage;
import PagesAahana.ShareRequirementsPage;
import PagesAahana.DeveloperLoungePage;
import PagesAahana.MagicBricksHomePage;
import PagesAahana.InteriorDesignPage;
import PagesAahana.CreditScorePage;
import PagesAahana.LocalityTrendsPage;
import PagesAahana.RatesAndTrendsPage;
import PagesAahana.PropWorthPage;

import java.util.List;

public class AllStepDefinitions {

    // ─── Page Object References ───────────────────────────────────────────────────

    private final HomeLoanPage         homeLoanPage;
    private final MagicBricksHomePage  magicBricksHomePage;
    private final DeveloperLoungePage  developerLoungePage;
    private final InteriorDesignPage   interiorDesignPage;
    private final PropWorthPage        propWorthPage;

    private KolkataRealEstatePage  kolkataPage;
    private ShareRequirementsPage  shareRequirementsPage;
    private CreditScorePage        creditScorePage;
    private RatesAndTrendsPage     ratesAndTrendsPage;
    private LocalityTrendsPage     localityTrendsPage;
    private String                 originalWindowHandle;

    // ─── Constructor ──────────────────────────────────────────────────────────────

    public AllStepDefinitions() {
        this.homeLoanPage        = new HomeLoanPage(DriverManager.getDriver());
        this.magicBricksHomePage = new MagicBricksHomePage(DriverManager.getDriver());
        this.developerLoungePage = new DeveloperLoungePage(DriverManager.getDriver());
        this.interiorDesignPage  = new InteriorDesignPage(DriverManager.getDriver());
        this.propWorthPage       = new PropWorthPage(DriverManager.getDriver());
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // HomeLoanSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @Given("I navigate to the MagicBricks Kolkata residential real estate page")
    public void iNavigateToMagicBricksKolkataPage() {
        DriverManager.getDriver().get(
                "https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata"
        );
        try { Thread.sleep(4000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Given("the browser is launched with anti-automation detection settings")
    public void theBrowserIsLaunchedWithAntiAutomationSettings() {
        // ChromeOptions with anti-automation flags are applied at driver init in DriverManager.
        // This step is intentionally a no-op — it serves as a documentation/readability step.
        System.out.println("Browser launched with anti-automation detection settings.");
    }

    @When("I click on the {string} tab in the navigation menu")
    public void iClickOnTabInNavigationMenu(String tabName) {
        homeLoanPage.clickMoreServicesTab(tabName);
    }

    @And("I click on the {string} link")
    public void iClickOnLink(String linkName) {
        homeLoanPage.clickHomeLoansLink(linkName);
    }

    @And("I switch to the newly opened Home Loans window")
    public void iSwitchToNewlyOpenedHomeLoanWindow() {
        homeLoanPage.switchToNewlyOpenedWindow();
    }

    @And("I enter {string} in the Loan Amount field")
    public void iEnterLoanAmount(String amount) {
        homeLoanPage.enterLoanAmount(amount);
    }

    @And("I enter {string} in the Mobile Number field")
    public void iEnterMobileNumber(String number) {
        homeLoanPage.enterMobileNumber(number);
    }

    @And("I enter {string} in the Property City field")
    public void iEnterPropertyCity(String city) {
        homeLoanPage.enterPropertyCity(city);
    }

    @And("I wait for the city dropdown suggestions to appear")
    public void iWaitForCityDropdownSuggestions() {
        homeLoanPage.waitForCityDropdown();
    }

    @And("I select the first city suggestion from the dropdown")
    public void iSelectFirstCitySuggestion() {
        homeLoanPage.selectFirstCitySuggestion();
    }

    @And("I select the second option for loan type preference")
    public void iSelectSecondLoanTypeOption() {
        homeLoanPage.selectSecondLoanTypeOption();
    }

    @And("I select the fifth option for loan tenure preference")
    public void iSelectFifthLoanTenureOption() {
        homeLoanPage.selectFifthLoanTenureOption();
    }

    @And("I click the {string} button")
    public void iClickTheButton(String buttonName) {
        switch (buttonName) {
            case "Generate OTP":
                homeLoanPage.clickGenerateOtp();
                break;
            default:
                throw new IllegalArgumentException("No action mapped for button: " + buttonName);
        }
    }

    @Then("a confirmation message should be displayed on the screen")
    public void aConfirmationMessageShouldBeDisplayed() {
        Assert.assertTrue(homeLoanPage.isConfirmationMessageDisplayed(),
                "Confirmation message was not displayed after clicking Generate OTP.");
    }

    @And("an OTP should be sent to the registered mobile number {string}")
    public void anOtpShouldBeSentToNumber(String mobileNumber) {
        Assert.assertTrue(homeLoanPage.isOtpSentToNumber(mobileNumber),
                "OTP confirmation not shown for mobile number: " + mobileNumber);
    }

    @When("I manually enter the OTP within {int} seconds")
    public void iManuallyEnterOtpWithinSeconds(int seconds) {
        homeLoanPage.waitForManualOtpEntry();
    }

    @Then("the application should be submitted successfully")
    public void theApplicationShouldBeSubmittedSuccessfully() {
        Assert.assertTrue(homeLoanPage.isApplicationSubmittedSuccessfully(),
                "Application was not submitted successfully.");
    }

    @And("I should receive a callback confirmation message")
    public void iShouldReceiveCallbackConfirmation() {
        Assert.assertTrue(homeLoanPage.isCallbackConfirmationReceived(),
                "Callback confirmation message was not displayed.");
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // MagicBricks_ShareRequirementsSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @Given("the user is on the MagicBricks Kolkata residential real estate page")
    public void theUserIsOnTheMagicBricksKolkataResidentialRealEstatePage()
            throws InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        kolkataPage = new KolkataRealEstatePage(driver);
        kolkataPage.navigateTo();
    }

    @When("the user clicks on the Post Property tab in the navigation menu")
    public void theUserClicksOnThePostPropertyTabInTheNavigationMenu()
            throws InterruptedException {
        kolkataPage.clickPostPropertyTab();
    }

    @And("the user clicks on Share Requirement link")
    public void theUserClicksOnShareRequirementLink() {
        originalWindowHandle = kolkataPage.clickShareRequirement();
    }

    @And("the user switches to the new window")
    public void theUserSwitchesToTheNewWindow() throws InterruptedException {
        shareRequirementsPage = kolkataPage.switchToShareRequirementsWindow(originalWindowHandle);
    }

    @And("the user selects property types as {string} and {string}")
    public void theUserSelectsPropertyTypes(String type1, String type2)
            throws InterruptedException {
        shareRequirementsPage.selectPropertyTypes();
    }

    @And("the user selects budget as {string}")
    public void theUserSelectsBudget(String budget) throws InterruptedException {
        shareRequirementsPage.selectBudget();
    }

    @And("the user selects bedroom preference as {string}")
    public void theUserSelectsBedroomPreference(String bedroom) throws InterruptedException {
        shareRequirementsPage.selectBedroom();
    }

    @And("the user selects floor preference as {string}")
    public void theUserSelectsFloorPreference(String floor) throws InterruptedException {
        shareRequirementsPage.selectFloorPreference();
    }

    @And("the user sets area range from {string} to {string}")
    public void theUserSetsAreaRange(String min, String max) throws InterruptedException {
        shareRequirementsPage.setAreaRange();
    }

    @And("the user enters city as {string}")
    public void theUserEntersCity(String city) throws InterruptedException {
        shareRequirementsPage.enterCity(city);
    }

    @And("the user enters locality as {string} and selects the first suggestion")
    public void theUserEntersLocalityAndSelectsTheFirstSuggestion(String locality)
            throws InterruptedException {
        shareRequirementsPage.enterLocalityAndSelectFirstSuggestion(locality);
    }

    @And("the user accepts the Terms and Conditions")
    public void theUserAcceptsTheTermsAndConditions() throws InterruptedException {
        shareRequirementsPage.acceptTermsAndConditions();
    }

    @And("the user clicks the Submit button")
    public void theUserClicksTheSubmitButton() throws InterruptedException {
        shareRequirementsPage.clickSubmit();
    }

    @Then("the requirement form should be submitted successfully")
    public void theRequirementFormShouldBeSubmittedSuccessfully() {
        boolean submitted = shareRequirementsPage.isFormSubmitted();
        org.junit.Assert.assertTrue(
            "Expected the requirement form to be submitted successfully, but it was not.",
            submitted
        );
        System.out.println("Requirement form submitted successfully.");
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // DeveloperLoungeSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @When("the user navigates to the {string} section")
    public void theUserNavigatesToSection(String sectionName) {
        magicBricksHomePage.navigateToSection(sectionName);
    }

    @And("the user clicks on {string}")
    public void theUserClicksOn(String linkText) throws InterruptedException {
        magicBricksHomePage.clickLink(linkText);
    }

    @And("the user clicks on Mohit Goel's profile circle")
    public void theUserClicksOnMohitGoelProfileCircle() throws InterruptedException {
        developerLoungePage.clickDeveloperProfile("Mohit Goel");
    }

    @And("the user clicks on the third insight card")
    public void theUserClicksOnThirdInsightCard() throws InterruptedException {
        developerLoungePage.clickInsightCard(3);
    }

    @Then("the video should start playing")
    public void theVideoShouldStartPlaying() {
        boolean isPlaying = developerLoungePage.isVideoPlaying();
        Assert.assertTrue(isPlaying, "Expected the video to be playing, but it was not.");
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // InteriorDesignSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @When("I click on the {string} tab in the nav menu")
    public void iClickHomeInteriorTab(String tabName) {
        interiorDesignPage.clickHomeInteriorTab();
    }
    
    @And("I click on the {string} link there")
    public void iclickHomeInteriorDesignServices(String tabname) {
    	interiorDesignPage.clickInteriorDesignLink();
    }
    
    @And("I switch to the newly opened window")
    public void iSwitchToNewlyOpenedWindow() {
        interiorDesignPage.switchToNewlyOpenedWindow();
    }

    @And("I enter {string} in the Name field")
    public void iEnterName(String name) {
        interiorDesignPage.enterName(name);
    }

    @And("I enter {string} in the Phone Number field")
    public void iEnterPhoneNumber(String phone) {
        interiorDesignPage.enterPhoneNumber(phone);
    }

    @And("I click the {string} button now")
    public void iClickTheOTPButton(String buttonName) throws InterruptedException {
    	interiorDesignPage.clickBookSlot();
    	Thread.sleep(30000);
    }
    
    @And("I click the {string} button to proceed")
    public void iClickSubmitButton(String buttonname) throws InterruptedException {
    	Thread.sleep(2000);
    	interiorDesignPage.clickSubmit();
    }

    @And("I wait for the timeline options to appear")
    public void iWaitForTimelineOptions() {
        interiorDesignPage.waitForTimelineOptions();
    }

    @And("I select {string} as the project timeline")
    public void iSelectProjectTimeline(String timeline) {
        interiorDesignPage.selectProjectTimeline(timeline);
    }

    @And("I wait for the budget options to appear")
    public void iWaitForBudgetOptions() {
        interiorDesignPage.waitForBudgetOptions();
    }

    @And("I select {string} as the budget range")
    public void iSelectBudgetRange(String budget) {
        interiorDesignPage.selectBudgetRange(budget);
    }

    @And("I click the {string} button to confirm")
    public void iClickButtonToConfirm(String buttonName) {
        switch (buttonName) {
            case "Submit":
                interiorDesignPage.clickFinalSubmit();
                break;
            default:
                throw new IllegalArgumentException("No action mapped for button (confirm): " + buttonName);
        }
    }

    @Then("the user should be redirected to the property details form")
    public void theUserShouldBeRedirectedToPropertyDetailsForm() {
        Assert.assertTrue(interiorDesignPage.isRedirectedToPropertyDetailsForm(),
                "User was not redirected to the property details form.");
    }

    @Then("a consultation slot should be booked successfully")
    public void aConsultationSlotShouldBeBookedSuccessfully() {
        Assert.assertTrue(interiorDesignPage.isSlotBookedSuccessfully(),
                "Consultation slot was not booked successfully.");
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // MagicBricks_CreditScoreSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @When("the user clicks on the Financial Services tab in the navigation menu")
    public void theUserClicksOnTheFinancialServicesTabInTheNavigationMenu()
            throws InterruptedException {
        kolkataPage.clickFinancialServicesTab();
    }

    @And("the user clicks on the Check Credit Score link")
    public void theUserClicksOnTheCheckCreditScoreLink() {
        originalWindowHandle = kolkataPage.clickCheckCreditScore();
    }

    @And("the user switches to the new Credit Score window")
    public void theUserSwitchesToTheNewCreditScoreWindow() {
        creditScorePage = kolkataPage.switchToCreditScoreWindow(originalWindowHandle);
        creditScorePage.waitForFormToLoad();
    }

    @And("the user enters first name as {string}")
    public void theUserEntersFirstNameAs(String firstName) {
        creditScorePage.enterFirstName(firstName);
    }

    @And("the user enters last name as {string}")
    public void theUserEntersLastNameAs(String lastName) {
        creditScorePage.enterLastName(lastName);
    }

    @And("the user enters email address as {string}")
    public void theUserEntersEmailAddressAs(String email) {
        creditScorePage.enterEmail(email);
    }

    @And("the user enters mobile number as {string}")
    public void theUserEntersMobileNumberAs(String mobile) {
        creditScorePage.enterMobileNumber(mobile);
    }

    @And("the user selects the gender option")
    public void theUserSelectsTheGenderOption() throws InterruptedException {
        creditScorePage.selectGenderOption();
    }
    
    @And("the user ticks the Terms and Conditions button")
    public void thUserTicksTermsAndConditionsButton() {
    	creditScorePage.acceptTermsAndConditions();
    }

    @And("the user clicks on Get Free Report")
    public void theUserClicksOnGetFreeReport() throws InterruptedException {
        creditScorePage.clickGetFreeReport();
    }

    @Then("the user should be able to verify the credit score report")
    public void theUserShouldBeAbleToVerifyTheCreditScoreReport() {
        boolean verifyPresent = creditScorePage.isVerifyButtonPresent();
        org.junit.Assert.assertTrue(
            "Expected the Verify button to be present after submitting the Credit Score form.",
            verifyPresent
        );
        creditScorePage.clickVerify();
        System.out.println("Verify button clicked — Credit Score report verification initiated.");
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // MagicBricks_RatesAndTrendsSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @When("the user clicks on the Rates and Trends option in the navigation menu")
    public void theUserClicksOnTheRatesAndTrendsOptionInTheNavigationMenu()
            throws InterruptedException {
        ratesAndTrendsPage = kolkataPage.clickRatesAndTrends();
        System.out.println("Clicked Rates and Trends in navigation.");
    }

    @And("the user selects Hyderabad from the city list")
    public void theUserSelectsHyderabadFromTheCityList() throws InterruptedException {
        ratesAndTrendsPage.selectHyderabad();
        System.out.println("Selected Hyderabad from the city list.");
    }

    @And("the user clicks on View Trends for the first property")
    public void theUserClicksOnViewTrendsForTheFirstProperty() throws InterruptedException {
        localityTrendsPage = ratesAndTrendsPage.clickViewTrendsForFirstProperty();
        System.out.println("Clicked View Trends for the first property.");
    }

    @And("the user clicks on Locality Snapshot")
    public void theUserClicksOnLocalitySnapshot() {
        localityTrendsPage.clickLocalitySnapshot();
        System.out.println("Clicked Locality Snapshot tab.");
    }

    @Then("the locality details should be printed")
    public void thePropertyRatesAndTrendsPageForTheLocalityShouldBeDisplayed() {
    	localityTrendsPage.printLocalityDetails();
        List<String> details = localityTrendsPage.getLocalityInfoTexts();
        org.junit.Assert.assertFalse(
            "Expected locality details to be present, but none were found.",
            details.isEmpty()
        );
        System.out.println("Total locality info blocks found: " + details.size());
    }


    // ═══════════════════════════════════════════════════════════════════════════════
    // PropWorthSteps
    // ═══════════════════════════════════════════════════════════════════════════════

    @Given("the user is on the MagicBricks Kolkata residential page")
    public void theUserIsOnMagicBricksKolkataResidentialPage() {
        DriverManager.getDriver().get(
                "https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata"
        );
        sleep(4000);
    }

    @When("the user navigates to PropWorth from the top menu")
    public void theUserNavigatesToPropWorth() {
        propWorthPage.navigateToPropWorth();
    }

    @And("the user searches for locality {string}")
    public void theUserSearchesForLocality(String locality) {
        propWorthPage.searchLocality(locality);
    }

    @And("the user selects the first suggestion")
    public void theUserSelectsFirstSuggestion() {
        propWorthPage.selectFirstSuggestion();
    }

    @And("the user clicks Get Estimate")
    public void theUserClicksGetEstimate() {
        propWorthPage.clickGetEstimate();
    }

    @And("the user selects property type as {string}")
    public void theUserSelectsPropertyType(String propertyType) {
        propWorthPage.selectPropertyType(propertyType);
    }

    @And("the user selects BHK as {string}")
    public void theUserSelectsBhk(String bhk) {
        propWorthPage.selectBhk(bhk);
    }

    @And("the user enters super area as {string} square feet")
    public void theUserEntersSuperArea(String area) {
        propWorthPage.enterSuperArea(area);
    }

    @And("the user adds {string} covered parking")
    public void theUserAddsCoveredParking(String count) {
        propWorthPage.addCoveredParking(count);
    }

    @And("the user selects property age as {string}")
    public void theUserSelectsPropertyAge(String age) {
        propWorthPage.selectPropertyAge(age);
    }

    @And("the user enters interior amount as {string}")
    public void theUserEntersInteriorAmount(String amount) {
        propWorthPage.enterInteriorAmount(amount);
    }

    @And("the user enters total number of floors as {string}")
    public void theUserEntersTotalFloors(String floors) {
        propWorthPage.enterTotalFloors(floors);
    }

    @And("the user clicks the final Get Estimate button")
    public void theUserClicksFinalGetEstimate() {
        propWorthPage.clickFinalGetEstimate();
    }

    @Then("the user is redirected to the login page for credentials")
    public void theUserIsRedirectedToLoginPage() {
        Assert.assertTrue(propWorthPage.isRedirectedToLoginPage(),
                "User was not redirected to the login page after clicking Get Estimate.");
    }

    // ── Helper ─────────────────────────────────────────────────────────────────

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}