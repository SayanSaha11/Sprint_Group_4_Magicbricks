//package runner;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.DataProvider;
//
//@CucumberOptions(
//    features = "src/test/resources/features",
//    glue = {"stepDefinitions", "hooks"},
//    plugin = {
//        "pretty",
//        "html:target/cucumber-reports/report.html",   // your existing report
//        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // ⭐ added
//    },
//    monochrome = true
//)
//public class TestRunner extends AbstractTestNGCucumberTests {
//
//    @Override
//    @DataProvider(parallel = true) // @DataProvider(parallel = false)  // ← change this
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
//}



package runner.runner_ankan;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import hooks.hooks_ankan.Hooks;

@CucumberOptions(
    features = "src/test/resources/features/features_ankan",
    glue = {"stepDefinitions.stepDefinitions_ankan", "hooks.hooks_ankan"},
	plugin = {
	    "pretty",
	    "html:target/cucumber-reports/report.html",
	    "json:target/cucumber-reports/cucumber.json",
	    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
	},
monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private String browser;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setBrowser(@Optional("chrome") String browser, ITestContext context) {
        this.browser = browser;
        Hooks.browserByTestName.put(context.getCurrentXmlTest().getName(), browser);
        System.out.println("TestRunner → browser: " + browser
                + " | test: " + context.getCurrentXmlTest().getName()
                + " | thread: " + Thread.currentThread().getId());
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        Hooks.registerThreadBrowser(Thread.currentThread().getId(), browser, 1);
        return super.scenarios();
    }
}
