package utilities;

import com.mongodb.MapReduceCommand;
import driver.ExecutionNode;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3.x2000.x09.xmldsig.ObjectType;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {
    public static String baseDir = "./Screenshots/";

    public static void snapshot(WebDriver oDriver) {

        String scenarioName = ExecutionNode.getScenarioName();
        long timestamp = System.currentTimeMillis();
        String filename = baseDir +scenarioName+"/WebDriver"+timestamp+".png";

        TakesScreenshot scrShot = ((TakesScreenshot)oDriver);
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filename));
            System.out.println("the Screenshot is taken and saved as = "+filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void snapshot(WebElement webElement) throws IOException {
        String scenarioName = ExecutionNode.getScenarioName();
        long timestamp = System.currentTimeMillis();
        String filename= baseDir+scenarioName+"/WebElementScr"+timestamp+".png";
        File file = webElement.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filename));
            System.out.println("the Screenshot is taken and saved as : "+filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    public static String getCurrentDateTime(){

        DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String snapshot(String scenarioStatus) {

        String scenarioName = ExecutionNode.getScenarioName();
        String timestamp = getCurrentDateTime();
        String filename = baseDir +scenarioStatus+"/"+scenarioName+"/WebDriver"+timestamp+".png";

        TakesScreenshot scrShot = ((TakesScreenshot)ExecutionNode.getDriver());
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filename));
            System.out.println("the Screenshot is taken and saved as = "+filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filename;
    }

}
