package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.pages.LoginPage;
import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OrderTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LoginPage loginPage;

    private By getLinkDangNhap = By.id("LinkDN");
    private By getQuayLai = By.linkText("Quay lại");
    private By getDanhSachSanPham = By.id("HyperLink2");
    private By getSanPham = By.id("ContentPlaceHolder1_DataList1_HyperLink1_2");
    private By getSoLuong = By.id("ContentPlaceHolder1_Datalist1_txtSoLuong_0");
    private By getThemGioHang = By.id("ContentPlaceHolder1_Datalist1_btnThemVaoGio_0");
    private By getThanhToan = By.id("ContentPlaceHolder1_btnThanhToan");
    private By ghiChu = By.id("ContentPlaceHolder1_dtlThongTinUser_txtGhiChu_0");
    private By getDatHang = By.id("ContentPlaceHolder1_dtlThongTinUser_btnThanhToan_0");
    private By getThongBao = By.id("ContentPlaceHolder1_lblThanhCong");
    private By getThongBaoLoi = By.id("ContentPlaceHolder1_lblThongBao");
    private By getThongBaoLoiUser = By.id("ContentPlaceHolder1_dtlThongTinUser_lblThongBao_0");
    private By getGioHang = By.id("HyperLink4");

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElement(getLinkDangNhap);
    }

    // Test case 1: Đặt mua thành công
    @Test(priority = 1)
    public void testOrderSuccess() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getDanhSachSanPham);
        validateUtils.clickElementWithJS(getSanPham);
        validateUtils.setValueWithJS(getSoLuong, "2");
        validateUtils.clickElementWithJS(getThemGioHang);
        validateUtils.clickElementWithJS(getThanhToan);
        validateUtils.setText(ghiChu, "Giao hàng vào buổi sáng");
        validateUtils.clickElementWithJS(getDatHang);
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBao), "Đặt hàng thành công!"), "Người dùng đặt hàng thành công!");
        Thread.sleep(2000);
    }

    // Test case 2: Đặt mua trong khi giỏ hàng đang trống
    @Test(priority = 2)
    public void testOrderWithEmptyCart() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getGioHang);
        validateUtils.clickElementWithJS(getThanhToan);
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoi), "Không thể thanh toán khi không có mặt hàng nào trong giỏ hàng!"), "Người dùng đặt hàng không thành công: Không có mặt hàng nào trong giỏ hàng");
        Thread.sleep(2000);
    }

    // Test case 3: Đặt mua nhưng thiếu trường thông tin ghi chú
    @Test(priority = 3)
    public void testOrderWithEmptyNote() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("user9999", "user9999");
        validateUtils.clickElementWithJS(getDanhSachSanPham);
        validateUtils.clickElementWithJS(getSanPham);
        validateUtils.setValueWithJS(getSoLuong, "2");
        validateUtils.clickElementWithJS(getThemGioHang);
        validateUtils.clickElementWithJS(getThanhToan);
        validateUtils.setText(ghiChu, "");
        validateUtils.clickElementWithJS(getDatHang);
        assertTrue(validateUtils.verifyText(validateUtils.getText(getThongBaoLoiUser), "Nhập ghi chú!"), "Người dùng đặt hàng không thành công: Nhập ghi chú!");
        Thread.sleep(2000);
    }

    // Test case 4: Hủy thao tác đặt mua
    @Test(priority = 4)
    public void testCancelOrder() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("user9999", "user9999");
        validateUtils.clickElementWithJS(getDanhSachSanPham);
        validateUtils.clickElementWithJS(getSanPham);
        validateUtils.setValueWithJS(getSoLuong, "2");
        validateUtils.clickElementWithJS(getThemGioHang);
        validateUtils.clickElementWithJS(getThanhToan);
        validateUtils.setText(ghiChu, "Giao hàng vào buổi sáng");
        validateUtils.clickElementWithJS(getQuayLai);
        assertTrue(validateUtils.verifyUrl("http://hauiproj.somee.com/Giohang.aspx"), "Người dùng đặt hàng không thành công: Hủy thao tác thanh toán!");
        Thread.sleep(2000);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
