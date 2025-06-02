package com.somee.tests;

import com.somee.base.BaseTest;
import com.somee.base.TestListener;
import com.somee.pages.CartPage;
import com.somee.pages.LoginPage;
import com.somee.utils.Log;
import com.somee.utils.ValidateUtils;
import com.somee.utils.VideoRecorder;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {
    private ValidateUtils validateUtils;
    private LoginPage loginPage;
    private CartPage cartPage;

    private final By dangNhapButton = By.id("LinkDN");
    private final By getGioHang = By.id("HyperLink4");
    private final By sanPham1 = By.id("ContentPlaceHolder1_DataList1_HyperLink1_0");
    private final By tenSanPham1 = By.id("ContentPlaceHolder1_Datalist1_lblTenSP_0");
    private final By sanPham2 = By.id("ContentPlaceHolder1_DataList1_HyperLink1_2");
    private final By tenSanPham2 = By.id("ContentPlaceHolder1_Datalist1_lblTenSP_0");
    private final By sanPham3 = By.id("ContentPlaceHolder1_DataList1_HyperLink1_4");
    private final By tenSanPham3 = By.id("ContentPlaceHolder1_Datalist1_lblTenSP_0");
    private final By xoaGioHang = By.id("ContentPlaceHolder1_btnXoaGioHang");
    private final By thongBao = By.id("ContentPlaceHolder1_gvGioHang_Label1");

    @BeforeClass
    public void setUpRecord() throws Exception {
        Log.info("Bắt đầu quay video");
        VideoRecorder.startRecord("TestCart");
    }

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        validateUtils = new ValidateUtils(driver);
        validateUtils.clickElementWithJS(dangNhapButton);
    }

    @Test(priority = 1, description = "Thêm sản phẩm vào giỏ hàng")
    public void addProductToCart() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getGioHang);
        boolean check = cartPage.addProductToCart(sanPham1, tenSanPham1, "5");
        assertTrue(check, "Đã thêm sản phẩm vào giỏ hàng thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Thêm nhiều sản phẩm vào giỏ hàng")
    public void addProductsToCart() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getGioHang);
        boolean check1 = cartPage.addProductToCart(sanPham2, tenSanPham2, "5");
        boolean check2 = cartPage.addProductToCart(sanPham3, tenSanPham3, "10");
        assertTrue(check1 && check2, "Đã thêm các sản phẩm vào giỏ hàng thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 3, description = "Cập nhật số lượng sản phẩm trong giỏ hàng")
    public void updateNumberOfProduct() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getGioHang);
        boolean check = cartPage.addProductToCart(sanPham1, tenSanPham1, "5");
        String name = "Kính Thời Trang 1";
        By soLuongInput = By.xpath(
                "//table[@id='ContentPlaceHolder1_gvGioHang']//tr[./td/span[contains(@id, 'lblTenSP_') and text()='" + name + "']]/td/input[contains(@id, 'txtSoLuong_') and @type='text']"
        );
        By capNhatButton = By.xpath(
                "//table[@id='ContentPlaceHolder1_gvGioHang']//tr[./td/span[contains(@id, 'lblTenSP_') and text()='" + name + "']]/td/input[@type='button' and @value='Cập nhật']"
        );
        cartPage.updateNumberOfProduct(soLuongInput, capNhatButton, "11");
        assertEquals(validateUtils.checkNumberOfProduct(name), 11, "Đã cập nhật số lượng thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 4, description = "Xóa sản phẩm khỏi giỏ hàng")
    public void deleteProductFromCart() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getGioHang);
        boolean check = cartPage.addProductToCart(sanPham1, tenSanPham1, "5");
        String name = "Kính Thời Trang 1";
        By deleteButton = By.xpath(
                "//table[@id='ContentPlaceHolder1_gvGioHang']//tr[./td/span[contains(@id, 'lblTenSP_') and text()='" + name + "']]/td/input[@type='button' and @value='Xóa']"
        );
        validateUtils.clickElementWithJS(deleteButton);
        assertFalse(validateUtils.checkProductInCart(name), "Đã xóa sản phẩm khỏi giỏ hàng thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 5, description = "Xóa sản phẩm khỏi giỏ hàng")
    public void deleteAllProductsFromCart() throws InterruptedException {
        validateUtils.waitForPageLoaded();
        loginPage.login("abcd", "abcd");
        validateUtils.clickElementWithJS(getGioHang);
        boolean check1 = cartPage.addProductToCart(sanPham1, tenSanPham1, "5");
        boolean check2 = cartPage.addProductToCart(sanPham2, tenSanPham2, "5");
        validateUtils.clickElementWithJS(xoaGioHang);
        assertTrue(validateUtils.verifyText(validateUtils.getText(thongBao), "Không có mặt hàng nào trong giỏ hàng!"), "Đã xóa giỏ hàng thành công!");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        VideoRecorder.stopRecord();
        Log.info("Kết thúc quay video");
    }
}
