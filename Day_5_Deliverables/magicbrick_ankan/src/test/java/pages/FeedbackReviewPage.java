package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FeedbackReviewPage extends BasePage {

    @FindBy(css = ".mb-srp__card__society--name")
    private List<WebElement> propertyCards;

    @FindBy(xpath = "//div[text()='Write Project Review']")
    private WebElement writeReviewBtn;

    @FindBy(xpath = "//textarea[contains(@placeholder,'Tell us')]")
    private WebElement reviewTextArea;

    @FindBy(name = "reviewTitle")
    private WebElement reviewTitleInput;

    @FindBy(css = ".mb-custom-select__select-box")
    private WebElement relationDropdown;

    @FindBy(xpath = "//div[@class='mb-custom-select__option' and text()='Other']")
    private WebElement otherOption;

    @FindBy(xpath = "//div[text()='Project Infrastructure']/following::label[contains(@for,'Rt5')][1]")
    private WebElement ratingLabel;

    @FindBy(css = ".mb-rev__action-btn")
    private WebElement submitReviewBtn;

    public void clickFirstProperty() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfAllElements(propertyCards));
        propertyCards.get(0).click();
    }

    public void switchToNewTab() throws InterruptedException {
        Thread.sleep(8000);
        String mainTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        Thread.sleep(3000);
    }

    // ✅ ONLY THIS METHOD CHANGED
    public void clickWriteReview() throws InterruptedException {
        Thread.sleep(9000);

        // Step 1 — wait for element to be present in DOM
        WebElement btn = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()='Write Project Review']")
            )
        );

        // Step 2 — scroll to center of screen (not just into view)
        ((JavascriptExecutor) driver)
            .executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", btn
            );

        Thread.sleep(9000); // let sticky header settle after scroll

        // Step 3 — JS click to bypass overlapping sticky navbar
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", btn);

        Thread.sleep(9000);
    }

    public void fillReview(String reviewText, String title) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(reviewTextArea));
        reviewTextArea.sendKeys(reviewText);
        Thread.sleep(8000);
        reviewTitleInput.sendKeys(title);
        Thread.sleep(7000);
    }

    public void selectRelation() throws InterruptedException {
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.elementToBeClickable(relationDropdown));
        relationDropdown.click();
        Thread.sleep(8000);
        wait.until(ExpectedConditions.elementToBeClickable(otherOption));
        otherOption.click();
        Thread.sleep(8000);
    }

    public void selectRating() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ratingLabel));
        ratingLabel.click();
        Thread.sleep(9000);
    }

    public void submitReview() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(submitReviewBtn));
        submitReviewBtn.click();
        Thread.sleep(8000);
        System.out.println("Thanks! Your review has been posted.");
    }
}