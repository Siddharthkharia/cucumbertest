package stepDefinations;

import driver.ExecutionNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.BaseTest;
import org.junit.AfterClass;

public class hooks extends BaseTest {
    @Before
    public void init(Scenario scenario){
        addScenario(scenario.getName());
        driver.driver.initDriver();
        ExecutionNode.setScenarioName(scenario.getName());
    }

    @After
    public void TearDown(){
        System.out.println("PersonalLOGGER_Quit Quiting and flushing driver from ExcutionNode");
        ExecutionNode.getDriver().quit();
        ExecutionNode.flushDriver();
    }

}
