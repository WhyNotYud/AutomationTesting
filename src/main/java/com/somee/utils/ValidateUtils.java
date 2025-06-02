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
import java.util.List;

public class ValidateUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ValidateUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void setText(By element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public String getText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    public void clickElementWithJS(By element) {
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element)); // Vẫn chờ phần tử có thể click được
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
    }

    public void setValueWithJS(By element, String text) {
        WebElement webElement = driver.findElement(element);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", webElement, text);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", webElement);
    }

    public boolean verifyCategoryAddedToTable(By categoryTable, String categoryName) {
        WebElement table = driver.findElement(categoryTable);
        boolean found = false;
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(categoryName)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        return found;
    }

    public boolean verifyCategoryExistsInTable(String categoryName) {
        waitForPageLoaded();
        By categoryTableLocator = By.id("ContentPlaceHolder1_GridView1");
        By categoryRowLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td[text()='" + categoryName + "']]"
        );
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(categoryRowLocator));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean verifyCategoryDoesNotExistInTable(String categoryName) {
        waitForPageLoaded();
        By categoryTableLocator = By.id("ContentPlaceHolder1_GridView1");
        By categoryRowLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_GridView1']//tr[./td[text()='" + categoryName + "']]"
        );
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(categoryRowLocator));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean checkProductInCart(String productName) {
        waitForPageLoaded();
        By cartTableLocator = By.id("ContentPlaceHolder1_gvGioHang");
        By productRowInCartLocator = By.xpath(
                "//table[@id='ContentPlaceHolder1_gvGioHang']//tr[./td/span[contains(@id, 'lblTenSP_') and text()='" + productName + "']]"
        );
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productRowInCartLocator));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public int checkNumberOfProduct(String productName) {
        waitForPageLoaded();
        By viTriSoLuong = By.xpath(
                "//table[@id='ContentPlaceHolder1_gvGioHang']//tr[./td/span[contains(@id, 'lblTenSP_') and text()='" + productName + "']]/td/input[contains(@id, 'txtSoLuong_') and @type='text']"
        );
        try {
            WebElement soLuong = wait.until(ExpectedConditions.visibilityOfElementLocated(viTriSoLuong));
            String quantityString = soLuong.getAttribute("value");
            return Integer.parseInt(quantityString.trim());
        } catch (org.openqa.selenium.TimeoutException e) {
            return -1;
        }
    }
}
