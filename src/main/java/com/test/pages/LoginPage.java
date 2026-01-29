package com.test.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By user = By.id("user-name");
    private By pass = By.id("password");
    private By loginBtn = By.id("login-button");
    private By title = By.className("title");
    private By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String u, String p) {
        driver.findElement(user).sendKeys(u);
        driver.findElement(pass).sendKeys(p);
        driver.findElement(loginBtn).click();
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(title)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(error)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
