package com.automation.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.automation.driver.WebDriverManager.getDriver;

public class Wait {

    private static final int DEFAULT_UI_TIMEOUT_MS = 2;

    private Wait() {

    }

    public static void untilAppear(By locator, int timeoutMs) {
        WebDriverWait webDriverWait = getWebDriverWait(timeoutMs);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void untilClickable(By locator, int timeoutMs) {
        WebDriverWait webDriverWait = getWebDriverWait(timeoutMs);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void scrollPageDown() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0,250)");
    }

    public static void scrollPageDownSearch() {
        getWebDriverWait(1000);
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0,650)");
        getDriver().manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
    }

    private static WebDriverWait getWebDriverWait(int timeoutMs) {
        return getWebDriverWait(timeoutMs, DEFAULT_UI_TIMEOUT_MS);
    }

    private static WebDriverWait getWebDriverWait(int timeoutMs, int retryIntervalMs) {
        return new WebDriverWait(getDriver(), timeoutMs / 1000, retryIntervalMs);
    }
}