package com.test.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.base.BaseTest;
import com.test.pages.LoginPage;
import com.test.utils.DriverFactory;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expected) {

        createTest("Login Test - " + username + " | Expected: " + expected);

        getTest().info("Browser launched");
        getTest().info("Navigated to login page");

        loginPage.login(username, password);

        if (expected.equals("SUCCESS")) {
            Assert.assertTrue(loginPage.isLoginSuccessful());
            getTest().pass("Login successful");
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed());
            getTest().pass("Login failed as expected");
        }
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "SUCCESS"},
                {"performance_glitch_user", "secret_sauce", "SUCCESS"},
                {"problem_user", "secret_sauce", "SUCCESS"},
                {"locked_out_user", "secret_sauce", "FAIL"},
                {"wrong_user", "wrong_pass", "FAIL"}
        };
    }
}
