package PagesAahana;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShareRequirementsPage extends BasePage {

    // ─── Locators ────────────────────────────────────────────────────────────────

    // Property Type
    private final By propertyTypeDropdown       = By.id("textPropertySell");
    private final By residentialHouseOption     = By.id("propertyTypeSell_10001");
    private final By multistoreyApartmentOption = By.id("propertyTypeSell_10002");

    // Budget
    private final By budgetDropdown             = By.id("budgetBuyDDList");
    private final By budget70to80Lac            = By.id("budgetBuy_1002222-11809");

    // Bedroom
    private final By bedroomDropdown            = By.id("textBedRoom");
    private final By threeBHKOption             = By.id("bedrooms_11702");

    // Floor
    private final By floorDropdown              = By.id("floorPref");
    private final By below10thFloorOption       = By.xpath("//li[text()='Below 10th Floor']");

    // Area
    private final By areaDropdown               = By.id("coveredAreaDDList");
    private final By areaMin1000                = By.xpath("//li[text()='1000']");
    private final By areaMax5000                = By.xpath("(//li[text()='5000'])[2]");

    // City & Locality
    private final By cityField                  = By.id("keyword");
    private final By localityField              = By.id("keywordLoc");
    private final By localitySuggestions        = By.xpath(
        "//*[contains(@class,'ac_results')]//li" +
        " | //*[contains(@class,'autocomplete')]//li" +
        " | //*[contains(@id,'keywordLoc')]//following-sibling::*//li");

    // T&C and Submit
    private final By tncCheckbox               = By.id("tnc");
    private final By submitButton              = By.id("postReqSubmit");

    // ─── Constructor ─────────────────────────────────────────────────────────────

    public ShareRequirementsPage(WebDriver driver) {
        super(driver);
    }

    // ─── Actions ──────────────────────────────────────────────────────────────────

    /**
     * Selects "Residential House" and "Multistorey Apartment" from the property type dropdown.
     */
    public void selectPropertyTypes() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(propertyTypeDropdown)));
        Thread.sleep(1000);
        jsClick(driver.findElement(residentialHouseOption));
        Thread.sleep(500);
        jsClick(driver.findElement(multistoreyApartmentOption));
        Thread.sleep(500);
        System.out.println("Selected property types: Residential House, Multistorey Apartment");
    }

    /**
     * Selects the "70 - 80 Lac" option from the budget dropdown.
     */
    public void selectBudget() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(budgetDropdown)));
        Thread.sleep(1000);
        jsClick(driver.findElement(budget70to80Lac));
        Thread.sleep(500);
        System.out.println("Selected budget: 70 - 80 Lac");
    }

    /**
     * Selects "3 BHK" from the bedroom dropdown.
     */
    public void selectBedroom() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(bedroomDropdown)));
        Thread.sleep(1000);
        jsClick(driver.findElement(threeBHKOption));
        Thread.sleep(500);
        System.out.println("Selected bedroom: 3 BHK");
    }

    /**
     * Selects "Below 10th Floor" from the floor preference dropdown.
     */
    public void selectFloorPreference() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(floorDropdown)));
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(below10thFloorOption)).click();
        Thread.sleep(500);
        System.out.println("Selected floor: Below 10th Floor");
    }

    /**
     * Sets the area range — min 1000, max 5000.
     * The max tab auto-activates after selecting the min value.
     */
    public void setAreaRange() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(areaDropdown)));
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(areaMin1000)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(areaMax5000)).click();
        Thread.sleep(500);
        System.out.println("Set area range: 1000 to 5000");
    }

    /**
     * Types the city name and confirms with Enter.
     */
    public void enterCity(String cityName) throws InterruptedException {
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(cityField));
        city.sendKeys(cityName);
        Thread.sleep(1500);
        city.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        System.out.println("Entered city: " + cityName);
    }

    /**
     * Types the locality name, waits for autocomplete suggestions,
     * and clicks the first suggestion. Falls back to pressing Enter if none appear.
     */
    public void enterLocalityAndSelectFirstSuggestion(String locality) throws InterruptedException {
        WebElement locField = wait.until(ExpectedConditions.elementToBeClickable(localityField));
        locField.clear();
        locField.sendKeys(locality);
        Thread.sleep(3000); // Wait for suggestions to load

        List<WebElement> suggestions = driver.findElements(localitySuggestions);
        System.out.println("Suggestions found: " + suggestions.size());
        for (WebElement s : suggestions) {
            System.out.println("  [" + s.getText() + "]");
        }

        if (!suggestions.isEmpty()) {
            String firstSuggestion = suggestions.get(0).getText();
            suggestions.get(0).click();
            System.out.println("Clicked first suggestion: " + firstSuggestion);
        } else {
            System.out.println("No suggestions found — pressing Enter as fallback");
            locField.sendKeys(Keys.ENTER);
        }
        Thread.sleep(1000);
    }

    /**
     * Clicks the Terms and Conditions checkbox.
     */
    public void acceptTermsAndConditions() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(tncCheckbox)));
        Thread.sleep(500);
        System.out.println("Accepted Terms and Conditions.");
    }

    /**
     * Clicks the Submit button.
     */
    public void clickSubmit() throws InterruptedException {
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(submitButton)));
        Thread.sleep(5000);
        System.out.println("Clicked Submit button.");
    }

    /**
     * Verifies the form was submitted by checking the submit button is no longer present
     * or a confirmation indicator appears. Returns true if submitted successfully.
     */
    public boolean isFormSubmitted() {
        List<WebElement> submitBtn = driver.findElements(submitButton);
        // If submit button is gone or page has changed, form was submitted
        return submitBtn.isEmpty() || !submitBtn.get(0).isDisplayed();
    }
}