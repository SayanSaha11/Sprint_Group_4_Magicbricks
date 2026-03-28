package pages.pages_sayan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BudgetHomesPage extends BasePage {

    private final By propertyCardList = By.xpath("//div[@class='mb-srp__list']");
    private final By cardPriceAmount  = By.xpath(".//div[@class='mb-srp__card__price--amount']");
    private final By applyLoanBtn     = By.xpath("//a[@class='mb-ldp__dtls__applyLoan'][1]");
    private final By loanAmountInput  = By.xpath("//input[@id='amountRequiredEmiCal']");
    private final By interestRateInput= By.xpath("//input[@id='interestRateEmiCal']");
    private final By notFinalizedRadio= By.xpath("//label[@for='emiPropFinalizedNo']");
    private final By calculateEmiBtn  = By.xpath("//a[@id='submitbuttonEmiCalid']");
    private final By emiResultBox     = By.xpath("//div[@class='hl__calc__rt__box']");

    public BudgetHomesPage() {
        super();
    }

    public List<WebElement> getPropertyList() {
        waitPageReady();

        return wait.until(driver -> {
            try {
                List<WebElement> cards = driver.findElements(propertyCardList);
                if (cards.size() >= 5) {
                    // Quick stale check
                    for (WebElement card : cards) {
                        card.isDisplayed();
                    }
                    return cards;
                }
            } catch (org.openqa.selenium.StaleElementReferenceException ignored) {
                // retry
            }
            return null;
        });
    }

    public void printFirst5Prices() {
        List<WebElement> cards = getPropertyList();
        for (int i = 0; i < 5 && i < cards.size(); i++) {
            String price = cards.get(i).findElement(cardPriceAmount).getText();
            System.out.println("Price of Budget property " + (i + 1) + " : " + price);
        }
    }

    public void clickFirstProperty() {
        List<WebElement> cards = getPropertyList();
        waitForClickable(cards.get(0));
        cards.get(0).click();
    }

    public void clickApplyLoan() {
        waitPageReady();
        jsScrollIntoView(waitVisible(applyLoanBtn));
        staleProofClick(applyLoanBtn);
    }

    public void enterLoanAmount(String amount) {
        WebElement field = waitClickable(loanAmountInput);
        field.clear();
        field.sendKeys(amount);
    }

    public void enterInterestRate(String rate) {
        WebElement field = waitClickable(interestRateInput);
        field.clear();
        field.sendKeys(rate);
    }

    public void selectPropertyNotFinalisedRadio() {
        jsClick(waitVisible(notFinalizedRadio));
    }

    public void clickCalculateEmi() {
        waitClickable(calculateEmiBtn).click();
        waitVisible(emiResultBox);
    }

    public String getEmiResult() {
        return waitVisible(emiResultBox).getText();
    }
}