package runnerFilePrac;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/featureFile_prac/AgentView.feature"},
glue="stepDefinition_prac",
dryRun=false)
//dryRun=true when we don't have implementations yet in stepDefinition

public class AgentViewRunner extends AbstractTestNGCucumberTests{
	
}
