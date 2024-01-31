package stepDefinations;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import pom.AmazonPOM;
import org.example.BaseTest;
import driver.ExecutionNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utilities.utils.snapshot;


public class StepDefinitions extends BaseTest {


    @Given("Initialize and launch {string}")
    public void intiBrowser(String url){
        ExecutionNode.getDriver().get(url);
    }

    @Then("validate status is {}")
    public void validate_status(){
    }

    @Then("take screenshot of the webpage")
    public void takeScreenshot() {
        snapshot(ExecutionNode.getDriver());
    }

    @And("search for the {string}")
    public void search_product(String product) {
        WebElement searchbar = ExecutionNode.getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchbar.sendKeys(product);
        snapshot(ExecutionNode.getDriver());
        WebElement submitSearch = ExecutionNode.getDriver().findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        submitSearch.submit();
        snapshot(ExecutionNode.getDriver());
        List<WebElement> searchList = ExecutionNode.getDriver().findElements(By.xpath("//div[@data-component-type='s-search-result']"));
        for (WebElement el : searchList) {
            File file = el.getScreenshotAs(OutputType.FILE);
            try {
                snapshot(el);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String price = el.findElement(By.xpath("//span[@class='a-price']//following::span[@class='a-price-whole']")).getText();
            System.out.println("price for the element = " + price);
        }
    }

    @And("search for the data of products")
    public void search_product(DataTable dataTable) {
        List<String> productList = dataTable.asList(String.class);
        for (String product :productList){
            WebElement searchbar = ExecutionNode.getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
            searchbar.clear();
            searchbar.sendKeys(product);
            snapshot(ExecutionNode.getDriver());
            WebElement submitSearch = ExecutionNode.getDriver().findElement(By.xpath("//input[@id='nav-search-submit-button']"));
            submitSearch.submit();
            snapshot(ExecutionNode.getDriver());
            List<WebElement> searchList = ExecutionNode.getDriver().findElements(By.xpath("//div[@data-component-type='s-search-result']"));
            int counter = 0;
            for (WebElement el : searchList) {
                try {
                    snapshot(el);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String price = el.findElement(By.xpath("//span[@class='a-price']//following::span[@class='a-price-whole']")).getText();
                System.out.println("price for the element = " + price);
            }
        }
    }

    @Given("Execute the amazon launch and search operation for the {string}")
    public void amazonSearchTest(String product){
        try{
            AmazonPOM.getInstance().launchHomepage();
            AmazonPOM.getInstance().searchProduct(product);
        }catch (Exception e){
            snapshot(ExecutionNode.getDriver());
        }
    }


    @And("search for the data of products from below map")
        public void Search_product(DataTable dataTable){
        List<String> productList = new ArrayList<>();
            List<Map<String, String>> mapList = dataTable.asMaps(String.class, String.class);
            for( Map<String, String> column : mapList){
                productList.add(column.get("productName"));
            }
            for( String product : productList){
                AmazonPOM.getInstance().searchProduct(product);
            }
        }

}
