package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.pages.CategoryPage;
import com.somee.pages.LoginPage;
import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CategoryTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LoginPage loginPage;
    private CategoryPage categoryPage;

    private By getLinkDangNhap = By.id("LinkDN");
    private By getDanhMuc = By.id("HyperLink5");
    private By getBangDanhMuc = By.id("ContentPlaceHolder1_GridView1");
    private By getThongBaoLoi = By.id("ContentPlaceHolder1_lblThongBao");

    private String tenDanhMuc;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        categoryPage = new CategoryPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
    }

    // Test case 1: Thêm danh mục thành công
    @Test(priority = 1)
    public void addCategorySuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("100","Kính Cận Loại 2");
        System.out.println(validateUtils.verifyCategoryAddedToTable(getBangDanhMuc, "Kính Cận Loại 2"));
        assertTrue(validateUtils.verifyCategoryAddedToTable(getBangDanhMuc, "Kính Cận Loại 2"), "Thêm danh mục thành công");
        Thread.sleep(2000);
    }

    // Test case 2: Thêm danh mục với mã danh mục bị trống
    @Test(priority = 2)
    public void addCategoryWithEmptyID() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("","Kính Cận Loại 3");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Nhập mã danh mục!"), "Thêm danh mục không thành công: Vui lòng nhập mã danh mục!");
        Thread.sleep(2000);
    }

    // Test case 3: Thêm danh mục với tên danh mục bị trống
    @Test(priority = 3)
    public void addCategoryWithEmptyName() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("1001","");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Nhập tên danh mục!"), "Thêm danh mục không thành công: Vui lòng nhập tên danh mục!");
        Thread.sleep(2000);
    }

    // Test case 4: Thêm danh mục với mã danh mục đã tồn tại
    @Test(priority = 4)
    public void addCategoryWithExistID() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("2","Kính Cận Loại 3");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Mã danh mục đã được sử dụng!"), "Thêm danh mục không thành công: Mã danh mục đã được sử dụng!");
        Thread.sleep(2000);
    }

    // Test case 5: Thêm danh mục với tên danh mục đã tồn tại
    @Test(priority = 5)
    public void addCategoryWithExistName() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("1001","Kính Bơi Chống Nắng");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Tên danh mục đã được sử dụng!"), "Thêm danh mục không thành công: Tên danh mục đã được sử dụng!");
        Thread.sleep(2000);
    }


    // Test case 6: Xóa danh mục thành công
    @Test(priority = 6)
    public void deleteCategorySuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        tenDanhMuc = "Kính Cận Loại 2";
        categoryPage.deleteCategoryByName(tenDanhMuc);
        Thread.sleep(2000);
        validateUtils.waitForPageLoaded();
        assertTrue(validateUtils.verifyCategoryDoesNotExistInTable(tenDanhMuc), "Đã xóa danh mục thành công!");
        Thread.sleep(2000);
    }

    // Test case 7: Sửa danh mục
    @Test(priority = 7)
    public void testEditCategorySuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        String tenDanhMucCu = "Kính Cận Loại 2", tenDanhMucMoi = "Kính Cận Loại 1";
        categoryPage.editCategoryInline(tenDanhMucCu, tenDanhMucMoi);
        assertTrue(validateUtils.verifyCategoryExistsInTable(tenDanhMucMoi), "Lỗi: Không sửa được danh mục");
        Thread.sleep(2000);
    }

    // Test case 8: Hủy thao tác sửa danh mục
    @Test(priority = 8)
    public void testCancelEditCategory() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        String tenDanhMucCu = "Kính Cận Loại 2", tenDanhMucMoi = "Kính Cận Loại 1";
        categoryPage.cancelEditCategoryInline(tenDanhMucCu, tenDanhMucMoi);
        assertTrue(validateUtils.verifyCategoryDoesNotExistInTable(tenDanhMucMoi), "Lỗi: Không sửa được danh mục");
        Thread.sleep(2000);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
