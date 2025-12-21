package com.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}








/*
 * package com.test.utils;
 * 
 * import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver;
 * 
 * import io.github.bonigarcia.wdm.WebDriverManager;
 * 
 * public class DriverFactory {
 * 
 * private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
 * 
 * public static void setDriver() { WebDriverManager.chromedriver().setup();
 * driver.set(new ChromeDriver()); }
 * 
 * public static WebDriver getDriver() { return driver.get(); }
 * 
 * public static void quitDriver() { if (driver.get() != null) {
 * driver.get().quit(); driver.remove(); } } }
 */
