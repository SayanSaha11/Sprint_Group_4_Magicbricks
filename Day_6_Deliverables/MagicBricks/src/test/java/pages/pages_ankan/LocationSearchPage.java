package pages.pages_ankan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LocationSearchPage extends BasePage {

    // ✅ Don't use @FindBy for stale elements — find fresh every time
    public boolean areResultsDisplayed() throws InterruptedException {
        Thread.sleep(5000); // wait for page to fully load after search
        // ✅ Find elements fresh — NOT from @FindBy cached reference
        List<WebElement> resultCards = driver.findElements(
            By.cssSelector(".mb-srp__card")
        );
        System.out.println("Total results found: " + resultCards.size());
        return !resultCards.isEmpty();
    }

    // TC_04 — get error message text
    public String getErrorMessageText() {
        WebElement errorMsg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'valid') or contains(text(),'not')]")
            )
        );
        return errorMsg.getText();
    }
}
