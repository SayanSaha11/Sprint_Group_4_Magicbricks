package pages.pages_sayan;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class PlotPage extends BasePage {

    // ─── URL ─────────────────────────────────────────────────────────────────
    private static final String PLOT_URL =
        "https://www.magicbricks.com/residential-plots-land-for-sale-in-mumbai-pppfs";

    private final WebDriverWait longWait;


    private final By initialFilterLabel =
        By.xpath("//div[@class='filter__component__title more-title']");

    private final By updatedFilterLabel =
        By.xpath("//div[@class='top-filter__item-all-filter']");

    private final By moreFiltersBtn =
        By.xpath("//div[text()='More Filters']");

    private final By moreFilterPanel =
        By.xpath("//*[@id='moreFilter_0']");

    private final By minAreaSelect =
        By.xpath("//*[@id='moreFilter_0']/div[2]/div/div/div/div[1]/div[1]/select");

    private final By maxAreaSelect =
        By.xpath("(//*[@id='moreFilter_0']/div[2]/div/div/div/div[1]/div/select)[2]");

    private final By applyFiltersBtn =
        By.xpath("//div[@class='filter__component__btn']");

    private final By plotAreaCards =
        By.xpath("//div[@data-summary='plot-area']");

    public PlotPage() {
        super();
        // 45-second wait for the filter panel (slow on MagicBricks)
        longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openPlotsPage() {
        driver.get(PLOT_URL);

        // Wait up to 10 s for a second tab to appear 
        String mainTab = driver.getWindowHandle();
        try {
            longWait.until(d -> d.getWindowHandles().size() > 1);
            // Switch to the new (plots listing) tab — exclude the original
            Set<String> handles = driver.getWindowHandles();
            for (String tab : handles) {
                if (!tab.equals(mainTab)) {
                    driver.switchTo().window(tab);
                    break;
                }
            }
        } catch (Exception e) {
            // No second tab opened — we are already on the correct tab
            System.out.println("[PlotPage] No second tab detected; staying on current tab.");
        }

        waitPageReady();

        // Wait for the initial filter label to confirm the SRP is usable
        longWait.until(ExpectedConditions.visibilityOfElementLocated(initialFilterLabel));
    }

    public String getFilterSpec() {
        List<WebElement> updated = driver.findElements(updatedFilterLabel);
        if (!updated.isEmpty() && updated.get(0).isDisplayed()) {
            return updated.get(0).getText();
        }
        return longWait.until(
            ExpectedConditions.visibilityOfElementLocated(initialFilterLabel)).getText();
    }

    public void clickMoreFilters() {

        WebElement btn = longWait.until(
                ExpectedConditions.elementToBeClickable(moreFiltersBtn));

        jsScrollIntoView(btn);
        btn.click();

        // Wait for panel container first (IMPORTANT)
        WebElement panel = longWait.until(
                ExpectedConditions.visibilityOfElementLocated(moreFilterPanel));

        // Ensure panel is fully expanded (height > 0)
        longWait.until(driver -> panel.getSize().height > 0);
    }

    public void selectMinPlotArea(String value) {

        WebElement minEl = longWait.until(
                ExpectedConditions.presenceOfElementLocated(minAreaSelect));

        jsScrollIntoView(minEl);

        try {

            longWait.until(ExpectedConditions.elementToBeClickable(minEl));

            minEl.click(); // open dropdown

            Select select = new Select(minEl);
            select.selectByValue(value);

        } catch (Exception e) {

            System.out.println("⚠️ Normal select failed, using JS fallback");

            // JS fallback (handles hidden/custom UI)
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                "arguments[0].value='" + value + "';" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                minEl
            );
        }
    }

    public void selectMaxPlotArea(String value) {

        WebElement maxEl = longWait.until(
                ExpectedConditions.presenceOfElementLocated(maxAreaSelect));

        jsScrollIntoView(maxEl);

        try {
            //  Try normal Selenium interaction first
            longWait.until(ExpectedConditions.elementToBeClickable(maxEl));

            maxEl.click(); // open dropdown

            Select select = new Select(maxEl);
            select.selectByValue(value);

        } catch (Exception e) {

            System.out.println("⚠️ Normal select failed, using JS fallback");

            // JS fallback (stronger event trigger)
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                "arguments[0].value='" + value + "';" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                maxEl
            );
        }
    }

    public void clickApplyFilters() {

        WebElement btn = longWait.until(
                ExpectedConditions.elementToBeClickable(applyFiltersBtn));

        jsScrollIntoView(btn);
        btn.click();

        longWait.until(ExpectedConditions.visibilityOfElementLocated(updatedFilterLabel));
    }

    public void printFirst5PlotAreas() {
        List<WebElement> areas = driver.findElements(plotAreaCards);
        for (int i = 0; i < 5 && i < areas.size(); i++) {
            System.out.println("Plot Area of Property " + (i + 1)
                    + " is : " + areas.get(i).getText());
        }
    }


    public List<WebElement> getPlotAreaElements() {
        return driver.findElements(plotAreaCards);
    }


}