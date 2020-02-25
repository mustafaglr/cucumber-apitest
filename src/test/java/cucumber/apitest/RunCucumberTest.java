package cucumber.apitest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

	features= {"src/test/java/cucumber/apitest/"},
	plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber.json"}
	
)
public class RunCucumberTest {
}
