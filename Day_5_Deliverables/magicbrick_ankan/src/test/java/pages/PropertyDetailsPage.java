package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PropertyDetailsPage extends BasePage {

    @FindBy(css = ".mb-srp__card__society--name")
    private List<WebElement> propertyCards;

    public void clickFirstProperty() throws InterruptedException {
        Thread.sleep(9000);
        wait.until(ExpectedConditions.visibilityOfAllElements(propertyCards));
        propertyCards.get(0).click();
    }

    public void switchToNewTab() {
        String mainTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    public String getTitle() throws InterruptedException {
        Thread.sleep(9000);
        return driver.findElement(By.tagName("h1")).getText();
    }

    public String getPrice() {
        return driver.findElement(
            By.xpath("//div[contains(@class,'price')]")).getText();
    }

    public String getLocation() {
        return driver.findElement(
            By.xpath("//div[contains(@class,'loc')]")).getText();
    }

    public String getDescription() {
        List<WebElement> desc = driver.findElements(
            By.xpath("//div[contains(@class,'description')]"));
        return desc.isEmpty() ? "Description not available" : desc.get(0).getText();
    }

    public boolean arePropertyDetailsDisplayed() throws InterruptedException {
        String title    = getTitle();
        String price    = getPrice();
        String location = getLocation();
        System.out.println("Title: "    + title);
        System.out.println("Price: "    + price);
        System.out.println("Location: " + location);
        System.out.println("Desc: "     + getDescription());
        return !title.isEmpty() && !price.isEmpty() && !location.isEmpty();
    }
}