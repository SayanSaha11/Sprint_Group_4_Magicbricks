package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String BASE_URL =
            "https://www.magicbricks.com/property-for-sale-rent-in-Mumbai/residential-real-estate-Mumbai";

    private final By buyHeading       = By.xpath("//a[@id='buyheading']");
    private final By budgetHomesLink  = By.xpath("//a[contains(text(),'Budget Homes')]");
    private final By premiumHomesLink = By.xpath("//a[contains(text(),'Premium Homes')]");
    private final By officeSpaceLink  = By.xpath("//a[contains(text(),'Office Space in Mumbai')]");
    private final By commercialLink   = By.xpath("//a[contains(text(),'Commercial Space in Mumbai')]");
    private final By houseForSaleLink = By.xpath("//a[contains(text(),'House for sale in Mumbai')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        waitVisible(buyHeading);
    }

    // Alias used by the step definitions that come from the provided step-def file
    public void openMumbaiPage() {
        openHomePage();
    }

    public void clickBuyHeading() {
        waitClickable(buyHeading).click();
        // Wait until the dropdown is populated before returning
        waitVisible(budgetHomesLink);
    }

    /**
     * TC10 FIX – ElementNotInteractableException root cause:
     * The Buy dropdown menu renders its sub-links into the DOM immediately but
     * the CSS transition (opacity/transform) hasn't finished yet, so the link
     * is technically present but not interactable.
     *
     * Fix strategy:
     *   1. Re-hover over the Buy heading to ensure the dropdown is open.
     *   2. Scroll the target link into the centre of the viewport.
     *   3. Use staleProofClick() so any mid-animation stale reference is retried.
     *
     * This method is called by the shared step "the user clicks {string}".
     */
    public void clickBuyMenuLink(String linkText) {
        By linkLocator = getLinkLocator(linkText);

        // Step 1 – Re-hover the Buy heading so the dropdown is fully open.
        // Some browsers close it between clickBuyHeading() and the next step.
        Actions actions = new Actions(driver);
        WebElement heading = waitClickable(buyHeading);
        actions.moveToElement(heading).perform();

        // Step 2 – Wait for the specific link to become visible inside the dropdown
        WebElement link = waitVisible(linkLocator);

        // Step 3 – Scroll into view (handles cases where the link is clipped)
        jsScrollIntoView(link);

        // Step 4 – stale-proof click with up to 3 retries
        staleProofClick(linkLocator);
    }

    /**
     * Maps the display text used in feature steps to the correct By locator.
     */
    private By getLinkLocator(String linkText) {
        switch (linkText) {
            case "Budget Homes":              return budgetHomesLink;
            case "Premium Homes":             return premiumHomesLink;
            case "Office Space in Mumbai":    return officeSpaceLink;
            case "Commercial Space in Mumbai":return commercialLink;
            case "House for sale in Mumbai":  return houseForSaleLink;
            default:
                // Fallback: match by text – handles any unlisted link
                return By.xpath("//a[contains(text(),'" + linkText + "')]");
        }
    }

    // ── Original per-link methods (kept for backward compat) ──────────────

    public void clickBudgetHomes() {
        clickBuyMenuLink("Budget Homes");
    }

    public void clickPremiumHomes() {
        clickBuyMenuLink("Premium Homes");
    }

    public void clickOfficeSpace() {
        clickBuyMenuLink("Office Space in Mumbai");
    }

    public void clickCommercialSpace() {
        clickBuyMenuLink("Commercial Space in Mumbai");
    }

    public void clickHouseForSale() {
        clickBuyMenuLink("House for sale in Mumbai");
    }

    public String getCurrentHandle() {
        return driver.getWindowHandle();
    }
}