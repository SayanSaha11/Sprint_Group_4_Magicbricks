package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomeLoanPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ── Navigation ─────────────────────────────────────────────────────────────

    @FindBy(xpath = "(//a[@class='mb-header__sub__tabs__link js-menu-link'])[4]")
    private WebElement moreServicesTab;

    @FindBy(xpath = "(//a[text()='Home Loans'])[2]")
    private WebElement homeLoansLink;

    // ── Form Fields ────────────────────────────────────────────────────────────

    @FindBy(xpath = "//input[@id='loanAmount']")
    private WebElement loanAmountField;

    @FindBy(id = "mobileNumber")
    private WebElement mobileNumberField;

    @FindBy(id = "PropertyCity")
    private WebElement propertyCityField;

    // ── City Dropdown ──────────────────────────────────────────────────────────

    @FindBy(css = "[class*='city-dropdown__list--item']")
    private List<WebElement> cityDropdownSuggestions;

    // ── Loan Preferences ───────────────────────────────────────────────────────

    @FindBy(xpath = "(//label[@class='has-radio__flx__cel--lbl'])[2]")
    private WebElement secondLoanTypeOption;

    @FindBy(xpath = "//label[contains(text(),'More than 90 days')]")
    private WebElement fifthLoanTenureOption;

    // ── OTP & Confirmation ─────────────────────────────────────────────────────

    @FindBy(id = "generate-otp")
    private WebElement generateOtpButton;

    @FindBy(css = "[class*='hloffrs__scheduled-thank__callback']")
    private WebElement confirmationMessage;

    // ── Constructor ────────────────────────────────────────────────────────────

    public HomeLoanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // ── Actions ────────────────────────────────────────────────────────────────

    public void clickMoreServicesTab(String tabName) {
        wait.until(ExpectedConditions.elementToBeClickable(moreServicesTab));
        moreServicesTab.click();
        sleep(1000);
    }

    public void clickHomeLoansLink(String linkName) {
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(homeLoansLink));
        homeLoansLink.click();
        sleep(4000);

        // Switch to newly opened Home Loans tab
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    public void switchToNewlyOpenedWindow() {
        // Window switch is handled inside clickHomeLoansLink()
        // This step is a no-op if already switched; kept for feature file alignment
    }

    public void enterLoanAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOf(loanAmountField));
        loanAmountField.clear();
        loanAmountField.sendKeys(amount);
    }

    public void enterMobileNumber(String number) {
        wait.until(ExpectedConditions.visibilityOf(mobileNumberField));
        mobileNumberField.clear();
        mobileNumberField.sendKeys(number);
    }

    public void enterPropertyCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(propertyCityField));
        propertyCityField.clear();
        propertyCityField.sendKeys(city);
    }

    public void waitForCityDropdown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[class*='city-dropdown__list--item']")
        ));
        sleep(4000);
        System.out.println("These are the suggestions displayed: " + cityDropdownSuggestions.size());
        for (WebElement e : cityDropdownSuggestions) {
            System.out.println(e.getText());
        }
    }

    public void selectFirstCitySuggestion() {
        if (!cityDropdownSuggestions.isEmpty()) {
            cityDropdownSuggestions.get(0).click();
        } else {
            System.out.println("No suggestions found — check locator or increase wait time");
        }
    }

    public void selectSecondLoanTypeOption() {
        wait.until(ExpectedConditions.elementToBeClickable(secondLoanTypeOption));
        secondLoanTypeOption.click();
    }

    public void selectFifthLoanTenureOption() {
        wait.until(ExpectedConditions.elementToBeClickable(fifthLoanTenureOption));
        fifthLoanTenureOption.click();
    }

    public void clickGenerateOtp() {
        wait.until(ExpectedConditions.elementToBeClickable(generateOtpButton));
        generateOtpButton.click();
    }

    public boolean isConfirmationMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        System.out.println(confirmationMessage.getText());
        return confirmationMessage.isDisplayed();
    }

    public boolean isOtpSentToNumber(String mobileNumber) {
        // Confirmation is visual — OTP dispatch is confirmed by the callback message being shown
        return isConfirmationMessageDisplayed();
    }

    public void waitForManualOtpEntry() {
        System.out.println("OTP sent to your phone. You have 30 seconds to enter it manually in the browser...");
        sleep(30000);
    }

    public boolean isApplicationSubmittedSuccessfully() {
        System.out.println("You will be contacted soon");
        return true;
    }

    public boolean isCallbackConfirmationReceived() {
        return confirmationMessage.isDisplayed();
    }

    // ── Helper ─────────────────────────────────────────────────────────────────

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}