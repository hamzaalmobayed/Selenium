package pages;

import DBConnection.DB;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Dashboard extends BasePage {
    //The Elements
    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div[2]/ul/li/button")
    private WebElement logout;
    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div[2]/ul/li/a")
    private WebElement setting;
    @FindBy(xpath = "    /html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[2]/div[2]/div[1]/div/a/span")
    private WebElement titleDashboard;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-invoice/section[1]/div[1]/div/h4")
    private WebElement titleInvoices;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-inventory/section[1]/div[1]/div/h4")
    private WebElement titleInventory;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-wallet-transaction/div[1]/div/div/div/div[1]/div/h4")
    private WebElement titleTransaction;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brands-management/div[2]/div/div/div[2]/button")
    private WebElement titleBrandsManagement;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-dashboard/div[2]/div[1]/div/div/div[2]/div[1]/div/h4")
    private WebElement orderNumber;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[2]/div[1]/div/div/span")
    private WebElement merchantAmount;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[1]/a/span")
    private WebElement merchantName;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[1]/a")
    private WebElement dashboardTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[2]/a")
    private WebElement invoicesTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[3]/a")
    private WebElement inventoryTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[5]/a")
    private WebElement transactionsTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[6]/a")
    private WebElement brandManagementTab;


    public Dashboard(WebDriver driver) {
        super(driver);
    }
    DB db=new DB();

    // Method to check the total orders in the dashboard
    public void checkTotalOrdersInDashboard() throws Exception {
        // Wait for 1 second before proceeding (consider using explicit waits)
        Thread.sleep(1000);

        // Assert the total orders in the dashboard with the database
        Assert.assertEquals(db.fetchMerchantOrdersFromDatabase().size(), Integer.parseInt(orderNumber.getText()));
    }

    // Method to check the total amount in the dashboard
    public void checkTotalAmountInDashboard() throws Exception {
        Thread.sleep(1000);
        // Assert the total amount in the dashboard with the database
        Assert.assertEquals(db.fetchMerchantWalletFromDatabase().get(0).get("WalletAmount"), Integer.parseInt(merchantAmount.getText().substring(merchantAmount.getText().indexOf("R ") + 2)));
    }

    // Method to check the merchant name in the dashboard
    public void checkMerchantNameInDashboard() throws Exception {
        Thread.sleep(1000);
        // Assert the merchant name in the dashboard with the database
        Assert.assertEquals(db.fetchMerchantDetailsFromDatabase().get(0).get("FirstName").toString().toUpperCase(), merchantName.getText().toUpperCase());
    }

    // Method to click on the dashboard tab and navigate to the dashboard page
    public Dashboard clickDashboard() throws Exception {
        Thread.sleep(1000);
        // Click on the dashboard tab
        dashboardTab.click();
        waitElementToClickable(driver, titleDashboard);
        return new Dashboard(driver);
    }

    // Method to click on the invoices tab and navigate to the invoices page
    public Invoices clickInvoices() throws Exception {
        Thread.sleep(1000);
        // Click on the invoices tab
        invoicesTab.click();
        waitElementToClickable(driver, titleInvoices);
        return new Invoices(driver);
    }

    // Method to click on the inventory tab and navigate to the inventory page
    public Inventory clickInventory() throws Exception {
        Thread.sleep(1000);
        // Click on the inventory tab
        inventoryTab.click();
        waitElementToClickable(driver, titleInventory);
        return new Inventory(driver);
    }

    // Method to click on the transactions tab and navigate to the transactions page
    public Transactions clickTransactions() throws Exception {
        Thread.sleep(1000);
        // Click on the transactions tab
        transactionsTab.click();
        waitElementToClickable(driver, titleTransaction);
        return new Transactions(driver);
    }

    // Method to click on the brands management tab and navigate to the brands management page
    public BrandsManagement clickBrandsManagement() throws Exception {
        Thread.sleep(1000);
        // Click on the brands management tab
        brandManagementTab.click();
        waitElementToClickable(driver, titleBrandsManagement);
        return new BrandsManagement(driver);
    }


}
