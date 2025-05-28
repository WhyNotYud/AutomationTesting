package com.somee.pages;

import com.somee.utils.ValidateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateUtils validateUtils;

    private By thoatButton = By.id("LinkDX");


    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        validateUtils = new ValidateUtils(driver);
    }

    public void logout() {
        validateUtils.waitForPageLoaded();
        validateUtils.clickElement(thoatButton);
    }
}
