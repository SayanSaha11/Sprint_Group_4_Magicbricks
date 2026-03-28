package runnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/featureFile/Locality.feature"},
glue={
"hooks",
"stepDefinition",
"StepDeifinitionLocal"
},
dryRun=false)
//dryRun=true when we don't have implementations yet in stepDefinition

public class LocalityBuyRunner extends AbstractTestNGCucumberTests{
	
}
