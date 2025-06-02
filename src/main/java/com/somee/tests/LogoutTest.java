package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.base.TestListener;
import com.somee.pages.LoginPage;
import com.somee.pages.LogoutPage;
import com.somee.utils.ValidateUtils;
import com.somee.utils.VideoRecorder;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class LogoutTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LogoutPage logoutPage;
    private LoginPage loginPage;

    private final By getLinkDangNhap = By.id("LinkDN");

    @BeforeClass
    public void setUpRecord() throws Exception {
        VideoRecorder.startRecord("TestLogout");
    }

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
    }

    @Test(priority = 1, description = "Đăng xuất thành công với admin")
    public void testAdminLogoutSuccess() throws InterruptedException  {
        validateUtils.waitForPageLoaded();
        loginPage.login("admin", "1234");
        logoutPage.logout();
        assertTrue(validateUtils.verifyUrl("http://hauiproj.somee.com/Default.aspx"), "Đăng xuất thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Đăng xuất thành công với user")
    public void testUserLogoutSuccess() throws InterruptedException  {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        logoutPage.logout();
        assertTrue(validateUtils.verifyUrl("http://hauiproj.somee.com/Default.aspx"), "Đăng xuất thành công");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        VideoRecorder.stopRecord();
    }
}
