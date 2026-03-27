package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features  = "src/test/resources/features/MagicBricks.feature",
    glue      = "stepDefinitions",
    plugin    = {
        "pretty",
        "html:target/cucumber-reports/parallel/report.html",
        "json:target/cucumber-reports/parallel/report.json"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)   // ← this enables parallel scenario execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}