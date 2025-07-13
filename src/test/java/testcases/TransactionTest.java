package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.BrandsManagement;
import pages.Inventory;
import pages.Orders;
import utils.ConfigUtils;

public class TransactionTest extends BaseTest {
    public static BrandsManagement brandsManagement;
    /**
     * Checks the title of columns in the Transactions table.
     *
     */
    @Test(priority = 1)
    public void isValuesOfTotalTransactionsInFrontEndAsDB() throws Exception {
        try {
            OrderTest.transactions
                    .checkTitleOfColumnInTransactions();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the search functionality in the Transactions page works correctly.
     *
     */
    @Test(priority = 2)
    public void isValuesOfSearchCorrect() throws Exception {
        try {
            OrderTest.transactions
                    .checkSearch(ConfigUtils.getInstance().getTransactionID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the Brands Management tab in the Transactions page is clickable.
     *
     */
    @Test(priority = 3)
    public void isBrandsManagementTabClickable() throws Exception {
        brandsManagement = OrderTest.transactions
                .clickBrandsManagement();
    }

}
