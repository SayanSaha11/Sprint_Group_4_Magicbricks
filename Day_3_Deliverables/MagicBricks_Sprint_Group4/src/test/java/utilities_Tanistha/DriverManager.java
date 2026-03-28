package utilities_Tanistha;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> parentWindowThreadLocal = new ThreadLocal<>();
    
    private static final Map<String, Object> BROWSER_PREFS = Map.of(
    	    "profile.default_content_setting_values.notifications", 2,
    	    "profile.default_content_setting_values.geolocation", 2,
    	    "profile.default_content_setting_values.media_stream", 2,
    	    "profile.default_content_setting_values.popups", 2
    );
    //2 -> blocks 
    
    private static final String[] BROWSER_ARGS = {
            "--disable-notifications",
            "--disable-popup-blocking",
            "--disable-infobars"
        };
    //chrome.exe --disable-notifications --disable-infobars
    //command-line args

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase().trim()) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(BROWSER_ARGS);
                edgeOptions.setExperimentalOption("prefs", BROWSER_PREFS);
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(BROWSER_ARGS);
                chromeOptions.setExperimentalOption("prefs", BROWSER_PREFS);
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }


    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    public static String getParentWindow() {
        return parentWindowThreadLocal.get();
    }

    public static void setParentWindow(String window) {
        parentWindowThreadLocal.set(window);
    }
}