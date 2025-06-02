package com.somee.pages;

import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateUtils validateUtils;

    private By tiepTucMuaHang = By.id("ContentPlaceHolder1_btnTiepTucMuaHang");
    private By soLuongMua = By.id("ContentPlaceHolder1_Datalist1_txtSoLuong_0");
    private By themVaoGioHang = By.id("ContentPlaceHolder1_Datalist1_btnThemVaoGio_0");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        validateUtils = new ValidateUtils(driver);
    }

    public boolean addProductToCart(By sanPham, By tenSanPham, String soLuong) {
        validateUtils.waitForPageLoaded();
        validateUtils.clickElementWithJS(tiepTucMuaHang);
        validateUtils.clickElementWithJS(sanPham);
        String tenSanPhamText = validateUtils.getText(tenSanPham);
        validateUtils.setValueWithJS(soLuongMua, soLuong);
        validateUtils.clickElementWithJS(themVaoGioHang);
        return validateUtils.checkProductInCart(tenSanPhamText);
    }

    public void updateNumberOfProduct(By soLuongInput, By capNhatButton, String soLuong) {
        validateUtils.waitForPageLoaded();
        validateUtils.setValueWithJS(soLuongInput, soLuong);
        validateUtils.clickElementWithJS(capNhatButton);
    }
}
