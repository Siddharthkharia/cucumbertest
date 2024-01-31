package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class driver {

    public static void initDriver(){
        if(ExecutionNode.getDriver()==null){
            System.out.println("PersonalLOGGER_INIT Launching new driver and setting in ExcutionNode");
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions= new ChromeOptions();
            chromeOptions.setHeadless(true);
            WebDriver oDriver = new ChromeDriver(chromeOptions);
            ExecutionNode.setDriver(oDriver);
        }else{
            System.out.println("PersonalLOGGER_NA driver already exists in ExcutionNode Doing Nothing");
        }
    }
}
