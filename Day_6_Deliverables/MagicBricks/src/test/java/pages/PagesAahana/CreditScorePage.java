package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreditScorePage extends BasePage {

    // ─── Locators ────────────────────────────────────────────────────────────────

    private final By firstNameField    = By.id("firstName");
    private final By lastNameField     = By.id("lastName");
    private final By emailField        = By.id("emailAddress");
    private final By mobileField       = By.id("mobileNumberCibil");

    // 2nd radio label — selects Female / second gender option
    private final By genderOption      = By.xpath("(//label[@class='has-radio__flx__cel--lbl'])[2]");

    private final By tncCheckbox       = By.xpath("//label[@for='tnc-checkbox']");
    private final By getFreeReportBtn  = By.xpath("//a[contains(text(),'Get Free Report')]");
    private final By verifyButton      = By.xpath("(//a[text()='Verify'])[1]");

    // ─── Constructor ─────────────────────────────────────────────────────────────

    public CreditScorePage(WebDriver driver) {
        super(driver);
    }

    // ─── Actions ──────────────────────────────────────────────────────────────────

    /**
     * Waits until the form is visible and ready for input.
     */
    public void waitForFormToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        System.out.println("Credit Score form loaded.");
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        System.out.println("Entered first name: " + firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
        System.out.println("Entered last name: " + lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        System.out.println("Entered email: " + email);
    }

    public void enterMobileNumber(String mobile) {
        driver.findElement(mobileField).sendKeys(mobile);
        System.out.println("Entered mobile number: " + mobile);
    }

    /**
     * Selects the second gender radio option (Female).
     * @throws InterruptedException 
     */
    public void selectGenderOption() throws InterruptedException {
        driver.findElement(genderOption).click();
        System.out.println("Selected gender option.");
        Thread.sleep(2000);
    }

    public void acceptTermsAndConditions() {
        driver.findElement(tncCheckbox).click();
        System.out.println("Accepted Terms and Conditions.");
    }

    public void clickGetFreeReport() throws InterruptedException {
        driver.findElement(getFreeReportBtn).click();
        System.out.println("Clicked Get Free Report.");
        // Wait for OTP / verification page to load
        Thread.sleep(30000);
    }

    /**
     * Clicks the first "Verify" button on the OTP / verification screen.
     */
    public void clickVerify() {
        wait.until(ExpectedConditions.elementToBeClickable(verifyButton)).click();
        System.out.println("Clicked Verify button.");
    }

    /**
     * Returns true if the Verify button is present on the page,
     * confirming the form was submitted and the verification step was reached.
     */
    public boolean isVerifyButtonPresent() {
        return !driver.findElements(verifyButton).isEmpty();
    }
}