package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class StepDefinitions {

    public WebDriver oDriver;
    static int ScrShotCtr=0;
    WebDriverWait owait ;

    public Map<Integer,String> scenarios = new HashMap<Integer, String>();

    @Given("Initialize {string} driver and launch {string}")
    public void intiBrowser(String browser, String url){

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            this.oDriver = new ChromeDriver();
        }else{
            WebDriverManager.firefoxdriver().setup();
            this.oDriver = new FirefoxDriver();
        }
        owait = new WebDriverWait(oDriver,2000);
        this.oDriver.get(url);

    }

    @Then("validate status is {}")
    public void validate_status(){

    }

    @Then("take screenshot of the webpage")
    public void takeScreenshot() {
        int count = ScrShotCtr;

        String scenarioName = scenarios.get(Thread.currentThread().hashCode());
        TakesScreenshot scrShot = ((TakesScreenshot) oDriver);
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./Screenshots/" + scenarioName + "_" + count + ".png"));
            System.out.println("the Screenshot is taken and saved as " + scenarioName + "_" + count + ".png");
            ScrShotCtr++;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Before
    public void init(Scenario scenario){
        addScenario(scenario.getName());
    }

    @After
    public void browserTearDown(){
        this.oDriver.quit();
    }

    public void addScenario(String scenario){
        Thread currentThread = Thread.currentThread();
        int threadId = currentThread.hashCode();
        scenarios.put(threadId,scenario);
    }


}
