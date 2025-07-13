package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfile extends BasePage {
    @FindBy(css="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[1]/div/div/div[4]/div/ul/li[1]/a")
    private WebElement communication;
    @FindBy(css="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[1]/div/div/div[4]/div/ul/li[2]/a")
    private WebElement bank;
    @FindBy(css="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[1]/div/div/div[4]/div/ul/li[3]/a")
    private WebElement address;
    @FindBy(css="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[1]/div/div/div[4]/div/ul/li[4]/a")
    private WebElement details;
    @FindBy(css="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[1]/div/div/div[1]/div/div[2]/div/div[2]/button")
    private WebElement changePassword;
    @FindBy(css="//*[@id=\"oldPassword\"]")
    private WebElement oldPassword;
    @FindBy(css="//*[@id=\"newPassword\"]")
    private WebElement newPassword;
    @FindBy(css="//*[@id=\"confirmPassword\"]")
    private WebElement confirmPassword;
    @FindBy(css="/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[2]/div/div/div[3]/button[2]")
    private WebElement saveButton;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[2]/div[2]/div[1]/div/a")
    private WebElement settings;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[2]/div[2]/div[1]/div/div/a[3]")
    private WebElement logout;
    @FindBy(xpath = "    /html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-merchant-profile/div/div[1]/div/div/div[3]/div/div[2]/a/button")
    private WebElement manageBrand;






    public MyProfile(WebDriver driver){super(driver);}
    public void checkIfCommunicationClickable() throws Exception {
        // Wait for a while and then click on the communication element
        Thread.sleep(1000);
        communication.click();
        waitElementToClickable(driver, communication);
    }

    public void checkIfBankClickable() throws Exception {
        // Wait for a while and then click on the bank element
        Thread.sleep(1000);
        bank.click();
        waitElementToClickable(driver, bank);
    }

    public void checkIfAddressClickable() throws Exception {
        // Wait for a while and then click on the address element
        Thread.sleep(1000);
        address.click();
        waitElementToClickable(driver, address);
    }

    public void checkIfDetailsClickable() throws Exception {
        // Wait for a while and then click on the details element
        Thread.sleep(1000);
        details.click();
        waitElementToClickable(driver, details);
    }

    public void checkIfChangePasswordWorking(String oldPassword, String newPassword, String confirmPassword) throws Exception {
        // Wait for a while and then click on the change password element
        Thread.sleep(1000);
        changePassword.click();
        waitElementToClickable(driver, changePassword);

        // Set the old password, new password, and confirm password
        setTextElementText(this.oldPassword, oldPassword);
        setTextElementText(this.newPassword, newPassword);
        setTextElementText(this.confirmPassword, confirmPassword);

        // Click the save button and wait for it to be clickable
        saveButton.click();
        waitElementToClickable(driver, saveButton);
    }

    public void clickManageBrands() throws Exception {
        // Wait for a while and then navigate to the Manage Brands section
        Thread.sleep(1000);
        backToDashboard();
        Thread.sleep(1000);
        manageBrand.click();
        waitElementToClickable(driver, manageBrand);
    }

    public Signup clickLogout() throws Exception {
        // Wait for a while and then navigate to the logout section
        Thread.sleep(1000);
        settings.click();
        waitElementToClickable(driver, settings);
        logout.click();
        waitElementToClickable(driver, logout);

        // Return a new Signup instance
        return new Signup(driver);
    }


}
