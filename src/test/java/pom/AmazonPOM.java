package pom;

import driver.ExecutionNode;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utilities.utils.*;


public abstract class AmazonPOM extends BaseTest {
    public static By searchbar;
    public static By submitSearch;
    public static By searchList;


    synchronized public static AmazonPOM getInstance(){
        return new Amazon_Web();
    }

    public void launchHomepage(){
        ExecutionNode.getDriver().get("https://www.amazon.in");
    }

    public void searchProduct (String productName){
        ExecutionNode.getDriver().findElement(searchbar).clear();
        ExecutionNode.getDriver().findElement(searchbar).sendKeys(productName);
        snapshot(ExecutionNode.getDriver());
        ExecutionNode.getDriver().findElement(submitSearch).submit();
        snapshot(ExecutionNode.getDriver());

        List<WebElement> ResultList = ExecutionNode.getDriver().findElements(searchList);
        for(WebElement el : ResultList){
            try{
                snapshot(el);
            }catch (Exception e){
                System.out.println("Exception while taking screenshot of the element");
            }
        }
    }
}
class Amazon_Web extends AmazonPOM {

    public Amazon_Web() {
        searchbar = By.xpath("//input[@id='twotabsearchtextbox']");
        submitSearch = By.xpath("//input[@id='nav-search-submit-button']");
        searchList = By.xpath("//div[@data-component-type='s-search-result']");
    }
}



