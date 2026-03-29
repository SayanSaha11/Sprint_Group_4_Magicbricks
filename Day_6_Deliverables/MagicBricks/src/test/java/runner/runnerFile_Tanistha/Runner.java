package runner.runnerFile_Tanistha;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
    features = {"src/test/resources/features/featureFile_Tanistha/Buy.feature"},
    glue = {"hooks.hooks_Tanistha", "stepDefinitions.stepDefinition_Tanistha"},
    plugin = {
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    dryRun = false
)
public class Runner extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters("browser")
    public void setBrowser(String browser) {
        System.setProperty("browser", browser);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}