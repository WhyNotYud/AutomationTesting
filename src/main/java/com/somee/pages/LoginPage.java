package com.somee.pages;

import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateUtils validateUtils;

    private By usernameField = By.id("ContentPlaceHolder1_txtTaikhoan");
    private By passwordField = By.id("ContentPlaceHolder1_txtMatkhau");
    private By loginButton = By.id("ContentPlaceHolder1_btDangnhap");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        validateUtils = new ValidateUtils(driver);
    }

    public void login(String username, String password) {
        validateUtils.waitForPageLoaded();
        validateUtils.setText(usernameField, username);
        validateUtils.setText(passwordField, password);
        validateUtils.clickElement(loginButton);
    }

}