package com.test.tests;

import com.test.base.BaseTest;
import com.test.pages.LoginPage;
import com.test.utils.DriverFactory;

import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        test = extent.createTest(
                "Login Test - User: " + username + " | Expected: " + expectedResult
        );

        test.info("Browser launched");
        test.info("Navigated to SauceDemo login page");
        test.info("Attempting login with username: " + username);

        loginPage.login(username, password);

        if (expectedResult.equals("SUCCESS")) {

            Assert.assertTrue(loginPage.isLoginSuccessful());
            test.pass("Login successful as expected");

        } else {

            Assert.assertTrue(loginPage.isErrorDisplayed());
            test.pass("Login failed as expected");
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "SUCCESS"},
                {"performance_glitch_user", "secret_sauce", "SUCCESS"},
                {"problem_user", "secret_sauce", "SUCCESS"}, // ❌ intentional failure
                {"locked_out_user", "secret_sauce", "FAIL"},
                {"wrong_user", "wrong_pass", "FAIL"}
        };
    }
}
