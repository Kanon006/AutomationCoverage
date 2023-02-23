package TestRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(tags="@Smoke", features = {"src/test/resources/Feature/postApi.feature"}, glue = {"StepDefinition"},   monochrome = true,
        dryRun = false,
        plugin = {
                "pretty","html:build/reports/feature.html"
        })

public class PostApiRunner extends AbstractTestNGCucumberTests {
}
