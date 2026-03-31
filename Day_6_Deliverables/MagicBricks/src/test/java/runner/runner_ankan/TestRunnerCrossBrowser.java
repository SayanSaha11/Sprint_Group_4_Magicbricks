package runner.runner_ankan;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
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
public class TestRunnerCrossBrowser extends AbstractTestNGCucumberTests {

    private String browser;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setBrowser(@Optional("chrome") String browser) {
        this.browser = browser;
        Hooks.registerThreadBrowser(Thread.currentThread().getId(), browser, 1);
        System.out.println("TestRunnerCrossBrowser → browser: " + browser
                + " | thread: " + Thread.currentThread().getId());
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}











































//package runner.runner_ankan;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.ITestContext;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//import hooks.hooks_ankan.Hooks;
//
//@CucumberOptions(
//    features = "src/test/resources/features/features_ankan",
//    glue = {"stepDefinitions.stepDefinitions_ankan", "hooks.hooks_ankan"},
//    plugin = {
//        "pretty",
//        "html:target/cucumber-reports/report.html",
//        "json:target/cucumber-reports/cucumber.json",
//        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//    },
//    monochrome = true
//)
//public class TestRunnerCrossBrowser extends AbstractTestNGCucumberTests {
//
//    private String browser;
//
//    @Parameters("browser")
//    @BeforeClass(alwaysRun = true)
//    public void setBrowser(@Optional("chrome") String browser, ITestContext context) {
//        this.browser = browser;
//        Hooks.browserByTestName.put(context.getCurrentXmlTest().getName(), browser);
//        // Register ALL scenarios for this browser upfront
//        Object[][] scenarios = super.scenarios();
//        for (int i = 0; i < scenarios.length; i++) {
//            Hooks.browserQueue.add(browser);
//        }
//        System.out.println("TestRunnerCrossBrowser → browser: " + browser
//                + " | scenarios: " + scenarios.length
//                + " | thread: " + Thread.currentThread().getId());
//    }
//
//    @Override
//    @DataProvider(parallel = false)
//    public Object[][] scenarios() {
//        Hooks.registerThreadBrowser(Thread.currentThread().getId(), browser, 1);
//        return super.scenarios();
//    }
//}



