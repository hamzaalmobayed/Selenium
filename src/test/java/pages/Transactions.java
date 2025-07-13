package pages;

import DBConnection.DB;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transactions extends BasePage {
    DB db=new DB();
    //The Elements
    @FindBy(tagName = "thead")
    private WebElement table;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-wallet-transaction/div[1]/div/div/div/div[1]/div/div/input")
    private WebElement searchFld;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-wallet-transaction/div[1]/div/div/div/div[1]/div/div/button")
    private WebElement searchBtn;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[6]/a")
    private WebElement brandManagementTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brands-management/div[2]/div/div/div[2]/button")
    private WebElement titleBrandsManagement;


    public Transactions(WebDriver driver) {
        super(driver);
    }
    /**
     * Checks the title of columns in the Transactions table.
     *
     */
    public void checkTitleOfColumnInTransactions() throws Exception {
        // Expected column titles
        List<String> actualTitle = Arrays.asList("Transaction ID", "Date & Time", "Order Id", "Opening Balance", "Amount", "Closing Balance");

        // Extracted column titles from the web
        List<WebElement> titles = table.findElements(new By.ByTagName("th"));

        // Extracted titles as strings
        List<String> extractedTitles = new ArrayList<>();
        for (WebElement title : titles) {
            extractedTitles.add(title.getText());
        }

        // Compare the expected and extracted titles
        boolean areListsEqual = actualTitle.equals(extractedTitles);

        // Assert that the lists are equal
        Assert.assertTrue(areListsEqual, "Lists are not equal");
    }

    /**
     * Checks if the search functionality in the Transactions page works correctly.
     *-*
     */
    public void checkSearch(String transaction) throws Exception {
        Thread.sleep(1000);
        boolean isAllResultsAsSearched = false;
        List<String> searchResult = new ArrayList<>();

        // Set the text in the search field
        setTextElementText(searchFld, transaction);

        // Click the search button
        searchBtn.click();
        waitElementToClickable(driver, searchBtn);
        Thread.sleep(3000);

        // Get the search results from the web
        List<WebElement> results = driver.findElements(By.xpath("/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-wallet-transaction/div[1]/div/div/div/div[2]/div/div/div/table/tbody/tr/td[1]"));

        // Extract search results as strings
        for (WebElement element : results) {
            searchResult.add(element.getText());
        }

        // Check if the search results contain the expected transaction
        for (String element : searchResult) {
            isAllResultsAsSearched = element.contains(transaction);
            if (!isAllResultsAsSearched) break;
        }

        if (searchResult.size() != 0) {
            System.out.println(db.fetchMerchantTransactionsAndSearchInItFromDatabase(transaction).size() + " from db");
            System.out.println(searchResult.size() + " from web");
            Assert.assertEquals(db.fetchMerchantTransactionsAndSearchInItFromDatabase(transaction), searchResult);
            Assert.assertTrue(isAllResultsAsSearched);
        }

        Thread.sleep(1000);
    }

    /**
     * Clicks on the 'Brands Management' tab in the dashboard and navigates to the Brands Management page.
     *
     */
    public BrandsManagement clickBrandsManagement() throws Exception {
        Thread.sleep(1000);
        backToDashboard();
        Thread.sleep(1000);
        brandManagementTab.click();
        waitElementToClickable(driver, titleBrandsManagement);
        return new BrandsManagement(driver);
    }

}
