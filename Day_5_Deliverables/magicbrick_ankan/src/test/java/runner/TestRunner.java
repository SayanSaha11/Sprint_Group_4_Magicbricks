package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report.html",   // your existing report
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // ⭐ added
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // @DataProvider(parallel = false)  // ← change this
    public Object[][] scenarios() {
        return super.scenarios();
    }
}