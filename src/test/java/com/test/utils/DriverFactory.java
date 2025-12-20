package com.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // 🔹 Enable this for Jenkins headless runs
        // options.addArguments("--headless=new");
        // options.addArguments("--window-size=1920,1080");

        driver.set(new ChromeDriver(options));
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

    // 🔹 For Extent Report
    public static String getBrowserName() {
        return ((RemoteWebDriver) getDriver())
                .getCapabilities()
                .getBrowserName();
    }

    public static String getBrowserVersion() {
        return ((RemoteWebDriver) getDriver())
                .getCapabilities()
                .getBrowserVersion();
    }
}
