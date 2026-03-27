package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PlotPage extends BasePage {

    private static final String PLOT_URL =
            "https://www.magicbricks.com/residential-plots-land-for-sale-in-mumbai-pppfs";

    private final By filterSpec      = By.xpath("//div[@class='top-filter__item-all-filter']");
    private final By moreFiltersBtn  = By.xpath("//div[text()='More Filters']");

    /*
     * Both min and max selects sit inside #moreFilter_0.
     * The original code used a class selector for min and a hard index for max,
     * which broke after the min selection triggered a partial panel re-render.
     *
     * More-robust approach: select both dropdowns by their position inside the
     * filter panel (index 1 = min, index 2 = max) using an nth-of-type/position
     * XPath so we are independent of class names and div counters.
     */
    private final By minAreaSelect   =
            By.xpath("(//*[@id='moreFilter_0']//select[@class='filter-budget__select'])[1]");
    private final By maxAreaSelect   =
            By.xpath("(//*[@id='moreFilter_0']//select[@class='filter-budget__select'])[2]");

    private final By applyFiltersBtn = By.xpath("//div[@class='filter__component__btn']");

    public PlotPage(WebDriver driver) {
        super(driver);
    }

    public void openPlotPage() {
        driver.get(PLOT_URL);
        driver.manage().window().maximize();
        waitVisible(filterSpec);
    }

    public String getFilterSpec() {
        return waitVisible(filterSpec).getText();
    }

    public void clickMoreFilters() {
        waitClickable(moreFiltersBtn).click();
        // Wait for the min select to appear before proceeding
        waitVisible(minAreaSelect);
    }

    /**
     * TC12 FIX – min dropdown:
     * After the panel opens, scroll the min select into view, then use
     * Select.selectByValue so we rely on the <option value=""> attribute
     * rather than visible text (avoids locale/formatting issues).
     */
    public void selectMinBudget(String value) {
        WebElement minEl = waitVisible(minAreaSelect);
        jsScrollIntoView(minEl);
        new Select(waitClickable(minAreaSelect)).selectByValue(value);
        // Wait for the max select to stabilise after the min change triggers re-render
        wait.until(ExpectedConditions.elementToBeClickable(maxAreaSelect));
    }

    /**
     * TC12 FIX – max dropdown ElementNotInteractableException root cause:
     * After selecting the min value, MagicBricks re-renders the filter panel
     * via JS.  The max <select> element is momentarily hidden/rebuilt in the DOM.
     * The previous code immediately called .click() on the <option> element
     * which was either stale or not yet visible.
     *
     * Fix:
     *   1. Re-fetch the max select after the panel has stabilised.
     *   2. Scroll it into view.
     *   3. Use Select.selectByValue() – this directly sets the value via
     *      Selenium's Select API without needing to click a <option> element
     *      that may be visually hidden (the browser expands the dropdown
     *      internally, so visibility of options is not required).
     */
    public void selectMaxBudget(String value) {
        // Re-fetch the element – the panel may have been rebuilt after min selection
        WebElement maxEl = waitVisible(maxAreaSelect);
        jsScrollIntoView(maxEl);
        // Use Select API directly – avoids clicking the hidden <option>
        new Select(waitClickable(maxAreaSelect)).selectByValue(value);
    }

    public void clickApplyFilters() {
        waitClickable(applyFiltersBtn).click();
        waitVisible(filterSpec);
    }
}