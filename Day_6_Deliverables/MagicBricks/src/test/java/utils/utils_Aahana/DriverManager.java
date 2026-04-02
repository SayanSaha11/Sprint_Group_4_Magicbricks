package utils_Aahana;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }

    public static void initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}