package pages.pages_ankan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(id = "tabRENT")
    private WebElement rentTab;

    @FindBy(id = "keyword")
    private WebElement cityInput;

    @FindBy(css = ".mb-search__tag-close")
    private WebElement tagClose;

    @FindBy(css = ".mb-search__btn")
    private WebElement searchButton;

    @FindBy(xpath = "//span[normalize-space()='kolkata']")
    private WebElement kolkataSuggestion;

    public void openMagicBricks() {
        driver.get("https://www.magicbricks.com");
    }

    public void clickRentTab() throws InterruptedException {
        Thread.sleep(3000);
        rentTab.click();
    }

    // ✅ TC_02, TC_26, TC_29, TC_30, TC_31, TC_35
    // Types city AND selects from dropdown
    public void enterCityAndSelectDropdown(String city) throws InterruptedException {
        try {
//        	cityInput.click();
            tagClose.click();
        } catch (Exception e) {}
        Thread.sleep(500);
        cityInput.click();
        cityInput.sendKeys(city);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(kolkataSuggestion));
        kolkataSuggestion.click();
        Thread.sleep(2000);
    }
    
    


    // ✅ TC_04 ONLY
    // Types invalid city — NO dropdown click, go straight to search
    public void enterInvalidCity(String city) throws InterruptedException {
        try {
            tagClose.click();
        } catch (Exception e) {}
        Thread.sleep(500);
        cityInput.click();
        tagClose.click();
        cityInput.sendKeys(city);
//        tagClose.click();
//        Thread.sleep(3000);
        // ❌ NO dropdown click here — Unknown has no suggestion
    }

    public void clickSearch() throws InterruptedException {
        Thread.sleep(500);
        searchButton.click();
    }
}