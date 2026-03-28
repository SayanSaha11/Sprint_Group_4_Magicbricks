package hooks_Tanistha;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities_Tanistha.DriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.initDriver(browser);
        
        scenario.log("Browser: " + browser.toUpperCase());
    }

    @After
    public void tearDown(Scenario scenario) {

        DriverManager.quitDriver();
    }
}
