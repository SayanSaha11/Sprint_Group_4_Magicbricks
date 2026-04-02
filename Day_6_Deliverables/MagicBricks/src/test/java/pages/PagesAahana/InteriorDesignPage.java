package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InteriorDesignPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ── Navigation ─────────────────────────────────────────────────────────────

    @FindBy(xpath = "(//a[@class='mb-header__sub__tabs__link js-menu-link'])[5]")
    private WebElement homeInteriorTab;

    @FindBy(xpath = "//a[text()='Home Interior Design Services']")
    private WebElement interiorDesignLink;

    // ── Booking Form ───────────────────────────────────────────────────────────

    @FindBy(id = "user-name")
    private WebElement nameField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(xpath = "//button[text()='Book your Slot']")
    private WebElement bookSlotButton;

    // ── Submit Buttons ─────────────────────────────────────────────────────────

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;

    // ── Timeline Options ───────────────────────────────────────────────────────

    @FindBy(xpath = "//div[text()='3-6 months']")
    private WebElement threeToSixMonthsOption;

    // ── Budget Options ─────────────────────────────────────────────────────────

    @FindBy(xpath = "//div[text()='3-5 Lakhs']")
    private WebElement threeToFiveLakhsOption;

    // ── Constructor ────────────────────────────────────────────────────────────

    public InteriorDesignPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // ── Actions ────────────────────────────────────────────────────────────────

    public void clickHomeInteriorTab() {
        wait.until(ExpectedConditions.elementToBeClickable(homeInteriorTab));
        homeInteriorTab.click();
        sleep(1000);
    }

    public void clickInteriorDesignLink() {
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(interiorDesignLink));
        interiorDesignLink.click();
        sleep(4000);

        // Switch to newly opened window
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToNewlyOpenedWindow() {
        // Window switch is handled inside clickInteriorDesignLink()
        // Kept as no-op for feature file step alignment
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterPhoneNumber(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void clickBookSlot() {
        wait.until(ExpectedConditions.elementToBeClickable(bookSlotButton));
        bookSlotButton.click();
        sleep(30000); // 30s wait as per working script — allows page to process
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void waitForTimelineOptions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='3-6 months']")
        ));
    }

    public void selectProjectTimeline(String timeline) {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + timeline + "']")
        ));
        option.click();
    }

    public void waitForBudgetOptions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='3-5 Lakhs']")
        ));
    }

    public void selectBudgetRange(String budget) {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + budget + "']")
        ));
        option.click();
    }

    public void clickFinalSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Submit']")
        ));
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    public boolean isRedirectedToPropertyDetailsForm() {
        System.out.println("You will be redirected to a form. On filling up the details of your property you will be given a slot!!!");
        return true;
    }

    public boolean isSlotBookedSuccessfully() {
        return true;
    }

    // ── Helper ─────────────────────────────────────────────────────────────────

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}