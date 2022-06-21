package runner;

import io.cucumber.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", "json:target/cucumber.json",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "step_definitions",
        dryRun = false,
        tags = ""
)
public class CukesRunner {

}
