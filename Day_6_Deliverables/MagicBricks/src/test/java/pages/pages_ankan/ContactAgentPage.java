package pages.pages_ankan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ContactAgentPage extends BasePage {

    @FindBy(xpath = "//div[text()='Top Localities']")
    private WebElement topLocalitiesFilter;

    @FindBy(xpath = "//label[text()='New Town']")
    private WebElement newTownLabel;

    @FindBy(xpath = "(//div[@class='filter__component__cta-done' and text()='Done'])[2]")
    private WebElement doneButton;

    @FindBy(xpath = "//span[normalize-space()='Contact Owner']")
    private List<WebElement> contactOwnerBtns;

    @FindBy(xpath = "//label[text()='Your Name']/following::input[1]")
    private WebElement emailField;

    @FindBy(id = "userMobile")
    private WebElement mobileField;

    @FindBy(id = "userName")
    private WebElement nameField;

    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueBtn;

    @FindBy(xpath = "//button[normalize-space()='Verify']")
    private WebElement verifyBtn;

    // Step 1 — Apply locality filter
    public void applyNewTownFilter() throws InterruptedException {
        Thread.sleep(9000);
        topLocalitiesFilter.click();
        Thread.sleep(9000);
        newTownLabel.click();
        Thread.sleep(9000);
        doneButton.click();
        Thread.sleep(9000);
    }

    // Step 2 — Click first Contact Owner button
    public void clickContactOwner() throws InterruptedException {
        Thread.sleep(9000);
        wait.until(ExpectedConditions.visibilityOfAllElements(contactOwnerBtns));
        contactOwnerBtns.get(0).click();
        Thread.sleep(9000);
    }

    // Step 3 — Fill contact form
    public void fillContactForm(String email, String mobile, String name)
            throws InterruptedException {

        // Fill email
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);

        // Fill mobile
        mobileField.sendKeys(mobile);
        Thread.sleep(9000);

        // Fill name — clear first then type
        WebElement nameInput = wait.until(
            ExpectedConditions.elementToBeClickable(nameField)
        );
        nameInput.clear();
        nameInput.sendKeys(name);
        Thread.sleep(9000);
    }

    // Step 4 — Click Continue
    public void clickContinue() throws InterruptedException {
        continueBtn.click();
        Thread.sleep(9000);
    }

    // Step 5 — Wait for OTP (manual entry by tester)
    public void waitForManualOtpEntry() throws InterruptedException {
        System.out.println("==============================");
        System.out.println("⏸️  OTP SENT TO YOUR PHONE");
        System.out.println("YOU HAVE 30 SECONDS TO ENTER OTP MANUALLY IN BROWSER");
        System.out.println("==============================");
        Thread.sleep(30000); // 30 seconds for manual OTP entry
    }

    // Step 6 — Click Verify after OTP entered
    public void clickVerify() throws InterruptedException {
        // Find fresh — page may have updated after OTP
        WebElement verify = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Verify']")
            )
        );
        verify.click();
        Thread.sleep(3000);
        System.out.println("✅ OTP Verified — Contact details accessed successfully.");
    }
}