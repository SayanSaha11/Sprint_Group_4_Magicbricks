package pages_rent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LocationDetailsPage extends BasePage {

    @FindBy(css = ".mb-srp__card__society--name")
    private List<WebElement> propertyCards;

    public void clickFirstProperty() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfAllElements(propertyCards));
        propertyCards.get(0).click();
    }

    public void switchToNewTab() throws InterruptedException {
        Thread.sleep(3000);
        String mainTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        Thread.sleep(3000); // ✅ wait for new tab to fully load
    }

    public String getLocationText() {
        // ✅ Find fresh every time — no @FindBy cache
        WebElement locElement = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'loc')]")
            )
        );
        return locElement.getText();
    }

    public boolean isLocationContainsKolkata() {
        String location = getLocationText();
        System.out.println("Location: " + location);
        return location.toLowerCase().contains("kolkata");
    }
}