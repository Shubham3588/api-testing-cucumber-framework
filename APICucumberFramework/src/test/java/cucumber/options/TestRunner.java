package cucumber.options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",     // ← Use forward slashes only
    glue = {"stepDefinitions"}                // Optional: for clean output
)
public class TestRunner {}
