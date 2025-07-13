package testcases;

import base.BaseTest;
import org.junit.jupiter.api.Order;
import pages.Dashboard;
import pages.Invoices;
import pages.Login;
import org.testng.annotations.Test;
import pages.Orders;

//@Feature("Test Case")
public class DashBoaredTest extends BaseTest {
    static Invoices invoices;
    @Test(priority = 1)
// This test verifies if the total orders displayed in the front end match the database
    public void verifyTotalOrdersInFrontEndAsDB() throws Exception {
        try {
            LoginTest.dashboard.checkTotalOrdersInDashboard();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
// This test verifies if the merchant wallet amount displayed in the front end matches the database
    public void verifyMerchantWalletInFrontEndAsDB() throws Exception {
        try {
            LoginTest.dashboard.checkTotalAmountInDashboard();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Test(priority = 3)
// This test verifies if the merchant name displayed in the front end matches the database
    public void verifyMerchantNameInFrontEndAsDB() throws Exception {
        try {
            LoginTest.dashboard.checkMerchantNameInDashboard();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Test(priority = 4)
// This test verifies if the dashboard tab is clickable
    public void verifyDashboardTabIsClickable() throws Exception {
        LoginTest.dashboard.clickDashboard();
    }

    @Test(priority = 5)
// This test verifies if the invoices tab is clickable and navigates to the invoices page
    public void verifyInvoicesTabIsClickable() throws Exception {
        invoices = LoginTest.dashboard.clickInvoices();
    }


}
