package stepDefinations;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import driver.ExecutionNode;
import io.cucumber.java.*;
import io.cucumber.messages.types.Step;
import io.cucumber.messages.types.TestStep;
import io.cucumber.messages.types.TestStepResult;
import org.example.BaseTest;
import utilities.utils;

import java.io.File;
import java.io.IOException;

public class hooks extends BaseTest {

    public static ExtentReports extentReports;

    @BeforeAll
    public static void BeforeClass(){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File("./Reports/extentReport"+ utils.getCurrentDateTime()+".html"));
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }
    @Before
    public void init(Scenario scenario){
        addScenario(scenario.getName());
        driver.driver.initDriver();
        ExecutionNode.setScenarioName(scenario.getName());
        ExtentTest extentTest = extentReports.createTest(scenario.getName());
        ExecutionNode.setExtentTest(extentTest);
    }

//    @BeforeStep
    public void beforeEachStep(Step step){
        ExtentTest extentStep = ExecutionNode.getExtentTest().createNode(step.getKeyword(),step.getText());
        ExecutionNode.setExtentStep(extentStep);
    }

//    @AfterStep
    public void afterEachStep(TestStepResult testStepResult){
        ExecutionNode.getExtentstep().log(Status.PASS,"Test Step details");
    }

    @After
    public void TearDown(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            System.out.println("taking snapshot for the failed scenario");
            utils.snapshot(ExecutionNode.getDriver());
        }

        switch (scenario.getStatus()){

            case PASSED:
                ExecutionNode.getExtentTest().pass(scenario.getName()+" Passed", MediaEntityBuilder.createScreenCaptureFromPath(utils.snapshot("Passed")).build());
                break;

            case FAILED:
                String path= utils.snapshot("Failed");
                ExecutionNode.getExtentTest().addScreenCaptureFromPath(path);
                System.out.println("Saving the screenshot on failure ");
                ExecutionNode.getExtentTest().fail(scenario.getName()+" Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                break;

            case SKIPPED:
                ExecutionNode.getExtentTest().skip(scenario.getName()+" Skipped", MediaEntityBuilder.createScreenCaptureFromPath(utils.snapshot("Skipped")).build());
                break;
        }

        System.out.println("ExecutionCompleted for testcase :"+ExecutionNode.getScenarioName());
        ExecutionNode.removeScenarioName();
        System.out.println("PersonalLOGGER_Quit Quiting and flushing driver from ExcutionNode");
        ExecutionNode.getDriver().quit();
        ExecutionNode.flushDriver();
    }


    @AfterAll
    public static void reportFlush(){
        extentReports.flush();
    }

}
