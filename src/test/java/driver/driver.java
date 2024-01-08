package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driver {

    public static void initDriver(){
        if(ExecutionNode.getDriver()==null){
            WebDriverManager.chromedriver().setup();
            WebDriver oDriver = new ChromeDriver();
            ExecutionNode.setDriver(oDriver);
        }
    }
}
