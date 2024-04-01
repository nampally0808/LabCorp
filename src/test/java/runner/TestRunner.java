package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources"
,glue="com.labcorp.www.stepdefinitions", plugin = { "pretty", "html:target/cucumber-reports" }		)
public class TestRunner {
	

}
