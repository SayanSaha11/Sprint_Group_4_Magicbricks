
package hooks.hooks_sayan;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.utils_sayan.DriverManager;

public class Hooks {

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        String browser = DriverManager.getBrowser().toUpperCase();

        System.out.printf("%n══════════════════════════════════════════════%n");
        System.out.printf("Browser  : %s%n", browser);
        System.out.printf("Thread   : %s%n", Thread.currentThread().getName());
        System.out.printf("Scenario : %s%n", scenario.getName());
        System.out.printf("══════════════════════════════════════════════%n%n");

        DriverManager.initDriver();
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) throws InterruptedException {
        Thread.sleep(4500);

        String browser = DriverManager.getBrowser().toUpperCase();

        System.out.printf("%n══════════════════════════════════════════════%n");
        System.out.printf("Browser  : %s%n", browser);
        System.out.printf("Scenario : %s%n", scenario.getName());
        System.out.printf("Status   : %s%n", scenario.getStatus());
        System.out.printf("══════════════════════════════════════════════%n%n");

        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", 
                        "[" + browser + "] FAILED – " + scenario.getName());
            } catch (Exception ignored) {
                System.out.println("Could not capture screenshot");
            }
        }

        DriverManager.quitDriver();
    }
}