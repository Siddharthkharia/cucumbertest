package driver;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class ExecutionNode {
    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    static ThreadLocal<String> scenarioName = new ThreadLocal<String>();

    static ThreadLocal<ExtentTest> extentTestLocal = new ThreadLocal();
    static ThreadLocal<ExtentTest> extentStep = new ThreadLocal();

    public static ExtentTest getExtentstep() {
        return extentStep.get();
    }

    public static void setExtentStep(ExtentTest extent_step) {
        extentStep.set(extent_step);
    }

    public static void flushExtentStep (){
        extentStep.remove();
    }

    public static ExtentTest getExtentTest() {
        return extentTestLocal.get();
    }

    public static void setExtentTest(ExtentTest extentTest) {
        ExecutionNode.extentTestLocal.set(extentTest);
    }

    public static void flushExtentTest(){
        extentTestLocal.remove();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver); ;
    }

    public static void flushDriver (){
        driver.remove();
    }

    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static void setScenarioName(String scenario) {
        scenarioName.set(scenario); ;
    }

    public static void removeScenarioName (){
        scenarioName.remove();
    }



}
