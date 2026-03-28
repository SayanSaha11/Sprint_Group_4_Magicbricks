//package pages.pages_sayan;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//public class HomePage extends BasePage {
//
//    private static final String BASE_URL =
//        "https://www.magicbricks.com/property-for-sale-rent-in-Mumbai/residential-real-estate-Mumbai";
//
//    private final By buyHeading       = By.xpath("//a[@id='buyheading']");
//    private final By budgetHomesLink  = By.xpath("//a[contains(text(),'Budget Homes')]");
//    private final By premiumHomesLink = By.xpath("//a[contains(text(),'Premium Homes')]");
//    private final By officeSpaceLink  = By.xpath("//a[contains(text(),'Office Space in Mumbai')]");
//    private final By commercialLink   = By.xpath("//a[contains(text(),'Commercial Space in Mumbai')]");
//    private final By houseForSaleLink = By.xpath("//a[contains(text(),'House for sale in Mumbai')]");
//
//    public HomePage() {
//        super();
//    }
//
//    public void openHomePage() {
//        driver.get(BASE_URL);
//        waitPageReady();
//        waitVisible(buyHeading);
//    }
//
//    public void openMumbaiPage() {
//        openHomePage();
//    }
//
//    public void clickBuyHeading() {
//        WebElement heading = waitClickable(buyHeading);
//        heading.click();
//
//        // Re-hover guarantees dropdown is fully visible + stable
//        new Actions(driver)
//            .moveToElement(heading)
//            .perform();
//
//        waitVisible(budgetHomesLink);
//    }
//
//    public void clickBuyMenuLink(String linkText) {
//        By target = getLinkLocator(linkText);
//
//        new Actions(driver)
//            .moveToElement(waitClickable(buyHeading))
//            .perform();
//
//        WebElement link = waitVisible(target);
//        jsScrollIntoView(link);
//        wait.until(ExpectedConditions.elementToBeClickable(target));
//        staleProofClick(target);
//    }
//
//    private By getLinkLocator(String linkText) {
//        switch (linkText) {
//            case "Budget Homes":               return budgetHomesLink;
//            case "Premium Homes":              return premiumHomesLink;
//            case "Office Space in Mumbai":     return officeSpaceLink;
//            case "Commercial Space in Mumbai": return commercialLink;
//            case "House for sale in Mumbai":   return houseForSaleLink;
//            default:
//                return By.xpath("//a[contains(text(),'" + linkText + "')]");
//        }
//    }
//}

package pages.pages_sayan;   // ← Correct package

import org.openqa.selenium.By;
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

    public void openHomePage() {
        driver.get(BASE_URL);
        waitPageReady();
        waitVisible(buyHeading);
    }

    public void openMumbaiPage() {
        openHomePage();
    }

    public void clickBuyHeading() {
        WebElement heading = waitClickable(buyHeading);
        heading.click();

        new Actions(driver)
                .moveToElement(heading)
                .pause(java.time.Duration.ofMillis(1000))
                .perform();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(budgetHomesLink),
                ExpectedConditions.visibilityOfElementLocated(houseForSaleLink)
        ));
    }

    public void clickBuyMenuLink(String linkText) {
        By target = getLinkLocator(linkText);

        WebElement buyHead = waitClickable(buyHeading);
        new Actions(driver).moveToElement(buyHead).perform();

        WebElement link = waitVisible(target);
        jsScrollIntoView(link);

        wait.until(ExpectedConditions.elementToBeClickable(target));
        staleProofClick(target);
    }

    private By getLinkLocator(String linkText) {
        switch (linkText) {
            case "Budget Homes":               return budgetHomesLink;
            case "Premium Homes":              return premiumHomesLink;
            case "Office Space in Mumbai":     return officeSpaceLink;
            case "Commercial Space in Mumbai": return commercialLink;
            case "House for sale in Mumbai":   return houseForSaleLink;
            default:
                return By.xpath("//a[contains(text(),'" + linkText + "')]");
        }
    }
}