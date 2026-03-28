package pages.pages_sayan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PremiumHomesPage extends BasePage {

    // ─── Locators ────────────────────────────────────────────────────────────
    private final By searchButton    = By.xpath("//div[@class='mb-search__btn']");
    private final By propertyCards   = By.xpath("//div[@class='mb-srp__card']");
    private final By imageSlider     = By.xpath(".//div[@class='mb-srp__card__photo__fig--slider']");
    private final By projectPhotoTab = By.xpath("//li[@data-category='Project Photo'][1]");
    private final By rightArrow      = By.xpath("//div[@class='arrow rightArrow']");

    // ─── Constructor ─────────────────────────────────────────────────────────
    public PremiumHomesPage() {
        super();
    }

    
    public void clickSearchButton() {
        waitPageReady();
        jsClick(waitClickable(searchButton));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(propertyCards, 0));
    }

    public void clickFirstPropertyImageSlider() {
        List<WebElement> cards = waitAllVisible(propertyCards);
        WebElement slider = cards.get(0).findElement(imageSlider);
        waitForClickable(slider);
        slider.click();
        waitVisible(projectPhotoTab); // confirm lightbox/gallery opened
    }

    public void clickProjectPhotoTab() {
        waitClickable(projectPhotoTab).click();
        waitVisible(rightArrow); // confirm photo view is active
        System.out.println("Photo 1 seen. Moving right");
    }

    public void navigateGalleryRight(int times) {
        for (int i = 1; i <= times; i++) {
            jsScrollIntoView(waitVisible(rightArrow));
            staleProofClick(rightArrow);
            waitClickable(rightArrow); // wait for next image to load
            System.out.println("Photo " + (i + 1) + " seen. Moving right");
        }
    }
    
    public List<WebElement> getFreshPropertyCards() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(propertyCards, 0));
        return driver.findElements(propertyCards);
    }
}