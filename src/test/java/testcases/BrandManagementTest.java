package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.Inventory;

public class BrandManagementTest extends BaseTest {
    static Inventory inventory;

    // Test to check if brands of the merchant are displayed in the front end as in the database
    @Test(priority = 1)
    public void isBrandsOfMerchantInFrontEndAsDB() {
        try {
            TransactionTest.brandsManagement.checkIfBrandsInFrontAsDB();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Test to check if brands of the merchant are clickable in the front end as in the database
    @Test(priority = 2)
    public void isBrandsOfMerchantClickableInFrontEndAsDB() {
        try {
            TransactionTest.brandsManagement.checkIfBrandsClickableInFrontAsDB();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Test to check if branches of the merchant are displayed in the front end as in the database
    @Test(priority = 3)
    public void isBranchesOfMerchantInFrontEndAsDB() {
        try {
            TransactionTest.brandsManagement.checkIfBranchesOfBrandInFrontAsDB();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Test to check if brand details are displayed in the front end as in the database
    @Test(priority = 4)
    public void isBrandsDetailsInFrontEndAsDB() {
        try {
            TransactionTest.brandsManagement.checkIfBrandsDetailsInFrontAsDB();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Test to check if branches of the merchant are clickable in the front end as in the database
    @Test(priority = 5)
    public void isBranchesOfMerchantClickableInFrontEndAsDB() {
        try {
            TransactionTest.brandsManagement.checkIfBranchesClickableInFrontAsDB();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Test to check if branch details are displayed in the front end as in the database
    @Test(priority = 6)
    public void isBranchDetailsInFrontEndAsDB() {
        try {
            TransactionTest.brandsManagement.checkBranchDetailsAsDB();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Test to check if inventory is clickable in the front end
    @Test(priority = 7)
    public void isInventoryClickableInFront() {
        try {
            inventory = TransactionTest.brandsManagement.clickInventory();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
