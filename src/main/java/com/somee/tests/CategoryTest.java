package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.base.TestListener;
import com.somee.pages.CategoryPage;
import com.somee.pages.LoginPage;
import com.somee.utils.ValidateUtils;
import com.somee.utils.VideoRecorder;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class CategoryTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LoginPage loginPage;
    private CategoryPage categoryPage;

    private final By getLinkDangNhap = By.id("LinkDN");
    private final By getDanhMuc = By.id("HyperLink5");
    private final By getBangDanhMuc = By.id("ContentPlaceHolder1_GridView1");
    private final By getThongBaoLoi = By.id("ContentPlaceHolder1_lblThongBao");

    @BeforeClass
    public void setUpRecord() throws Exception {
        VideoRecorder.startRecord("TestLogin");
    }

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        categoryPage = new CategoryPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
    }

    @Test(priority = 1, description = "Thêm danh mục thành công")
    public void addCategorySuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("100","Kính Cận Loại 2");
        System.out.println(validateUtils.verifyCategoryAddedToTable(getBangDanhMuc, "Kính Cận Loại 2"));
        assertTrue(validateUtils.verifyCategoryAddedToTable(getBangDanhMuc, "Kính Cận Loại 2"), "Thêm danh mục thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Thêm danh mục với mã danh mục bị trống")
    public void addCategoryWithEmptyID() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("","Kính Cận Loại 3");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Nhập mã danh mục!"), "Thêm danh mục không thành công: Vui lòng nhập mã danh mục!");
        Thread.sleep(2000);
    }

    @Test(priority = 3, description = "Thêm danh mục với tên danh mục bị trống")
    public void addCategoryWithEmptyName() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("1001","");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Nhập tên danh mục!"), "Thêm danh mục không thành công: Vui lòng nhập tên danh mục!");
        Thread.sleep(2000);
    }

    @Test(priority = 4, description = "Thêm danh mục với mã danh mục đã tồn tại")
    public void addCategoryWithExistID() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("2","Kính Cận Loại 3");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Mã danh mục đã được sử dụng!"), "Thêm danh mục không thành công: Mã danh mục đã được sử dụng!");
        Thread.sleep(2000);
    }

    @Test(priority = 5, description = "Thêm danh mục với tên danh mục đã tồn tại")
    public void addCategoryWithExistName() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        categoryPage.addCategory("1001","Kính Bơi Chống Nắng");
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Tên danh mục đã được sử dụng!"), "Thêm danh mục không thành công: Tên danh mục đã được sử dụng!");
        Thread.sleep(2000);
    }

    @Test(priority = 6, description = "Xóa danh mục thành công")
    public void deleteCategorySuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        String tenDanhMuc = "Kính Cận Loại 2";
        categoryPage.deleteCategoryByName(tenDanhMuc);
        Thread.sleep(2000);
        validateUtils.waitForPageLoaded();
        assertTrue(validateUtils.verifyCategoryDoesNotExistInTable(tenDanhMuc), "Đã xóa danh mục thành công!");
        Thread.sleep(2000);
    }

    @Test(priority = 7, description = "Sửa danh mục")
    public void testEditCategorySuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        String tenDanhMucCu = "Kính Cận Loại 2", tenDanhMucMoi = "Kính Cận Loại 1";
        categoryPage.editCategoryInline(tenDanhMucCu, tenDanhMucMoi);
        assertTrue(validateUtils.verifyCategoryExistsInTable(tenDanhMucMoi), "Lỗi: Không sửa được danh mục");
        Thread.sleep(2000);
    }

    @Test(priority = 8, description = "Hủy thao tác sửa danh mục")
    public void testCancelEditCategory() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        validateUtils.clickElementWithJS(getDanhMuc);
        String tenDanhMucCu = "Kính Cận Loại 2", tenDanhMucMoi = "Kính Cận Loại 1";
        categoryPage.cancelEditCategoryInline(tenDanhMucCu, tenDanhMucMoi);
        assertTrue(validateUtils.verifyCategoryDoesNotExistInTable(tenDanhMucMoi), "Lỗi: Không sửa được danh mục");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        VideoRecorder.stopRecord();
    }
}
