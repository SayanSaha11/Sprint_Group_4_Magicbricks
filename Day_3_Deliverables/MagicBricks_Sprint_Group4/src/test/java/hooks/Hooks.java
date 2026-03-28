package hooks;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import stepDefinition.Common;

public class Hooks {

    @Before
    public void setup() {
        Common.driver = new ChromeDriver();

        Common.driver.manage().window().maximize();
        Common.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (Common.driver != null) {
            Common.driver.quit();
            Common.driver = null;
        }
    }
}