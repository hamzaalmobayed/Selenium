package base;


import hamza.example.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigUtils;

import java.time.Duration;

public class BasePage {
    protected static WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  static void presenceOfElement(WebDriver driver , int time , By element) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void visibilityOfElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(BasePage.driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementToClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected static void setTextElementText (WebElement TextElement, String value) {
        TextElement.sendKeys(value);
    }
    public void backToDashboard(){
        driver.navigate().back();
    }
}

