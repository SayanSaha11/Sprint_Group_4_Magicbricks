package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected WebDriver     driver;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;

    public BasePage(WebDriver driver) {
        this.driver   = driver;
        this.wait     = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // ── JS helpers ─────────────────────────────────────────────────────────

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /** Click a locator via JS – bypasses interactability checks entirely. */
    protected void jsClick(By locator) {
        jsClick(driver.findElement(locator));
    }

    public void scrollBy(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + ", " + y + ");");
    }

    /** Scrolls element into the centre of the viewport so it becomes interactable. */
    protected void jsScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center',inline:'nearest'});", element);
    }

    // ── Explicit-wait wrappers ──────────────────────────────────────────────

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement longWaitVisible(By locator) {
        return longWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement longWaitClickable(By locator) {
        return longWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> waitAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> waitPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ── Stale-element safe click (re-fetches element up to 3 times) ────────

    /**
     * Retries the find+click up to {@code maxAttempts} times when a
     * StaleElementReferenceException is thrown.  This is the correct fix for
     * pages that partially re-render after a tab switch or JS navigation.
     */
    protected void staleProofClick(By locator) {
        staleProofClick(locator, 3);
    }

    protected void staleProofClick(By locator, int maxAttempts) {
        org.openqa.selenium.StaleElementReferenceException lastEx = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                waitClickable(locator).click();
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                lastEx = e;
                // Brief yield – let the DOM settle before re-fetching
                try { Thread.sleep(500); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new org.openqa.selenium.StaleElementReferenceException(
            "Element still stale after " + maxAttempts + " attempts: " + locator, lastEx);
    }

    /**
     * Scrolls the element into view then clicks via JS.
     * Use when normal .click() raises ElementNotInteractableException because
     * the element is off-screen or obscured by a sticky header/overlay.
     */
    protected void scrollAndJsClick(By locator) {
        WebElement el = waitVisible(locator);
        jsScrollIntoView(el);
        // small DOM-settle wait after scroll before JS click
        wait.until(ExpectedConditions.elementToBeClickable(el));
        jsClick(el);
    }

    // ── Tab-switching (no Thread.sleep) ────────────────────────────────────

    /**
     * Polls until a new tab appears beyond knownHandles, then switches to it.
     * After switching, waits for document.readyState == "complete" so the
     * page is fully loaded before the next step runs.
     */
    public void switchToNewTab(String... knownHandles) {
        Set<String> known = new HashSet<>(Arrays.asList(knownHandles));
        WebDriverWait tabWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        tabWait.until(d -> d.getWindowHandles().size() > known.size());
        for (String tab : driver.getWindowHandles()) {
            if (!known.contains(tab)) {
                driver.switchTo().window(tab);
                // Wait for the new tab's page to finish loading
                wait.until(d -> ((JavascriptExecutor) d)
                        .executeScript("return document.readyState").equals("complete"));
                return;
            }
        }
    }

    /** Convenience alias used by the step definitions. */
    public void switchToNewTabExcluding(String knownHandle) {
        switchToNewTab(knownHandle);
    }

    /** Switch to a third tab (not mainTab, not secondTab). */
    public void switchToThirdTab(String mainTab, String secondTab) {
        switchToNewTab(mainTab, secondTab);
    }

    /** Switch to a fourth tab (not main, second, or third). */
    public void switchToFourthTab(String mainTab, String secondTab, String thirdTab) {
        switchToNewTab(mainTab, secondTab, thirdTab);
    }

    /** Returns the current window handle. */
    public String currentHandle() {
        return driver.getWindowHandle();
    }

    protected List<String> getAllWindowHandles() {
        return new ArrayList<>(driver.getWindowHandles());
    }
}