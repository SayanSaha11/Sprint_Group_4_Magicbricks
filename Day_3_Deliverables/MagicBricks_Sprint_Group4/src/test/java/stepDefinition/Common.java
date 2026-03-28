package stepDefinition;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pagesTanistha.HomePage;

public class Common {

    public static WebDriver driver;
    public static String parentWindow; // ✅ make static so ProjectSteps can access it
    HomePage home;

    @Given("I open the MagicBricks Kolkata residential real estate page")
    public void i_open_the_magic_bricks_kolkata_residential_real_estate_page() {
    	 Common.driver.get("https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");
    	    ((JavascriptExecutor) Common.driver).executeScript("window.focus();");
    }
    
    @Given("the new projects page loads with URL containing {string}")
    public void the_new_projects_page_loads_with_url_containing(String urlFragment) {
        // ✅ Switch to new tab that opened after Projects link click
        Set<String> windows = driver.getWindowHandles();
        for (String w : windows) {
            if (!w.equals(parentWindow)) {
                driver.switchTo().window(w);
                break;
            }
        }
        // ✅ Verify URL
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains(urlFragment)
            : "Expected URL to contain '" + urlFragment + "' but got: " + currentUrl;
    }
}