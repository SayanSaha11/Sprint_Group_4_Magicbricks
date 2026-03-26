package runnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/featureFile/Tanistha_Buy2.feature"},
glue="stepDefinition",
dryRun=true)
//dryRun=true when we don't have implementations yet in stepDefinition

public class Tanistha_Buy extends AbstractTestNGCucumberTests{
	
}
