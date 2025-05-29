package com.somee.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ValidateUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ValidateUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void setText(By element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public String getText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectedConditions = new ExpectedCondition<>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            int timeoutWaitForPageLoaded = 10;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaitForPageLoaded));
            wait.until(expectedConditions);
        } catch (Throwable e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean verifyUrl(String url) {
        return driver.getCurrentUrl().contains(url);
    }

    public boolean verifyText(String text1, String text2) {
        return text1.contains(text2);
    }

    public void selectedValue(By element, String value) {
        WebElement dropdownElement= wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }
}
