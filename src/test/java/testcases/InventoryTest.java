package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.Inventory;
import pages.MyProfile;

public class InventoryTest extends BaseTest {
    static MyProfile myProfile;
    @Test(priority = 1)
// This test verifies if the My Profile link is clickable from the Inventory page
    public void isMyProfileClickable() throws Exception {
        // Click on My Profile link from the Inventory page
        myProfile = BrandManagementTest.inventory.clickMyProfile();
    }

}
