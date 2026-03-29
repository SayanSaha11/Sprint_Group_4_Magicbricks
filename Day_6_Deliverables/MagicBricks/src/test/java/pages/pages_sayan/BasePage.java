package pages.pages_sayan;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.utils_sayan.DriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final WebDriverWait longWait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Page Load Stability
    // ─────────────────────────────────────────────────────────────────────────
    public void waitPageReady() {
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // JavaScript Helpers
    // ─────────────────────────────────────────────────────────────────────────
    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void jsScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
    }

    public void scrollBy(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + ", " + y + ");");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Explicit Wait Wrappers
    // ─────────────────────────────────────────────────────────────────────────
    protected WebElement waitVisible(By locator) {
        return staleSafeVisible(locator);
    }

    protected WebElement waitClickable(By locator) {
        return staleSafeClickable(locator);
    }

    protected WebElement longWaitVisible(By locator) {
        return longWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement longWaitClickable(By locator) {
        return longWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> waitAllVisible(By locator) {
        return waitAllVisibleStaleSafe(locator);
    }

    protected List<WebElement> waitPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Stale-Safe Methods (Core Fix)
    // ─────────────────────────────────────────────────────────────────────────
    protected WebElement staleSafeVisible(By locator) {
        return wait.until(driver -> {
            for (int i = 0; i < 3; i++) {
                try {
                    WebElement el = driver.findElement(locator);
                    if (el.isDisplayed()) return el;
                } catch (StaleElementReferenceException e) {
                    if (i == 2) throw e;
                    sleep(400);
                }
            }
            return null;
        });
    }

    protected WebElement staleSafeClickable(By locator) {
        return wait.until(driver -> {
            for (int i = 0; i < 3; i++) {
                try {
                    return ExpectedConditions.elementToBeClickable(locator).apply(driver);
                } catch (StaleElementReferenceException e) {
                    if (i == 2) throw e;
                    sleep(400);
                }
            }
            return null;
        });
    }

    protected List<WebElement> waitAllVisibleStaleSafe(By locator) {
        return wait.until(driver -> {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (elements.isEmpty()) return null;
                // Quick validation to trigger stale check
                for (int i = 0; i < Math.min(3, elements.size()); i++) {
                    elements.get(i).isDisplayed();
                }
                return elements;
            } catch (StaleElementReferenceException e) {
                return null; // retry
            }
        });
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Stale Proof Click
    // ─────────────────────────────────────────────────────────────────────────
    protected void staleProofClick(By locator) {
        staleProofClick(locator, 3);
    }

    protected void staleProofClick(By locator, int maxAttempts) {
        StaleElementReferenceException lastEx = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                waitClickable(locator).click();
                return;
            } catch (StaleElementReferenceException ex) {
                lastEx = ex;
                sleep(500);
            }
        }
        throw new StaleElementReferenceException(
                "Element still stale after " + maxAttempts + " retries: " + locator, lastEx);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Tab Switching
    // ─────────────────────────────────────────────────────────────────────────
    public void switchToNewTab(String... knownHandles) {
        Set<String> known = new HashSet<>(Arrays.asList(knownHandles));
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d -> d.getWindowHandles().size() > known.size());

        for (String tab : driver.getWindowHandles()) {
            if (!known.contains(tab)) {
                driver.switchTo().window(tab);
                waitPageReady();
                return;
            }
        }
    }

    public void switchToNewTabExcluding(String knownHandle) {
        switchToNewTab(knownHandle);
    }

    public void switchToThirdTab(String mainTab, String secondTab) {
        switchToNewTab(mainTab, secondTab);
    }

    public void switchToFourthTab(String mainTab, String secondTab, String thirdTab) {
        switchToNewTab(mainTab, secondTab, thirdTab);
    }

    public String currentHandle() {
        return driver.getWindowHandle();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Utility
    // ─────────────────────────────────────────────────────────────────────────
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void hoverElement(By locator) {
        new Actions(driver).moveToElement(waitVisible(locator)).perform();
    }

    protected void waitForPageStable() {
        waitPageReady();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Legacy support (to fix your current compilation errors)
    // ─────────────────────────────────────────────────────────────────────────
    /**
     * Legacy method to support existing calls like waitForClickable(element)
     * in BudgetHomesPage and PremiumHomesPage.
     */
    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}