package com.somee.pages;

import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateUtils validateUtils;

    private By maDanhMucInput = By.id("ContentPlaceHolder1_txtID");
    private By tenDanhMucInput = By.id("ContentPlaceHolder1_txtTenDM");
    private By themMoiButton = By.id("ContentPlaceHolder1_LinkButton1");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        validateUtils = new ValidateUtils(driver);
    }

    public void addCategory(String maDanhMuc, String tenDanhMuc) {
        validateUtils.waitForPageLoaded();
        validateUtils.setText(maDanhMucInput, maDanhMuc);
        validateUtils.setText(tenDanhMucInput, tenDanhMuc);
        validateUtils.clickElementWithJS(themMoiButton);
    }

    public void deleteCategoryByName(String categoryName) {
        validateUtils.waitForPageLoaded();
        By deleteButtonLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td[text()='" + categoryName + "']]/td/input[@type='button' and @value='Xóa']"
        );
        validateUtils.clickElementWithJS(deleteButtonLocator);
    }

    public void editCategoryInline(String oldCategoryName, String newCategoryName) {
        validateUtils.waitForPageLoaded();
        By editButtonLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td[text()='" + oldCategoryName + "']]/td/input[@type='button' and @value='Sửa']"
        );
        validateUtils.clickElement(editButtonLocator);
        By inlineCategoryName = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td/input[@value='" + oldCategoryName + "']]/td[2]/input[@type='text']"
        );
        By saveButtonLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td/input[@value='" + oldCategoryName + "']]/td/input[@type='submit' and @value='Lưu']"
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(inlineCategoryName));
        validateUtils.setValueWithJS(inlineCategoryName, newCategoryName);
        validateUtils.clickElementWithJS(saveButtonLocator);
    }

    public void cancelEditCategoryInline(String oldCategoryName, String newCategoryName) {
        validateUtils.waitForPageLoaded();
        By editButtonLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td[text()='" + oldCategoryName + "']]/td/input[@type='button' and @value='Sửa']"
        );
        validateUtils.clickElement(editButtonLocator);
        By inlineCategoryName = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td/input[@value='" + oldCategoryName + "']]/td[2]/input[@type='text']"
        );
        By cancelButtonLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td/input[@value='" + oldCategoryName + "']]/td/input[@type='button' and @value='Hủy']"
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(inlineCategoryName));
        validateUtils.setValueWithJS(inlineCategoryName, newCategoryName);
        validateUtils.clickElementWithJS(cancelButtonLocator);
    }
}
