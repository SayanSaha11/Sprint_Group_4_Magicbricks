package pages.pages_ankan;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SortPage extends BasePage {

    @FindBy(xpath = "//div[text()='Top Localities']")
    private WebElement topLocalitiesFilter;

    @FindBy(xpath = "//label[text()='New Town']")
    private WebElement newTownLabel;

    @FindBy(xpath = "(//div[@class='filter__component__cta-done' and text()='Done'])[2]")
    private WebElement doneButton;

    @FindBy(xpath = "//div[text()='Posted By']")
    private WebElement postedByFilter;

    @FindBy(xpath = "//label[text()='Owners']")
    private WebElement ownersLabel;

    @FindBy(xpath = "//div[contains(text(),'Sort by')]")
    private WebElement sortByDiv;

    @FindBy(xpath = "//li[text()='Price - Low to High']")
    private WebElement priceLowToHigh;

    @FindBy(css = ".mb-srp__card__price--amount")
    private List<WebElement> priceElements;

    public void applyLocalityFilter() throws InterruptedException {
        topLocalitiesFilter.click();
        Thread.sleep(11000);
        newTownLabel.click();
        Thread.sleep(11000);
        doneButton.click();
        Thread.sleep(11000);
    }

    public void applyPostedByOwners() throws InterruptedException {
        postedByFilter.click();
        Thread.sleep(7000);
        ownersLabel.click();
        Thread.sleep(9000);
        driver.findElement(org.openqa.selenium.By.tagName("body")).click();
        Thread.sleep(9000);
    }

    public void sortByPriceLowToHigh() throws InterruptedException {
        sortByDiv.click();
        Thread.sleep(7000);
        priceLowToHigh.click();
        Thread.sleep(7000);
    }

    public boolean isPriceAscending() {
        List<Integer> prices = new ArrayList<>();
        for (WebElement el : priceElements) {
            String text = el.getText().replaceAll("[^0-9]", "");
            if (!text.isEmpty()) prices.add(Integer.parseInt(text));
        }
        System.out.println("Prices: " + prices);
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) return false;
        }
        return true;
    }
}