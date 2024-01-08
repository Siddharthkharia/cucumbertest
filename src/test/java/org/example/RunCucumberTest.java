package org.example;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
features = {"src/test/resources/featureFiles"},
        glue = {"stepDefinations"})
public class RunCucumberTest {


}
