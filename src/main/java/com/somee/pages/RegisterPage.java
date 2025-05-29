package com.somee.pages;

import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateUtils validateUtils;

    private By taiKhoanInput = By.id("ContentPlaceHolder1_txtTaiKhoan");
    private By matKhauInput = By.id("ContentPlaceHolder1_txtMatKhau");
    private By hoTenInput = By.id("ContentPlaceHolder1_txtHoTen");
    private By namSinhInput = By.id("ContentPlaceHolder1_txtNamSinh");
    private By gioiTinhOption = By.id("ContentPlaceHolder1_dllGioiTinh");
    private By emailInput = By.id("ContentPlaceHolder1_txtEmail");
    private By sdtInput = By.id("ContentPlaceHolder1_txtSdt");
    private By diaChiInput = By.id("ContentPlaceHolder1_txtDiaChi");

    private By dangKyButton = By.id("ContentPlaceHolder1_btDangky");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        validateUtils = new ValidateUtils(driver);
    }

    public void Register(String taiKhoan, String matKhau, String hoTen, String namSinh, String gioiTinh
            , String email, String sdt, String diaChi) {
        validateUtils.waitForPageLoaded();
        validateUtils.setText(taiKhoanInput, taiKhoan);
        validateUtils.setText(matKhauInput, matKhau);
        validateUtils.setText(hoTenInput, hoTen);
        validateUtils.setText(namSinhInput, namSinh);
        validateUtils.selectedValue(gioiTinhOption,gioiTinh);
        validateUtils.setText(emailInput, email);
        validateUtils.setText(sdtInput, sdt);
        validateUtils.setText(diaChiInput, diaChi);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        validateUtils.clickElement(dangKyButton);
    }

}
