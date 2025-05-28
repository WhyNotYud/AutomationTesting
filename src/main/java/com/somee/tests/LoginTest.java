package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.pages.LoginPage;
import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LoginPage loginPage;
    private By getLinkDangNhap = By.id("LinkDN");
    private By getLinkQuayLai = By.linkText("Quay lại");
    private By errorMessage = By.id("ContentPlaceHolder1_txtThongbaoDN");

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
    }

    // Test case 1: Đăng nhập thành công với admin
    @Test(priority = 1)
    public void testAdminLoginSuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        assertTrue(validateUtils.verifyUrl("Admin"), "Admin đăng nhập thất bại: Không thể chuyển hướng đến trang quản trị");
        Thread.sleep(2000);
    }

    // Test case 2: Đăng nhập thành công với user
    @Test(priority = 2)
    public void testUserLoginSuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        assertTrue(validateUtils.verifyUrl("Default"), "User đăng nhập thất bại: Không thể chuyển hướng đến trang chủ");
        Thread.sleep(2000);
    }

    // Test case 3: Sai tài khoản (mật khẩu đúng)
    @Test(priority = 3)
    public void testInvalidUsername() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("wronguser", "1234");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Sai tên tài khoản hoặc mật khẩu!"), "Đăng nhập không thành công: Sai tên tài khoản hoặc mật khẩu");
        Thread.sleep(2000);
    }

    // Test case 4: Sai mật khẩu (tài khoản đúng)
    @Test(priority = 4)
    public void testInvalidPassword() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "wrongpassword");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Sai tên tài khoản hoặc mật khẩu!"), "Đăng nhập không thành công: Sai tên tài khoản hoặc mật khẩu");
        Thread.sleep(2000);
    }

    // Test case 5: Sai cả tài khoản và mật khẩu
    @Test(priority = 5)
    public void testInvalidCredentials() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("wronguser", "wrongpassword");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Sai tên tài khoản hoặc mật khẩu!"), "Đăng nhập không thành công: Sai tên tài khoản hoặc mật khẩu");
        Thread.sleep(2000);
    }

    // Test case 6: Để trống tài khoản
    @Test(priority = 6)
    public void testEmptyUsername() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("", "1234");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Vui lòng nhập tài khoản và mật khẩu!"), "Đăng nhập không thành công: Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    // Test case 7: Để trống mật khẩu
    @Test(priority = 7)
    public void testEmptyPassword() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Vui lòng nhập tài khoản và mật khẩu!"), "Đăng nhập không thành công: Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    // Test case 8: Để trống cả tài khoản và mật khẩu
    @Test(priority = 8)
    public void testEmptyCredentials() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("", "");
        assertTrue(validateUtils.verifyText(validateUtils.getText(errorMessage), "Vui lòng nhập tài khoản và mật khẩu!"), "Đăng nhập không thành công: Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    // Test case 9: Hủy đăng nhập
    @Test(priority = 9)
    public void testCancelLogin() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        validateUtils.clickElement(getLinkQuayLai);
        assertTrue(validateUtils.verifyUrl("Default") , "Đăng nhập không thành công: Hủy thao tác đăng nhập");
        Thread.sleep(2000);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }
}