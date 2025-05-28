package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.pages.LoginPage;
import com.somee.pages.LogoutPage;
import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogoutTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LogoutPage logoutPage;
    private LoginPage loginPage;

    private By getLinkDangNhap = By.id("LinkDN");

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
    }

    // Test case 1: Đăng xuất thành công với admin
    @Test(priority = 1)
    public void testAdminLogoutSuccess() throws InterruptedException  {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        logoutPage.logout();
        assertTrue(validateUtils.verifyUrl("http://hauiproj.somee.com/Default.aspx"), "Đăng xuất thành công");
        Thread.sleep(2000);
    }

    // Test case 2: Đăng xuất thành công với user
    @Test(priority = 2)
    public void testUserLogoutSuccess() throws InterruptedException  {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        logoutPage.logout();
        assertTrue(validateUtils.verifyUrl("http://hauiproj.somee.com/Default.aspx"), "Đăng xuất thành công");
        Thread.sleep(2000);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
