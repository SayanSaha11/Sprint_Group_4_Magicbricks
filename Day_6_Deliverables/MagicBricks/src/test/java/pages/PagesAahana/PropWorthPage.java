package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PropWorthPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // ── Navigation ─────────────────────────────────────────────────────────────

    @FindBy(xpath = "(//a[@class='mb-header__sub__tabs__link js-menu-link'])[3]")
    private WebElement toolsAndAdviceTab;

    @FindBy(xpath = "/html/body/header/section[2]/div/ul/li[3]/div/div/div[3]/ul/li[4]/a")
    private WebElement propWorthLink;

    // ── Locality Search ────────────────────────────────────────────────────────

    @FindBy(xpath = "//input[contains(@class,'prop-worth__input')]")
    private WebElement localitySearchInput;

    @FindBy(xpath = "//span[@class='matches']")
    private List<WebElement> localitySuggestions;

    // ── Get Estimate ───────────────────────────────────────────────────────────

    @FindBy(xpath = "//*[contains(text(),'Get Estimate')]")
    private WebElement getEstimateButton;

    // ── Property Type ──────────────────────────────────────────────────────────

    @FindBy(xpath = "//label[@for='propertytype_10002']")
    private WebElement flatOption;

    // ── BHK ───────────────────────────────────────────────────────────────────

    @FindBy(xpath = "//label[@for='bhk_11700']")
    private WebElement oneBhkOption;

    // ── Super Area ─────────────────────────────────────────────────────────────

    @FindBy(className = "search-filter__super-area__input")
    private WebElement superAreaInput;

    @FindBy(className = "search-filter__super-area__select")
    private WebElement superAreaUnitSelect;

    // ── Covered Parking ────────────────────────────────────────────────────────

    @FindBy(xpath = "//div[text()=' Covered']/../div[contains(@class,'icon-plus')]")
    private WebElement coveredParkingPlusButton;

    // ── Property Age ───────────────────────────────────────────────────────────

    @FindBy(xpath = "//label[contains(text(),'1') and contains(text(),'3') and contains(text(),'yr')]")
    private WebElement oneToThreeYearsOption;

    // ── Interior Amount ────────────────────────────────────────────────────────

    @FindBy(className = "search-filter__interioramnt__input")
    private WebElement interiorAmountInput;

    // ── Total Floors ───────────────────────────────────────────────────────────

    @FindBy(className = "search-filter__totalfloor")
    private WebElement totalFloorsInput;

    // ── Final Get Estimate ─────────────────────────────────────────────────────

    @FindBy(xpath = "//div[contains(@class,'search-filter')]//div[text()='Get Estimate']")
    private WebElement finalGetEstimateButton;

    // ── Constructor ────────────────────────────────────────────────────────────

    public PropWorthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ── Actions ────────────────────────────────────────────────────────────────

    public void navigateToPropWorth() {
        wait.until(ExpectedConditions.elementToBeClickable(toolsAndAdviceTab));
        toolsAndAdviceTab.click();
        sleep(1000);

//        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(propWorthLink));
        propWorthLink.click();
        sleep(4000);

        // Switch to new tab
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        sleep(3000);
    }

    public void searchLocality(String locality) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[contains(@class,'prop-worth__input')]")
        ));
        js.executeScript("arguments[0].click();", input);
        input.sendKeys(locality);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='matches']")
        ));
    }

    public void selectFirstSuggestion() {
        driver.findElements(By.xpath("//span[@class='matches']")).get(0).click();
        sleep(1000);
    }

    public void clickGetEstimate() {
        wait.until(ExpectedConditions.elementToBeClickable(getEstimateButton));
        getEstimateButton.click();
        sleep(4000);
    }

    public void selectPropertyType(String propertyType) {
        // Currently maps "Flat" — extend switch for other types as needed
        switch (propertyType) {
            case "Flat":
                js.executeScript("arguments[0].click();", flatOption);
                break;
            default:
                js.executeScript("arguments[0].click();",
                        driver.findElement(By.xpath("//label[normalize-space()='" + propertyType + "']")));
        }
    }

    public void selectBhk(String bhk) {
        // Currently maps "1 BHK" — extend switch for other BHK types as needed
        switch (bhk) {
            case "1 BHK":
                js.executeScript("arguments[0].click();", oneBhkOption);
                break;
            default:
                js.executeScript("arguments[0].click();",
                        driver.findElement(By.xpath("//label[normalize-space()='" + bhk + "']")));
        }
    }

    public void enterSuperArea(String area) {
        superAreaInput.clear();
        superAreaInput.sendKeys(area);
        // Default unit: Sq.ft (value=100459) — as per working script
        new Select(superAreaUnitSelect).selectByValue("100459");
    }

    public void addCoveredParking(String count) {
        int clicks = Integer.parseInt(count);
        for (int i = 0; i < clicks; i++) {
            js.executeScript("arguments[0].click();", coveredParkingPlusButton);
        }
    }

    public void selectPropertyAge(String age) {
        // Currently maps "1-3 years" — extend switch for other ages as needed
        switch (age) {
            case "1-3 years":
                js.executeScript("arguments[0].click();", oneToThreeYearsOption);
                break;
            default:
                js.executeScript("arguments[0].click();",
                        driver.findElement(By.xpath("//label[normalize-space()='" + age + "']")));
        }
    }

    public void enterInteriorAmount(String amount) {
        interiorAmountInput.clear();
        interiorAmountInput.sendKeys(amount);
    }

    public void enterTotalFloors(String floors) {
        totalFloorsInput.clear();
        totalFloorsInput.sendKeys(floors);
    }

    public void clickFinalGetEstimate() {
        js.executeScript("arguments[0].click();", finalGetEstimateButton);
        sleep(3000);
        System.out.println("Redirected for Login before getting the actual estimate to verify.\nURL: " + driver.getCurrentUrl());
    }

    public boolean isRedirectedToLoginPage() {
        return driver.getCurrentUrl() != null &&
                (driver.getCurrentUrl().contains("login") ||
                 driver.getCurrentUrl().contains("signin") ||
                 driver.getTitle().toLowerCase().contains("login"));
    }

    // ── Helper ─────────────────────────────────────────────────────────────────

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}