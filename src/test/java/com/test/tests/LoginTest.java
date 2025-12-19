package com.test.tests;

import com.test.pages.LoginPage;
import com.test.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        loginPage.login(username, password);

        if (expectedResult.equals("SUCCESS")) {
            Assert.assertTrue(
                    loginPage.isLoginSuccessful(),
                    "Login should succeed"
            );
        } else {
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "Error message should be shown"
            );
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @DataProvider(name = "loginData")
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
