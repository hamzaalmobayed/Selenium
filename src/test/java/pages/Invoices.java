package pages;

import DBConnection.DB;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcases.LoginTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Invoices extends BasePage {
    //The Elements
    @FindBy(tagName = "thead")
    private WebElement table;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-invoice/section[1]/div[3]/table/tbody/tr/td[1]")
    private List<WebElement> results;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-invoice/section[1]/div[2]/div/div/div[1]/input")
    private WebElement searchFld;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-invoice/section[1]/div[2]/div/div/div[2]/div/button")
    private WebElement filterButton;
    @FindBy(xpath = "//*[@id=\"start-date\"]")
    private WebElement startDate;
    @FindBy(xpath = "//*[@id=\"end-date\"]")
    private WebElement endDate;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-invoice/section[1]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/button")
    private WebElement applyButton;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-invoice/section[1]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/button")
    private WebElement restButton;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[4]/a")
    private WebElement ordersTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[2]/div[1]/table/thead/tr/th[1]")
    private WebElement titleOrders;




    public Invoices(WebDriver driver) {
        super(driver);
    }
    DB db=new DB();
    public void checkTitleOfColumnInInvoices() throws Exception {
        // Define the expected titles
        List<String> expectedTitles = Arrays.asList("Order ID", "Ordered On", "Discount", "Sales", "Commission", "Net", "Action");

        // Get the titles from the table
        List<WebElement> titles = table.findElements(By.tagName("th"));

        // Extract the titles as text
        List<String> extractedTitles = titles.stream().map(WebElement::getText).collect(Collectors.toList());

        // Compare the expected titles with the extracted titles
        Assert.assertEquals(extractedTitles, expectedTitles, "Column titles do not match");
    }

    public void checkFilterInInvoices() throws Exception {
        // Click on the filter button and set filter criteria
        filterButton.click();
        waitElementToClickable(driver, filterButton);
        setTextElementText(startDate, "09/09/2023");
        setTextElementText(endDate, "15/09/2023");
        applyButton.click();
        waitElementToClickable(driver, applyButton);

        // Print and compare filtered results
        System.out.println(db.fetchMerchantInvoicessAndFilterItFromDatabase("2023-09-09", "2023-09-15"));
        Assert.assertEquals(results.size(), db.fetchMerchantInvoicessAndFilterItFromDatabase("2023-09-09", "2023-09-15").size());

        // Reset the filter
        filterButton.click();
        waitElementToClickable(driver, filterButton);
        restButton.click();
        waitElementToClickable(driver, restButton);
        Thread.sleep(1000);
    }

    public void checkSearch(String order) throws Exception {
        // Search for the provided order and validate the search results
        Thread.sleep(1000);
        List<String> searchResult = new ArrayList<>();
        setTextElementText(searchFld, order);
        Thread.sleep(1000);

        for (WebElement element : results) {
            searchResult.add(element.getText());
        }

        boolean isAllResultsAsSearched = searchResult.stream().allMatch(result -> result.contains(order));

        if (!searchResult.isEmpty()) {
            System.out.println(db.fetchMerchantInvoicesAndSearchInItFromDatabase(order));
            System.out.println(searchResult);
            Assert.assertEquals(db.fetchMerchantInvoicesAndSearchInItFromDatabase(order), searchResult);
            Assert.assertTrue(isAllResultsAsSearched);
        }

        Thread.sleep(1000);
    }

    public Orders clickOrders() throws Exception {
        // Navigate to the Orders page
        Thread.sleep(1000);
        backToDashboard();
        Thread.sleep(1000);
        ordersTab.click();
        waitElementToClickable(driver, titleOrders);
        return new Orders(driver);
    }


}
