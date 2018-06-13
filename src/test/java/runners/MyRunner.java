package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.deps.com.thoughtworks.xstream.core.Caching;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports/cucumber-html-report",
                "json:target/cucumber-reports/cucumber-json.json",
                "junit:target/cucumber-reports/Cucumber-junit.xml",
                "pretty"},
                monochrome = true,
        tags = {"@runit,@run","~@ignore"},
        features = {"src/test/resources"},
        glue = {"bindings","utilities"}
)


public class MyRunner {

}
