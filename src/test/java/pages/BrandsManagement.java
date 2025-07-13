package pages;

import DBConnection.DB;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrandsManagement extends BasePage {
    DB db=new DB();
    //The Elements
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brands-management/div[2]/div/div/span[1]")
    private List<WebElement> brandName;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brands-management/div[2]/div/div/div[2]/button")
    private WebElement brandBtn;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brand-details/div/app-branches/div/div/div/span[1]")
    private List<WebElement> branchesNames;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-branch-details/div/div[2]/div/div/div[1]/div[1]/p[2]")
    private WebElement branchName;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brand-details/div/app-branches/div/div/div/div[2]/button")
    private WebElement branchBtn;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brand-details/div/div/div/div[2]/div[1]/span")
    private WebElement clickableBrandNameEn;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brand-details/div/div/div/div[2]/div[2]/span[2]")
    private WebElement clickableBrandNameAr;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brand-details/div/div/div/div[2]/div[3]/span[2]")
    private WebElement clickableBrandSubCategory;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-brand-details/div/div/div/div[2]/div[4]/span[2]")
    private WebElement clickableBrandSecondaryCr;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[1]/app-sidebar/div/div/ul[1]/li[3]/a")
    private WebElement inventoryTab;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-inventory/section[1]/div[1]/div/h4")
    private WebElement titleInventory;

    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-branch-details/div/div[2]/div/div/div[1]/div[2]/p[2]")
    private WebElement branchContactNumber;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-branch-details/div/div[2]/div/div/div[2]/div[1]/p[2]")
    private WebElement branchOpeningTime;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-branch-details/div/div[2]/div/div/div[2]/div[2]/p[2]")
    private WebElement branchClosingTime;
    @FindBy(xpath = "/html/body/app-root/app-main-layout/div/div[2]/div/div[2]/div/div/app-branch-details/div/div[2]/div/div/div[3]/div/p[2]")
    private WebElement branchAmenities;




    public BrandsManagement(WebDriver driver) {
        super(driver);
    }
    static List<String> brandNames = new ArrayList<>();
    List<String> branchNames = new ArrayList<>();

    // Helper methods to get brand and branch names from elements
    private List<String> getBrandNamesFromElements() {
        List<String> brandNames = new ArrayList<>();
        for (WebElement element : brandName) {
            brandNames.add(element.getText());
        }
        return brandNames;
    }

    private List<String> getBranchNamesFromElements() {
        List<String> branchNames = new ArrayList<>();
        for (WebElement element : branchesNames) {
            branchNames.add(element.getText());
        }
        return branchNames;
    }

    // Check if brands are in front as per the database
    public void checkIfBrandsInFrontAsDB() throws Exception {
        List<String> brandNames = getBrandNamesFromElements();
        boolean isPartOfDBList = db.fetchMerchantBrandsFromDatabase().containsAll(brandNames);
        Assert.assertTrue(isPartOfDBList);
    }

    // Check if brands are clickable in the frontend as per the database
    public void checkIfBrandsClickableInFrontAsDB() {
        brandBtn.click();
        waitElementToClickable(driver, brandBtn);
    }

    // Check if brand details match the database
    public void checkIfBrandsDetailsInFrontAsDB() throws Exception {
        List<String> brandNames = getBrandNamesFromElements();
        if (!brandNames.isEmpty()) {
            String firstBrandName = brandNames.get(0);
            Map<String, Object> selectedBrandDetails = db.fetchMerchantBrandDataFromDatabase(
                    db.fetchMerchantBrandIdFromDatabase(firstBrandName).get(0)).get(0);

            Assert.assertEquals(selectedBrandDetails.get("NameEn"), clickableBrandNameEn.getText());
            Assert.assertEquals(selectedBrandDetails.get("NameAr"), clickableBrandNameAr.getText());
            Assert.assertEquals(selectedBrandDetails.get("SecondaryCR"), clickableBrandSecondaryCr.getText());
            Assert.assertEquals(selectedBrandDetails.get("SubCategory"), clickableBrandSubCategory.getText());
        }
    }

    // Check if branches of a brand match the database
    public void checkIfBranchesOfBrandInFrontAsDB() throws Exception {
        List<String> branchNames = getBranchNamesFromElements();
        if (!branchNames.isEmpty()) {
            String firstBranchName = branchNames.get(0);
            Assert.assertEquals(db.fetchMerchantBranchesForBrandFromDatabase(
                    db.fetchMerchantBrandIdFromDatabase(firstBranchName).get(0)), branchNames);
        }
    }

    // Check if branches are clickable in the frontend as per the database
    public void checkIfBranchesClickableInFrontAsDB() throws Exception {
        branchBtn.click();
        waitElementToClickable(driver, branchBtn);
    }

    // Check branch details match the database
    public void checkBranchDetailsAsDB() throws Exception {
        List<String> branchNames = getBranchNamesFromElements();
        if (!branchNames.isEmpty()) {
            String firstBranchName = branchNames.get(0);
            Map<String, Object> selectedBranchDetails = db.fetchMerchantBranchDataFromDatabase(
                    db.fetchMerchantBranchIdFromDatabase(firstBranchName).get(0)).get(0);

            Assert.assertEquals(branchName.getText(), selectedBranchDetails.get("Name"));
            Assert.assertEquals(branchAmenities.getText(), selectedBranchDetails.get("Amenities"));
            Assert.assertEquals(branchClosingTime.getText(), selectedBranchDetails.get("ClosingTiming"));
            Assert.assertEquals(branchOpeningTime.getText(), selectedBranchDetails.get("OpeningTiming"));
            Assert.assertEquals(branchContactNumber.getText(), selectedBranchDetails.get("ContactNumber"));
        }
    }

    // Click on the inventory
    public Inventory clickInventory() {
        backToDashboard();
        inventoryTab.click();
        waitElementToClickable(driver, inventoryTab);
        return new Inventory(driver);
    }



}
