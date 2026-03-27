package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Thread-safe WebDriver holder using ThreadLocal.
 *
 * Why ThreadLocal?
 *   Each Cucumber scenario runs in its own thread when parallel = true.
 *   ThreadLocal guarantees every thread has its own isolated WebDriver instance
 *   with no shared state between scenarios.
 *
 * Lifecycle (managed by Cucumber hooks in MagicBricksStepDefinitions):
 *   @Before → DriverManager.initDriver()    creates a new ChromeDriver
 *   @After  → DriverManager.quitDriver()    quits and removes from ThreadLocal
 *
 * All Page classes call DriverManager.getDriver() instead of receiving
 * a WebDriver via constructor injection, enabling no-arg constructors.
 */
public class DriverManager {

    // One WebDriver instance per thread
    private static final ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();

    // Utility class – no instantiation
    private DriverManager() {}

    /**
     * Sets up ChromeDriver using WebDriverManager and stores it in ThreadLocal.
     * Must be called from the @Before hook before any Page object is used.
     */
    public static void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Uncomment below for headless execution in CI pipelines:
        // options.addArguments("--headless=new");
        // options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driverHolder.set(driver);
    }

    /**
     * Returns the WebDriver for the current thread.
     *
     * @throws IllegalStateException if initDriver() was not called first
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverHolder.get();
        if (driver == null) {
            throw new IllegalStateException(
                "WebDriver not initialised. Call DriverManager.initDriver() in @Before hook.");
        }
        return driver;
    }

    /**
     * Quits the current driver and removes it from ThreadLocal.
     * Must be called from the @After hook to prevent memory leaks.
     */
    public static void quitDriver() {
        WebDriver driver = driverHolder.get();
        if (driver != null) {
            driver.quit();
            driverHolder.remove();
        }
    }
}