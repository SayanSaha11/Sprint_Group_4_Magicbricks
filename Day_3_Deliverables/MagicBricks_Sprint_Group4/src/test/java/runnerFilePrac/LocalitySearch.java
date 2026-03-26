package runnerFilePrac;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/featureFile_prac/LocalitySearch.feature"},
glue="stepDefinition",
dryRun=true)
//dryRun=true when we don't have implementations yet in stepDefinition

public class LocalitySearch extends AbstractTestNGCucumberTests{
	
}
