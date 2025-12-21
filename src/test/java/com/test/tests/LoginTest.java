package com.test.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.dataprovider.TestDataProvider;
import com.test.pages.LoginPage;
import com.test.utils.DriverFactory;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void loginTest(String username, String password, String expected) {

        loginPage.login(username, password);

        if (expected.equalsIgnoreCase("SUCCESS")) {
            Assert.assertTrue(
                    loginPage.isLoginSuccessful(),
                    "Expected login to be SUCCESS"
            );
        } else {
            Assert.assertFalse(
                    loginPage.isLoginSuccessful(),
                    "Expected login to FAIL"
            );
        }
    }
}
