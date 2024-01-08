package driver;

import org.openqa.selenium.WebDriver;

public class ExecutionNode {
    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    static ThreadLocal<String> scenarioName = new ThreadLocal<String>();

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
