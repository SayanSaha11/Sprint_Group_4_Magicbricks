package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BudgetHomesPage extends BasePage {

    private final By propertyList      = By.xpath("//div[@class='mb-srp__list']");
    private final By priceAmount       = By.xpath("//div[@class='mb-srp__card__price--amount']");
    private final By applyLoanBtn      = By.xpath("//a[@class='mb-ldp__dtls__applyLoan'][1]");
    private final By loanAmountInput   = By.xpath("//input[@id='amountRequiredEmiCal']");
    private final By interestRateInput = By.xpath("//input[@id='interestRateEmiCal']");
    private final By notFinalizedRadio = By.xpath("//label[@for='emiPropFinalizedNo']");
    private final By calculateBtn      = By.xpath("//a[@id='submitbuttonEmiCalid']");
    private final By emiResultBox      = By.xpath("//div[@class='hl__calc__rt__box']");

    public BudgetHomesPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getPropertyList() {
        return waitAllVisible(propertyList);
    }

    public void printFirst5Prices() {
        List<WebElement> properties = getPropertyList();
        for (int i = 0; i < 5; i++) {
            String price = properties.get(i).findElement(priceAmount).getText();
            System.out.println("Price of Budget property " + (i + 1) + " : " + price);
        }
    }

    public void clickFirstProperty() {
        List<WebElement> properties = getPropertyList();
        waitForClickable(properties.get(0));
        properties.get(0).click();
    }

    /**
     * TC04 FIX – StaleElementReferenceException root cause:
     * After switching to the property detail tab the page continues rendering
     * (lazy-loaded widgets, analytics scripts) which causes a partial DOM
     * rebuild.  The element reference captured by waitClickable() becomes stale
     * before .click() fires.
     *
     * Fix: (1) Wait for the page to be fully loaded via document.readyState.
     *       (2) Scroll the Apply Loan button into view so it is not obscured.
     *       (3) Use staleProofClick() which re-fetches the element on each
     *           StaleElementReferenceException (up to 3 retries).
     */
    public void clickApplyLoan() {
        // Step 1 – ensure page has finished all JS rendering
        wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
        // Step 2 – scroll button into view (it may be below the fold)
        WebElement btn = waitVisible(applyLoanBtn);
        jsScrollIntoView(btn);
        // Step 3 – stale-proof click: re-fetches on StaleElementReferenceException
        staleProofClick(applyLoanBtn);
    }

    public void enterLoanDetails(String amount, String rate) {
        WebElement amountField = waitClickable(loanAmountInput);
        amountField.clear();
        amountField.sendKeys(amount);

        WebElement rateField = waitClickable(interestRateInput);
        rateField.clear();
        rateField.sendKeys(rate);
    }

    public void selectPropertyNotFinalized() {
        jsClick(waitVisible(notFinalizedRadio));
    }

    public void clickCalculate() {
        waitClickable(calculateBtn).click();
    }

    public String getEmiResult() {
        return waitVisible(emiResultBox).getText();
    }
}