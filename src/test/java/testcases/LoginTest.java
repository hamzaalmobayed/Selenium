package testcases;

import base.BaseTest;
import pages.Dashboard;
import pages.Login;
import org.testng.annotations.Test;
import utils.ConfigUtils;

//@Feature("Test Case")
public class LoginTest extends BaseTest {
    static Dashboard dashboard;
    @Test(priority = 0)
// This test opens the website and performs a login
    public void openAndLogin() throws Exception {
        // Create a new instance of the Login page
        Login homePage = new Login(getDriver());

        // Open the website and login
        dashboard = homePage.loadWebSite().login(
                ConfigUtils.getInstance().getMerchantEmail(),
                ConfigUtils.getInstance().getMerchantPassword(),
                "1001");
    }


}
