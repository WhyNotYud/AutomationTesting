package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.base.TestListener;
import com.somee.pages.LoginPage;
import com.somee.utils.ValidateUtils;
import com.somee.utils.VideoRecorder;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LoginPage loginPage;
    private final By getLinkDangNhap = By.id("LinkDN");
    private final By getLinkQuayLai = By.linkText("Quay lại");
    private final By errorMessage = By.id("ContentPlaceHolder1_txtThongbaoDN");

    @BeforeClass
    public void setUpRecord() throws Exception {
        VideoRecorder.startRecord("TestLogin");
    }

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
        try {
            VideoRecorder.startRecord("TestLogin");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 1, description = "Đăng nhập thành công với admin")
    public void testAdminLoginSuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        assertTrue(validateUtils.verifyUrl("Admin"), "Admin đăng nhập thất bại: Không thể chuyển hướng đến trang quản trị");
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Đăng nhập thành công với user")
    public void testUserLoginSuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        assertTrue(validateUtils.verifyUrl("Default"), "User đăng nhập thất bại: Không thể chuyển hướng đến trang chủ");
        Thread.sleep(2000);
    }

    @Test(priority = 3, description = "Sai tài khoản (mật khẩu đúng)")
    public void testInvalidUsername() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("wronguser", "1234");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Sai tên tài khoản hoặc mật khẩu!"), "Đăng nhập không thành công: Sai tên tài khoản hoặc mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 4, description = "Sai mật khẩu (tài khoản đúng)")
    public void testInvalidPassword() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "wrongpassword");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Sai tên tài khoản hoặc mật khẩu!"), "Đăng nhập không thành công: Sai tên tài khoản hoặc mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 5, description = "Sai cả tài khoản và mật khẩu")
    public void testInvalidCredentials() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("wronguser", "wrongpassword");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Sai tên tài khoản hoặc mật khẩu!"), "Đăng nhập không thành công: Sai tên tài khoản hoặc mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 6, description = "Để trống tài khoản")
    public void testEmptyUsername() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("", "1234");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Vui lòng nhập tài khoản và mật khẩu!"), "Đăng nhập không thành công: Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 7, description = "Để trống mật khẩu")
    public void testEmptyPassword() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Vui lòng nhập tài khoản và mật khẩu!"), "Đăng nhập không thành công: Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 8, description = "Để trống cả tài khoản và mật khẩu")
    public void testEmptyCredentials() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("", "");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Vui lòng nhập tài khoản và mật khẩu!"), "Đăng nhập không thành công: Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 9, description = "Hủy đăng nhập")
    public void testCancelLogin() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        validateUtils.clickElement(getLinkQuayLai);
        assertTrue(validateUtils.verifyUrl("Default") , "Đăng nhập không thành công: Hủy thao tác đăng nhập");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        VideoRecorder.stopRecord();
    }
}