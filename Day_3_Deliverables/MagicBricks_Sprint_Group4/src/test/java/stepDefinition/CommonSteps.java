package stepDefinition;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pagesTanistha.HomePage;

public class CommonSteps {

    public static WebDriver driver;
    public static String parentWindow; // ✅ make static so ProjectSteps can access it
    HomePage home;

    @Given("I open the MagicBricks Kolkata residential real estate page")
    public void i_open_the_magic_bricks_kolkata_residential_real_estate_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.magicbricks.com/property-for-sale-rent-in-Kolkata/residential-real-estate-Kolkata");
        home = new HomePage(driver); // ✅ initialize home AFTER driver is ready
    }

    @Given("I click on the {string} heading")
    public void i_click_on_the_heading(String string) {
        if (string.equalsIgnoreCase("Buy")) {
            home.getBuyClick(); // ✅ home is now initialized, won't throw NullPointerException
        }
    }

    @When("I click on {string} link")
    public void i_click_on_link(String string) {
        parentWindow = driver.getWindowHandle(); // ✅ save before new tab opens
        if (string.equalsIgnoreCase("Find an Agent")) {
            home.clickAgent();
        } else if (string.equalsIgnoreCase("Localities in Kolkata")) {
            home.clickLocality();
        } else if (string.equalsIgnoreCase("Projects in Kolkata")) {
            home.clickProjects(); // ✅ was wrongly calling clickAgent() instead
        }
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