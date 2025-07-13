package pages;

import base.BasePage;
import org.testng.annotations.Test;
import utils.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage {
    //The Elements
    @FindBy(css = "input[formcontrolname='email']")
    private WebElement emailFld;
    @FindBy(css = "input[formcontrolname=\"password\"]")
    private WebElement passFld;
    @FindBy(css="input[minlength=\"4\"]")
    private WebElement otpFld;
    @FindBy( xpath = "/html/body/app-root/app-login/div/div/div[1]/div[3]/form/div[3]/button")
    private WebElement loginBtn;
    @FindBy( xpath = "/html/body/app-root/app-login/div/div/div[1]/div[2]/form/div[2]/button")
    private WebElement submitBtn;

    public Login(WebDriver driver) {
        //Constructor
        super(driver);
    }

    //The Methods
    public Login loadWebSite() {
        // Load the website using the configured base URL
        driver.get(ConfigUtils.getInstance().getBaseUrl());
        // Return a new instance of the Login page
        return new Login(driver);
    }

    public Dashboard login(String email, String password, String otpvalue) throws Exception {
        // Set the email and wait for a while
        setTextElementText(emailFld, email);
        Thread.sleep(1000);

        // Set the password and wait for a while
        setTextElementText(passFld, password);
        Thread.sleep(1000);

        // Click the login button and wait for OTP field to be clickable
        loginBtn.click();
        waitElementToClickable(driver, otpFld);

        // Set the OTP and click submit, then return the Dashboard instance
        setTextElementText(otpFld, otpvalue);
        submitBtn.click();
        return new Dashboard(driver);
    }








}
