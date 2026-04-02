package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class KolkataRealEstatePage extends BasePage {

    // ─── Locators ────────────────────────────────────────────────────────────────

    // Rates and Trends — 3rd item under nav sub-tabs
    private final By ratesAndTrendsNav = By.xpath(
        "/html/body/header/section[2]/div/ul/li[3]/div/div/div[3]/ul/li[3]/a");

    // Post Property — 2nd nav sub-tab
    private final By postPropertyTab = By.xpath(
        "(//a[@class='mb-header__sub__tabs__link js-menu-link'])[2]");

    // Financial Services — 4th nav sub-tab
    private final By financialServicesTab = By.xpath(
        "(//a[@class='mb-header__sub__tabs__link js-menu-link'])[4]");

    // Share Requirement link (visible after clicking Post Property tab)
    private final By shareRequirementLink = By.xpath("//a[text()='Share Requirement']");

    // Check Credit Score link (visible after clicking Financial Services tab)
    private final By checkCreditScoreLink = By.xpath("//a[text()='Check Credit Score']");

    // ─── Constructor ─────────────────────────────────────────────────────────────

    public KolkataRealEstatePage(WebDriver driver) {
        super(driver);
    }

    // ─── Navigation ───────────────────────────────────────────────────────────────

    /**
     * Opens the MagicBricks Kolkata residential real estate page.
     * Does NOT throw InterruptedException — Thread.sleep wrapped in try-catch.
     * Used as the entry point for ALL three scenarios.
     */
    public void navigateTo() {
        driver.get(
            "https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");
        try { Thread.sleep(4000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        System.out.println("Navigated to MagicBricks Kolkata page.");
    }

    // ─── Scenario 1 : Rates and Trends ───────────────────────────────────────────

    /**
     * Clicks the "Rates and Trends" link in the top navigation menu.
     * Switches to the newly opened tab/window if one appears.
     *
     * @return RatesAndTrendsPage — page object for the rates page
     */
    public RatesAndTrendsPage clickRatesAndTrends() throws InterruptedException {
        Set<String> windowsBefore = driver.getWindowHandles();

        WebElement navItem = wait.until(
            ExpectedConditions.presenceOfElementLocated(ratesAndTrendsNav));
        jsClick(navItem);
        Thread.sleep(2000);

        for (String handle : driver.getWindowHandles()) {
            if (!windowsBefore.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        System.out.println("After Rates & Trends click — URL: " + driver.getCurrentUrl());
        return new RatesAndTrendsPage(driver);
    }

    // ─── Scenario 2 : Share Requirements ─────────────────────────────────────────

    /**
     * Clicks the "Post Property" sub-tab in the navigation menu,
     * which reveals the Share Requirement link underneath it.
     */
    public void clickPostPropertyTab() throws InterruptedException {
        driver.findElement(postPropertyTab).click();
        Thread.sleep(1000);
        System.out.println("Clicked Post Property tab.");
    }

    /**
     * Stores the current window handle, then clicks "Share Requirement".
     *
     * @return original window handle (before the new window opens)
     */
    public String clickShareRequirement() {
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(shareRequirementLink)).click();
        System.out.println("Clicked Share Requirement link.");
        return originalWindow;
    }

    /**
     * Waits for the Share Requirement form to open in a new window,
     * switches focus to it, and returns a ShareRequirementsPage instance.
     *
     * @param originalWindow handle returned by clickShareRequirement()
     * @return ShareRequirementsPage — page object for the form
     */
    public ShareRequirementsPage switchToShareRequirementsWindow(String originalWindow)
            throws InterruptedException {
        switchToNewWindow(originalWindow);
        Thread.sleep(3000);
        System.out.println("Share Requirements window ready — URL: " + driver.getCurrentUrl());
        return new ShareRequirementsPage(driver);
    }

    // ─── Scenario 3 : Credit Score ────────────────────────────────────────────────

    /**
     * Clicks the "Financial Services" sub-tab in the navigation menu,
     * which reveals the Check Credit Score link underneath it.
     */
    public void clickFinancialServicesTab() throws InterruptedException {
        driver.findElement(financialServicesTab).click();
        Thread.sleep(1000);
        System.out.println("Clicked Financial Services tab.");
    }

    /**
     * Stores the current window handle, then clicks "Check Credit Score".
     *
     * @return original window handle (before the new window opens)
     */
    public String clickCheckCreditScore() {
        String originalWindow = driver.getWindowHandle();
        driver.findElement(checkCreditScoreLink).click();
        System.out.println("Clicked Check Credit Score link.");
        return originalWindow;
    }

    /**
     * Waits for the Credit Score form to open in a new window,
     * switches focus to it, and returns a CreditScorePage instance.
     *
     * @param originalWindow handle returned by clickCheckCreditScore()
     * @return CreditScorePage — page object for the credit score form
     */
    public CreditScorePage switchToCreditScoreWindow(String originalWindow) {
        switchToNewWindow(originalWindow);
        System.out.println("Credit Score window ready — URL: " + driver.getCurrentUrl());
        return new CreditScorePage(driver);
    }

    // ─── Private Helper ───────────────────────────────────────────────────────────

    /**
     * Waits until exactly 2 windows are open, then switches driver focus
     * to whichever handle is NOT the original window.
     *
     * @param originalWindow the window handle to avoid switching to
     */
    private void switchToNewWindow(String originalWindow) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("Switched to new window — URL: " + driver.getCurrentUrl());
    }
}