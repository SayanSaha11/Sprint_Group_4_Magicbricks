package pagesTanistha;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

        // Step 2: Wait for dropdown to appear
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
    
    public String getDesc() {
        
        // Step 1: Wait for page to be fully loaded first
        wait.until(d -> ((JavascriptExecutor) d)
            .executeScript("return document.readyState").equals("complete"));

        // Step 2: Scroll Read More into view
        WebElement readMore = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(@class,'description--content short')]//a[contains(@class,'description--readmore')]")
            )
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", readMore
        );

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Step 3: JS click instead of normal click — avoids ElementClickInterceptedException
        // under parallel load when overlays/popups may be covering the button
        WebElement readMoreFresh = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(@class,'description--content short')]//a[contains(@class,'description--readmore')]")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", readMoreFresh);

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Step 4: Wait for full description — increased timeout for parallel load
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(25));
        
        try {
            WebElement fullDesc = longWait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@class,'description--content full') and not(contains(@class,'hide'))]//p")
                )
            );
            String text = fullDesc.getText();
            System.out.println("Full desc empty: " + text.isEmpty());
            return text;

        } catch (Exception e) {
            // Fallback — return whatever description is visible
            System.out.println("Full desc not found, falling back to short desc");
            WebElement shortDesc = driver.findElement(
                By.xpath("//span[contains(@class,'description--content')]//p")
            );
            return shortDesc.getText();
        }
    }
    
    @FindBy(xpath="(//span[contains(text(),\"Download Brochure\")])[3]")
    private WebElement brochure;
    public void clickDownloadBrochure() {
    	wait.until(ExpectedConditions.elementToBeClickable(brochure));
    	brochure.click();
    }
    
    @FindBy(xpath="//input[@class=\"contact-form__input \"]")
    private List<WebElement> formData;
    public List<WebElement> getFormData(){
    	return formData;
    }

    // ✅ No change needed - driver.getTitle() is always fresh
    public String getPageTitle() {
        return driver.getTitle();
    }
}