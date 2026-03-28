package runnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/featureFile/Buy.feature"},
glue= {
		"hooks",
		"stepDefinition"
},
dryRun=false)
//dryRun=true when we don't have implementations yet in stepDefinition

public class Runner extends AbstractTestNGCucumberTests{
	
}
