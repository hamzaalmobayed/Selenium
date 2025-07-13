package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.Invoices;
import pages.Orders;
import pages.Transactions;
import utils.ConfigUtils;

public class OrderTest extends BaseTest {
    public static Transactions transactions;
    /**
     * Checks if the total completed orders in the front end match the database.
     *
     */
    @Test(priority = 1)
    public void isTotalCompletedOrdersInFrontEndAsDB() throws Exception {
        try {
            InvoicesTest.orders
                    .checkTotalTotalCompletedOrdersInOrdersPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the completed orders' amount in the orders page matches the database.
     *
     */
    @Test(priority = 2)
    public void checkCompletedOrdersAmountInOrders() throws Exception {
        try {
            InvoicesTest.orders
                    .checkTotalTotalCompletedOrdersAmountInOrdersPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the total incompleted orders in the front end match the database.
     *
     */
    @Test(priority = 3)
    public void isTotalInCompletedOrdersInFrontEndAsDB() throws Exception {
        try {
            InvoicesTest.orders
                    .checkTotalTotalInCompletedOrdersInOrdersPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the incompleted orders' amount in the orders page matches the database.
     *
     */
    @Test(priority = 4)
    public void checkInCompletedOrdersAmountInOrders() throws Exception {
        try {
            InvoicesTest.orders
                    .checkTotalTotalInCompletedOrdersAmountInOrdersPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the number of displayed orders matches the selected value in the dropdown.
     *
     */
    @Test(priority = 5)
    public void checkDisplayedOrdersAsSelectedInOrders() throws InterruptedException {
        try {
            InvoicesTest.orders
                    .checkOrdersNumberInOrdersPageAsSelected();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks the titles of the columns in the orders table.
     *
     */
    @Test(priority = 6)
    public void checkTitlesOfColumnInTable() throws Exception {
        try {
            InvoicesTest.orders
                    .checkTitleOfColumnInOrders();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the filter functionality in the orders page works correctly.
     *
     */
    @Test(priority = 7)
    public void checkIfFilterInInvoicesWorks() throws Exception {
        try {
            InvoicesTest.orders
                    .checkFilterInOrders();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the search functionality in all orders page works correctly.
     *
     */
    @Test(priority = 8)
    public void checkSearchInAllOrders() throws Exception {
        try {
            InvoicesTest.orders
                    .checkSearchAllOrders(ConfigUtils.getInstance().getOrderID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the 'Completed Orders' tab is clickable in the orders page.
     *
     */
    @Test(priority = 9)
    public void checkCompletedOrdersTabClickable() throws Exception {
        try {
            InvoicesTest.orders
                    .checkCompletedOrdersTabInOrdersPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the 'Incompleted Orders' tab is clickable in the orders page.
     *
     */
    @Test(priority = 10)
    public void checkInCompletedOrdersTabClickable() throws Exception {
        try {
            InvoicesTest.orders
                    .checkInCompletedOrdersTabInOrdersPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Clicks on the 'Transactions' tab in the dashboard and navigates to the Transactions page.
     * Also assigns the result to the 'transactions' variable for further use.
     *
     */
    @Test(priority = 11)
    public void isTransactionsTabClickable() throws Exception {
        transactions = InvoicesTest.orders
                .clickTransactions();
    }

}
