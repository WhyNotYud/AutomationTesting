package com.somee.pages;

import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateUtils validateUtils;

    private By thanhToanButton = By.id("ContentPlaceHolder1_btnThanhToan");
    private By ghiChuInput = By.id("ContentPlaceHolder1_dtlThongTinUser_txtGhiChu_0");
    private By datHangButton = By.id("ContentPlaceHolder1_dtlThongTinUser_btnThanhToan_0");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        validateUtils = new ValidateUtils(driver);
    }

    public void Order(String ghiChu) {
        validateUtils.waitForPageLoaded();
        validateUtils.clickElementWithJS(thanhToanButton);
        validateUtils.setValueWithJS(ghiChuInput, ghiChu);
        validateUtils.clickElementWithJS(datHangButton);
    }
}
