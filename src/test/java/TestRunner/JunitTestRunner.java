package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\resources\\features\\Weather.feature"
		, glue = { "StepDefinations"}
		,tags= "@NDTV"
		,dryRun = false
		
		,plugin=  { "pretty", "html:target/cucumber-report.html" }
		
				
		)

public class JunitTestRunner {

}
