package runner.runner_sayan;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.utils_sayan.DriverManager;

@CucumberOptions(
    features = "src/test/resources/features/features_sayan",
    glue = {"stepDefinitions.stepDefinitions_sayan", "hooks.hooks_sayan"},
    plugin = {
        "pretty",
	    "html:target/cucumber-reports/report.html",
	    "json:target/cucumber-reports/cucumber.json",
	    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,
    tags = "not @ignore"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setBrowserFromXml(@Optional("chrome") String browser) {
        System.out.printf("%n╔══════════════════════════════════════════════╗%n");
        System.out.printf("║  Browser : %-33s║%n", browser.toUpperCase());
        System.out.printf("║  Thread  : %-33s║%n", Thread.currentThread().getName());
        System.out.printf("╚══════════════════════════════════════════════╝%n%n");

        DriverManager.setBrowser(browser);

        // Extent Report can pick up the browser name
        System.setProperty("browser", browser.toUpperCase());
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}