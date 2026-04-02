package PagesAahana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class RatesAndTrendsPage extends BasePage {

    // ─── Locators ────────────────────────────────────────────────────────────────

    private final By hyderabadLink  = By.xpath("(//a[text()='Hyderabad'])[1]");
    private final By viewTrendsLink = By.xpath("(//a[text()='View Trends'])[1]");

    // ─── Constructor ─────────────────────────────────────────────────────────────

    public RatesAndTrendsPage(WebDriver driver) {
        super(driver);
    }

    // ─── Actions ──────────────────────────────────────────────────────────────────

    /**
     * Clicks on "Hyderabad" in the city list.
     * Switches to a new tab if one is opened.
     */
    public void selectHyderabad() throws InterruptedException {
        Set<String> windowsBefore = driver.getWindowHandles();

        WebElement hyderabad = wait.until(
            ExpectedConditions.presenceOfElementLocated(hyderabadLink));
        jsClick(hyderabad);
        Thread.sleep(2000);

        for (String handle : driver.getWindowHandles()) {
            if (!windowsBefore.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        System.out.println("After Hyderabad click URL: " + driver.getCurrentUrl());
    }

    /**
     * Clicks the first "View Trends" link on the page.
     * Switches to a new tab if one is opened.
     *
     * @return a new LocalityTrendsPage instance
     */
    public LocalityTrendsPage clickViewTrendsForFirstProperty() throws InterruptedException {
        Set<String> windowsBefore = driver.getWindowHandles();

        WebElement viewTrends = wait.until(
            ExpectedConditions.presenceOfElementLocated(viewTrendsLink));
        jsClick(viewTrends);
        Thread.sleep(2000);

        for (String handle : driver.getWindowHandles()) {
            if (!windowsBefore.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        System.out.println("After View Trends click URL: " + driver.getCurrentUrl());
        return new LocalityTrendsPage(driver);
    }
}