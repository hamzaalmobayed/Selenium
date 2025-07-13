package base;


import DBConnection.DB;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected ThreadLocal<WebDriver>  driver = new ThreadLocal<>();
//    public static List<String> merchantOrders=new ArrayList<>();
//    public static List<String> merchantWallet=new ArrayList<>();
//    public static List<String> merchantName=new ArrayList<>();

     public WebDriver getDriver(){
        return this.driver.get();
     }

    public void setDriver(WebDriver driver) {
    this.driver.set(driver);
     }

    @BeforeTest
    public void setup(){
        WebDriver driver = new DriverFactory().initializeDriver("chrome");
        setDriver(driver);
//        try{
//            DB db = new DB(); // Create an instance of your DB class
//
//            // Call the methods to fetch data from the database and perform any other setup
//            merchantOrders = db.fetchMerchantOrdersFromDatabase();
//            merchantWallet = db.fetchMerchantWalletFromDatabase();
//            merchantName = db.fetchMerchantNameFromDatabase();
//            System.out.println(merchantName);
//            System.out.println(merchantOrders);
//            System.out.println(merchantWallet);
//        }catch (Exception e){
//            System.out.println();
//        }
     }

//    @AfterTest
//    public void teardown(){
//        getDriver().quit();
//    }

}
