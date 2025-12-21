package com.test.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By menuBtn = By.id("react-burger-menu-btn");

    public void login(String user, String pass) {
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    // ✅ SUCCESS = landed on inventory page
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.urlContains("inventory"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ FAIL = still on login page
    public boolean isLoginFailed() {
        return driver.getCurrentUrl().contains("saucedemo.com");
    }
}



























/*
 * package com.test.pages;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.WebDriver;
 * 
 * public class LoginPage {
 * 
 * private WebDriver driver;
 * 
 * public LoginPage(WebDriver driver) { this.driver = driver; }
 * 
 * private By usernameField = By.id("user-name"); private By passwordField =
 * By.id("password"); private By loginButton = By.id("login-button"); private By
 * errorMessage = By.cssSelector("h3[data-test='error']");
 * 
 * public void login(String username, String password) {
 * driver.findElement(usernameField).clear();
 * driver.findElement(passwordField).clear();
 * 
 * driver.findElement(usernameField).sendKeys(username);
 * driver.findElement(passwordField).sendKeys(password);
 * driver.findElement(loginButton).click(); }
 * 
 * // 🔥 FIXED LOGIN SUCCESS CHECK public boolean isLoginSuccessful() { return
 * driver.getCurrentUrl().contains("inventory"); }
 * 
 * public boolean isErrorDisplayed() { return
 * driver.findElements(errorMessage).size() > 0; } }
 */