package pagesTanistha;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertyDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    public PropertyDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ FIX 1: Re-locate fresh using By.xpath instead of @FindBy proxy
    // Old code used @FindBy List<WebElement> navigationBar which went stale
    // after tab switch. Now we re-query the DOM inside the method itself.
    public void clickBuyTag() {
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }

        for (int i = 0; i < 3; i++) {
            try {
                WebElement buyTag = wait.until(
                    ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[@class='mb-header__main__link '])[1]")
                    )
                );
                // DEBUG: confirm what text is being clicked
                System.out.println("Buy tag text: " + buyTag.getText());
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buyTag);
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying Buy click, attempt: " + (i + 1));
            }
        }
    }

    // ✅ FIX 2: Re-locate fresh using By.cssSelector instead of @FindBy proxy
    // Old code used @FindBy List<WebElement> functionBuy which went stale.
    public void clickBudgetTag() {
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }

        List<WebElement> functionBuy = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".drop-call")
            )
        );
        // DEBUG: print all drop-call texts to confirm correct index
        for (int i = 0; i < functionBuy.size(); i++) {
            System.out.println("drop-call[" + i + "] = " + functionBuy.get(i).getText());
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", functionBuy.get(2));
    }

    // ✅ FIX 3: Re-locate fresh using By.xpath instead of @FindBy proxy
    // Old code used @FindBy List<WebElement> budgetCategory which went stale.
    public void clickCategory() {
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }

        List<WebElement> budgetCategory = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("(//div[contains(@class,'drop-call')])[3]//ul[contains(@class,'drop-links')]/li")
            )
        );
        // DEBUG: print all category texts to confirm correct index
        for (int i = 0; i < budgetCategory.size(); i++) {
            System.out.println("category[" + i + "] = " + budgetCategory.get(i).getText());
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", budgetCategory.get(2));
    }

    // ✅ FIX 4: Combined method - calls Buy, Budget and Category in sequence
    // NO tab switching here - all three actions happen on the same page
    // Tab switching was WRONG - clicking Buy stays on property detail page
    // Only clicking a budget category opens a new tab
    public void clickBuyThenBudgetThenCategory(String budgetRange) {

        // Step 1: Click Buy tag with retry for stale element
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }

        for (int i = 0; i < 3; i++) {
            try {
                WebElement buyTag = wait.until(
                    ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[@class='mb-header__main__link '])[1]")
                    )
                );
                System.out.println("Buy tag text: " + buyTag.getText());
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buyTag);
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying Buy click, attempt: " + (i + 1));
            }
        }

        // Step 2: Wait for dropdown to appear - use longer sleep as dropdown is hover based
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Step 3: Directly click budget range span by text from feature file
        for (int i = 0; i < 3; i++) {
            try {
                WebElement budgetRangeEl = wait.until(
                    ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[@data-tab='_blank' and contains(text(),'" + budgetRange + "')]")
                    )
                );
                System.out.println("Budget range found: " + budgetRangeEl.getText());
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", budgetRangeEl);
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying budget range click, attempt: " + (i + 1));
            }
        }
    }
    
    // ✅ FIX 5: Re-locate readMore fresh using By.xpath instead of @FindBy proxy
    // Old code: wait.until(visibilityOf(readMore_btn)) on a stale proxy threw
    // StaleElementReferenceException after page rendered dynamically
    public String getDesc() {
        WebElement readMore = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@class,'description--readmore')])[1]")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", readMore);

        WebElement desc = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'description')]")
            )
        );
        return desc.getText();
    }

    // ✅ No change needed - driver.getTitle() is always fresh
    public String getPageTitle() {
        return driver.getTitle();
    }
}