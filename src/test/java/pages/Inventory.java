package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Inventory extends BasePage {
    //The Elements
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[2]/div[2]/div[1]/div/a")
    private WebElement settings;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[1]/app-header/div/div[1]/div/div/div/div[2]/div[2]/div[1]/div/div/a[1]")
    private WebElement myProfile;



    public Inventory(WebDriver driver) {
        super(driver);
    }
    public MyProfile clickMyProfile() throws Exception {
        // Wait for 1 second for stability
        Thread.sleep(1000);

        // Navigate back to the dashboard (assuming this method is defined)
        backToDashboard();

        // Click on the settings (assuming settings is initialized)
        settings.click();

        // Wait for the settings element to be clickable
        waitElementToClickable(driver, settings);

        // Click on the My Profile (assuming myProfile is initialized)
        myProfile.click();

        // Return a new instance of MyProfile page
        return new MyProfile(driver);
    }

}
