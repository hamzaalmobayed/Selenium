package pages;
import org.openqa.selenium.JavascriptExecutor;
import DBConnection.DB;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.ConfigUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Orders extends BasePage {
    //The Elements
    @FindBy(xpath ="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[1]/div/div[1]/div/div[2]/div[1]/span")
    WebElement totalCompletedOrders;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[1]/div/div[2]/div/div[2]/div[1]/span")
    WebElement totalInCompletedOrders;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[1]/div/div[1]/div/div[2]/div[2]/span")
    WebElement completedOrdersAmount;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[1]/div/div[2]/div/div[2]/div[2]/span")
    WebElement inCompletedOrdersAmount;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[1]/div/div/select")
    WebElement numberOfDisplayedOrdersInTable;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[2]/input")
    private WebElement searchFldInAllOrders;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr/td[1]")
    private List<WebElement> resultsOfAllOrders;
    @FindBy(xpath = "//*[@id=\"nav-profile-tab\"]")
    WebElement completedOrdersTab;
    @FindBy(xpath = "//*[@id=\"nav-contact-tab\"]")
    WebElement inCompletedOrdersTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[4]/div/button")
    private WebElement filterButton;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[4]/div/div/div[1]/div[2]/button")
    private WebElement applyButton;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[4]/div/div/div[1]/div[1]/button")
    private WebElement restButton;
    @FindBy(xpath = "//*[@id=\"start-date\"]")
    private WebElement startDate;
    @FindBy(xpath = "//*[@id=\"end-date\"]")
    private WebElement endDate;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr/td[1]")
    private List<WebElement> results;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[4]/div/div/div[2]/div[2]/ngx-slider/span[5]")
    private WebElement minValueInFilter;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[1]/div/div/div[4]/div/div/div[2]/div[2]/ngx-slider/span[6]")
    private WebElement maxValueInFilter;
    @FindBy(tagName = "thead")
    private WebElement table;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[5]/a")
    private WebElement transactionsTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-wallet-transaction/div[1]/div/div/div/div[1]/div/h4")
    private WebElement titleTransaction;
    private List<WebElement> getDisplayedOrdersCount() {
        // Locate the displayed orders table and count the rows or elements
        return driver.findElements(By.xpath("/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-orders/section[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr/td[1]"));
    }
    public Orders(WebDriver driver) {
        super(driver);
    }
    //The Methods
    DB db=new DB();
    /**
     * Checks the total number of completed orders in the Orders page and compares it with the displayed count.
     *
     */
    public void checkTotalTotalCompletedOrdersInOrdersPage() throws Exception {
        // Wait for a short duration, then compare the total completed orders from the database with the displayed count
        Thread.sleep(1000);
        Assert.assertEquals(db.fetchMerchantOrdersCompletedFromDatabase().size(), Integer.parseInt(totalCompletedOrders.getText()));
    }

    /**
     * Checks the total amount of completed orders in the Orders page and compares it with the displayed amount.
     *
     */
    public void checkTotalTotalCompletedOrdersAmountInOrdersPage() throws Exception {
        // Wait for a short duration, then compare the total completed orders amount from the database with the displayed amount
        Thread.sleep(1000);
        System.out.println(db.fetchMerchantOrdersCompletedAmountFromDatabase() + "*///**");
        Assert.assertEquals(db.fetchMerchantOrdersCompletedAmountFromDatabase(), Integer.parseInt(completedOrdersAmount.getText().substring(completedOrdersAmount.getText().indexOf("R ") + 2)));
    }

    /**
     * Checks the total number of incompleted orders in the Orders page and compares it with the displayed count.
     *
     */
    public void checkTotalTotalInCompletedOrdersInOrdersPage() throws Exception {
        // Wait for a short duration, then compare the total incompleted orders from the database with the displayed count
        Thread.sleep(1000);
        Assert.assertEquals(db.fetchMerchantOrdersInCompletedFromDatabase().size(), Integer.parseInt(totalInCompletedOrders.getText()));
    }

    /**
     * Checks the total amount of incompleted orders in the Orders page and compares it with the displayed amount.
     *
     */
    public void checkTotalTotalInCompletedOrdersAmountInOrdersPage() throws Exception {
        // Wait for a short duration, then compare the total incompleted orders amount from the database with the displayed amount
        Thread.sleep(1000);
        System.out.println(db.fetchMerchantOrdersInCompletedAmountFromDatabase() + "*///**");
        Assert.assertEquals(db.fetchMerchantOrdersInCompletedAmountFromDatabase(), Integer.parseInt(inCompletedOrdersAmount.getText().substring(inCompletedOrdersAmount.getText().indexOf("R ") + 2)));
    }

    /**
     * Checks the number of displayed orders in the Orders page and compares it with the initial selection.
     *
     */
    public void checkOrdersNumberInOrdersPageAsSelected() throws InterruptedException {
        // Wait for a short duration, then select the number of displayed orders and compare it with the initial selection
        Thread.sleep(1000);
        Select select = new Select(numberOfDisplayedOrdersInTable);
        int initialSelection = 10;
        System.out.println(initialSelection + "==" + getDisplayedOrdersCount().size());
        Assert.assertEquals(getDisplayedOrdersCount().size(), initialSelection);
    }

    /**
     * Checks if the 'Completed Orders' tab is clickable in the Orders page.
     *
     */
    public void checkCompletedOrdersTabInOrdersPage() throws Exception {
        // Wait for a short duration, then click on the 'Completed Orders' tab and wait for it to be clickable
        Thread.sleep(1000);
        completedOrdersTab.click();
        waitElementToClickable(driver, completedOrdersTab);
    }

    /**
     * Checks if the 'Incomplete Orders' tab is clickable in the Orders page.
     *
     */
    public void checkInCompletedOrdersTabInOrdersPage() throws Exception {
        // Wait for a short duration, then click on the 'Incomplete Orders' tab and wait for it to be clickable
        Thread.sleep(1000);
        inCompletedOrdersTab.click();
        waitElementToClickable(driver, inCompletedOrdersTab);
    }

    /**
     * Checks the title of columns in the Orders page and compares it with the expected list of titles.
     *
     */
    public void checkTitleOfColumnInOrders() throws Exception {
        // Create a list of expected column titles
        List<String> actualTitle = Arrays.asList("Order ID", "Branch", "Brand info", "Ordered On", "Amount", "Tax", "Discount", "Offers", "Commission", "Total");
        List<WebElement> titles = table.findElements(new By.ByTagName("th"));
        Thread.sleep(1000);
        List<String> extractedTitles = new ArrayList<>();
        for (WebElement title : titles) {
            extractedTitles.add(title.getText());
        }

        // Compare the two lists and assert that they are equal
        boolean areListsEqual = actualTitle.equals(extractedTitles);
        Assert.assertTrue(areListsEqual, "Lists are not equal");
    }

    /**
     * Checks searching for orders in the Orders page based on the provided order and compares the results.
     *
     */
    public void checkSearchAllOrders(String order) throws Exception {
        // Wait for a short duration, perform a search for the order, collect the results, and compare them
        Thread.sleep(1000);
        boolean isAllResultsAsSearched = false;
        List<String> searchResult = new ArrayList<>();
        setTextElementText(searchFldInAllOrders, order);
        Thread.sleep(1000);
        for (WebElement element : resultsOfAllOrders) {
            searchResult.add(element.getText());
        }

        // Check if all results contain the searched order and assert the results
        for (String element : searchResult) {
            isAllResultsAsSearched = element.contains(order);
            if (!isAllResultsAsSearched) break;
        }
        if (searchResult.size() != 0) {
            Assert.assertEquals(db.fetchMerchantOrdersAndSearchInItFromDatabase(order), searchResult);
            Assert.assertTrue(isAllResultsAsSearched);
        }
        Thread.sleep(1000);
    }

    /**
     * Checks filtering orders based on date range in the Orders page and compares the results.
     *
     */
    public void checkFilterInOrders() throws Exception {
        // Click the filter button, set date range, apply the filter, and compare the filtered results
        filterButton.click();
        waitElementToClickable(driver, filterButton);
        setTextElementText(startDate, "09/09/2023");
        setTextElementText(endDate, "15/09/2023");
        applyButton.click();
        waitElementToClickable(driver, applyButton);
        System.out.println(db.fetchMerchantOrdersAndFilterItFromDatabase("2023-09-09", "2023-09-15"));
        Assert.assertEquals(results.size(), db.fetchMerchantOrdersAndFilterItFromDatabase("2023-09-09", "2023-09-15").size());

        // Click the filter button again and reset the filter
        System.out.println("click filter again");
        filterButton.click();
        waitElementToClickable(driver, filterButton);
        restButton.click();
        waitElementToClickable(driver, restButton);
        Thread.sleep(1000);
    }

    /**
     * Clicks on the 'Transactions' tab in the dashboard and navigates to the Transactions page.
     *
     */
    public Transactions clickTransactions() throws Exception {
        // Wait for a short duration, go back to the dashboard, click on 'Transactions' tab, and wait for it to be clickable
        Thread.sleep(1000);
        backToDashboard();
        Thread.sleep(1000);
        transactionsTab.click();
        waitElementToClickable(driver, titleTransaction);
        return new Transactions(driver);
    }

}
