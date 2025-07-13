package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import utils.ConfigUtils;

public class MyProfileTest extends BaseTest {
    @Test(priority = 1)
    public void isCommunicationClickable() throws Exception {
        try {
            // Check if communication is clickable
            InventoryTest.myProfile.checkIfCommunicationClickable();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void isBankClickable() throws Exception {
        try {
            // Check if bank is clickable
            InventoryTest.myProfile.checkIfBankClickable();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void isAddressClickable() throws Exception {
        try {
            // Check if address is clickable
            InventoryTest.myProfile.checkIfAddressClickable();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void isDetailsClickable() throws Exception {
        try {
            // Check if details is clickable
            InventoryTest.myProfile.checkIfDetailsClickable();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void isChangePasswordWorking() throws Exception {
        try {
            // Check if change password is working
            InventoryTest.myProfile.checkIfChangePasswordWorking(
                    ConfigUtils.getInstance().getMerchantPassword(),
                    ConfigUtils.getInstance().getNewPassword(),
                    ConfigUtils.getInstance().getConfirmPassword());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void isManageBrandWorking() throws Exception {
        try {
            // Check if manage brand is working
            InventoryTest.myProfile.clickManageBrands();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void isLogoutWorking() throws Exception {
        try {
            // Check if logout is working
            InventoryTest.myProfile.clickLogout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
