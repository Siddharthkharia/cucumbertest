package org.example;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml",
        "html:target/cucumber-reports/cucumber-html-report"},
        features = {"src/test/resources/featureFiles"},
        glue = {"stepDefinations"},
        monochrome = true)
public class RunCucumberTest {


}
