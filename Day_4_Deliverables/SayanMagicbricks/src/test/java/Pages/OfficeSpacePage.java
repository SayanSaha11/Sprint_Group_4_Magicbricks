package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OfficeSpacePage extends BasePage {

    private final By firstPropertyTitle = By.xpath("//h2[@class='mb-srp__card--title']");

    /*
     * TC14 FIX – the actual button text on the MagicBricks office detail page
     * is "Get Phone No.", not "Contact Agent".  The step-def file provided
     * confirms this: officeSpacePage.clickGetPhoneNo() →
     * xpath: //a[text()='Get Phone No.']
     *
     * StaleElementReferenceException root cause (same as TC04):
     * After switchToThirdTab() the detail page continues rendering and a JS
     * widget re-builds the CTA buttons, staling any previously captured
     * WebElement reference.
     *
     * Fix: (1) Wait for document.readyState = "complete".
     *       (2) Scroll button into centre of viewport.
     *       (3) staleProofClick() re-fetches on StaleElementReferenceException.
     */
    private final By getPhoneNoBtn = By.xpath("//a[text()='Get Phone No.']");

    public OfficeSpacePage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstProperty() {
        waitClickable(firstPropertyTitle).click();
    }

    /**
     * Clicks "Get Phone No." on the office detail page.
     * Called both by clickContactAgent() (old name) and clickGetPhoneNo() (new name).
     */
    public void clickGetPhoneNo() {
        // Step 1 – Full page-load guard before any interaction
        wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));

        // Step 2 – Scroll into view to avoid "off-screen" interactability failure
        WebElement btn = waitVisible(getPhoneNoBtn);
        jsScrollIntoView(btn);

        // Step 3 – Stale-proof click: re-fetches the element on each retry
        staleProofClick(getPhoneNoBtn);
        System.out.println("Get Phone No. button clicked successfully.");
    }

    /** Backward-compat alias so older step-def wiring still compiles. */
    public void clickContactAgent() {
        clickGetPhoneNo();
    }
}