package stepDefinitions.stepDefinition_Tanistha;

import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import pages.pagesTanistha.*;
import utils.utilities_Tanistha.*;

public class Common {

    HomePage home;

    @Given("I open the MagicBricks Kolkata residential real estate page")
    public void i_open_the_magic_bricks_kolkata_residential_real_estate_page() {
        DriverManager.getDriver().get(
            "https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata"
        );
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.focus();");
    }

    @Given("the new projects page loads with URL containing {string}")
    public void the_new_projects_page_loads_with_url_containing(String urlFragment) {
        WebDriver driver = DriverManager.getDriver();
        Set<String> windows = driver.getWindowHandles();
        for (String w : windows) {
            if (!w.equals(DriverManager.getParentWindow())) {
                driver.switchTo().window(w);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains(urlFragment)
            : "Expected URL to contain '" + urlFragment + "' but got: " + currentUrl;
    }
}