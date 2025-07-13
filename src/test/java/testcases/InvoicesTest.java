package testcases;

import base.BaseTest;
import pages.Dashboard;
import pages.Login;
import org.testng.annotations.Test;
import pages.Orders;
import utils.ConfigUtils;

//@Feature("Test Case")
public class InvoicesTest extends BaseTest {
    public static Orders orders;
    @Test(priority = 1)
// This test verifies the correctness of titles in the Invoices table
    public void isValuesOfTotalOrdersInFrontEndAsDB() throws Exception {
        try {
            DashBoaredTest.invoices.checkTitleOfColumnInInvoices();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 2)
// This test verifies the functionality of the filter in the Invoices page
    public void checkIfFilterInInvoicesWorks() throws Exception {
        try {
            DashBoaredTest.invoices.checkFilterInInvoices();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 3)
// This test verifies the correctness of the search functionality in the Invoices page
    public void isValuesOfSearchCorrect() throws Exception {
        try {
            DashBoaredTest.invoices.checkSearch(ConfigUtils.getInstance().getInvoiceID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 6)
// This test verifies the clickability of the Orders tab from the Invoices page
    public void isOrdersTabClickable() throws Exception {
        orders = DashBoaredTest.invoices.clickOrders();
    }




}
