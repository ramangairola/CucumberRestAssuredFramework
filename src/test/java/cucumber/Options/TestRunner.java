package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",glue = {"stepDefinations"},tags = {"@DeletePlaceAPI"},plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
}