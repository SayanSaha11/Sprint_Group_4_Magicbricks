//package hooks;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.github.bonigarcia.wdm.WebDriverManager;   // ← ADD this import
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import utils.DriverManager;
//
//import java.time.Duration;
//
//public class Hooks {
//
//    @Before
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();     // ← ADD this line
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        DriverManager.setDriver(driver);
//    }
//
//    @After
//    public void tearDown() {
//        if (DriverManager.getDriver() != null) {
//            DriverManager.getDriver().quit();
//            DriverManager.removeDriver();
//        }
//    }
//}



package hooks.hooks_ankan;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.utils_ankan.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Hooks {

    public static ConcurrentHashMap<String, String> browserByTestName = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Long, String> threadBrowserMap = new ConcurrentHashMap<>();
    public static LinkedBlockingQueue<String> browserQueue = new LinkedBlockingQueue<>();

    public static void registerThreadBrowser(long threadId, String browser, int count) {
        threadBrowserMap.put(threadId, browser);
        browserQueue.add(browser);
        System.out.println("Registered → thread: " + threadId + " browser: " + browser);
    }

    @Before
    public void setUp(Scenario scenario) {
        long threadId = Thread.currentThread().getId();
        String browserName = threadBrowserMap.get(threadId);

        if (browserName == null) {
            try {
                browserName = browserQueue.poll(10, java.util.concurrent.TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                browserName = "chrome";
            }
            if (browserName == null) browserName = "chrome";
            threadBrowserMap.put(threadId, browserName);
        }

        WebDriver driver;
        switch (browserName.toLowerCase().trim()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(new EdgeOptions());
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions());
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.setDriver(driver);

        System.out.println("Browser: " + browserName
                + " | Thread: " + threadId
                + " | Scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (DriverManager.getDriver() != null) {
            try {
                DriverManager.getDriver().quit();
            } catch (Exception e) {
                System.out.println("Driver already closed: " + e.getMessage());
            } finally {
                DriverManager.removeDriver();
            }
        }
    }
}