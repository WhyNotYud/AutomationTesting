package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.pages.RegisterPage;
import com.somee.utils.ValidateUtils;
import com.somee.utils.VideoRecorder;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegisterTest extends BaseTest {
    private ValidateUtils validateUtils;
    private RegisterPage registerPage;

    private final By getLinkDangKy = By.id("LinkDK");
    private final By getLinkQuayLai = By.linkText("Quay lại");
    private final By thongTinTaiKhoan = By.id("lblHTTK");
    private final By thongBao = By.id("ContentPlaceHolder1_lblThongBao");

    @BeforeClass
    public void setUpRecord() throws Exception {
        VideoRecorder.startRecord("TestRegister");
    }

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        registerPage = new RegisterPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangKy);
    }

    // Test 1: Đăng ký thành công
    @Test(priority = 1)
    public void testRegisterSuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "user9999", "Nguyễn Văn A", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "0999999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongTinTaiKhoan), "Xin chào"), "Tạo tài khoản thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    // Test 2: Đăng ký không thành công(Tài khoản để trống)
    public void testRegisterWithEmptyUsername() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("", "user9999", "Nguyễn Văn A", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "0999999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Nhập tài khoản"), "Tạo tài khoản không thành công: Vui lòng nhập tài khoản");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    // Test 3: Đăng ký không thành công(Mật khẩu để trống)
    public void testRegisterWithEmptyPassword() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "", "Nguyễn Văn A", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "0999999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Nhập mật khẩu"), "Tạo tài khoản không thành công: Vui lòng nhập mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    // Test 4: Đăng ký không thành công(Họ và tên để trống)
    public void testRegisterWithEmptyFullname() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "user9999", "", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "0999999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Nhập họ tên"), "Tạo tài khoản không thành công: Vui lòng nhập họ tên");
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    // Test 5: Đăng ký không thành công(Năm sinh để trống)
    public void testRegisterWithEmptyBirthDate() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "user9999", "Nguyễn Văn A", "", "Nam", "nguyenvana9999@gmail.com", "0999999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Chọn ngày sinh"), "Tạo tài khoản không thành công: Vui lòng chọn ngày sinh");
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    // Test 6: Đăng ký không thành công(Email để trống)
    public void testRegisterWithEmptyEmail() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "user9999", "Nguyễn Văn A", "01/01/2004", "Nam", "", "099999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Nhập Email"), "Tạo tài khoản không thành công: Vui lòng nhập Email");
        Thread.sleep(2000);
    }

    @Test(priority = 7)
    // Test 7: Đăng ký không thành công(Số điện thoại để trống)
    public void testRegisterWithEmptyPhone() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "user9999", "Nguyễn Văn A", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Nhập số điện thoại"), "Tạo tài khoản không thành công: Vui lòng nhập số điện thoại");
        Thread.sleep(2000);
    }

    @Test(priority = 8)
    // Test 8: Đăng ký không thành công(Địa chỉ để trống)
    public void testRegisterWithEmptyAddress() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("user9999", "user9999", "Nguyễn Văn A", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "099999999", "");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Nhập địa chỉ"), "Tạo tài khoản không thành công: Vui lòng nhập địa chỉ");
        Thread.sleep(2000);
    }

    // Test 9: Đăng ký không thành công(Tài khoản đã tồn tại)
    @Test(priority = 9)
    public void testRegisterFails() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        registerPage.Register("abcd", "abcd", "Nguyễn Văn A", "01/01/2004", "Nam", "nguyenvana9999@gmail.com", "099999999", "Hà Nội");
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Tài khoản đã có người sử dụng"), "Tạo tài khoản không thành công: Tài khoản đã có người sử dụng");
        Thread.sleep(2000);
    }

    // Test 10: Hủy thao tác đăng ký
    @Test(priority = 10)
    public void testCancelRegister() throws InterruptedException {
        validateUtils.clickElementWithJS(getLinkQuayLai);
        validateUtils.waitForPageLoaded();
        assertTrue(validateUtils.verifyUrl("http://hauiproj.somee.com/Default.aspx"), "Tạo tài khoản không thành công: Hủy thao tác đăng ký");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        VideoRecorder.stopRecord();
    }
}
