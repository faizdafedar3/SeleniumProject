/*
 * package com.test.tests;
 * 
 * import org.testng.Assert; import org.testng.annotations.BeforeMethod; import
 * org.testng.annotations.Test;
 * 
 * import com.test.dataprovider.TestDataProvider; import
 * com.test.pages.LoginPage; import com.test.utils.DriverFactory;
 * 
 * public class LoginTest {
 * 
 * private LoginPage loginPage;
 * 
 * @BeforeMethod public void setUp() { DriverFactory.setDriver();
 * DriverFactory.getDriver().get("https://www.saucedemo.com/"); loginPage = new
 * LoginPage(DriverFactory.getDriver()); }
 * 
 * @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
 * public void loginTest(String username, String password, String expected) {
 * 
 * loginPage.login(username, password);
 * 
 * if (expected.equalsIgnoreCase("SUCCESS")) { Assert.assertTrue(
 * loginPage.isLoginSuccessful(), "Expected login to be SUCCESS" ); } else {
 * Assert.assertFalse( loginPage.isLoginSuccessful(), "Expected login to FAIL"
 * ); } } }
 */


package com.test.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.pages.LoginPage;
import com.test.utils.DriverFactory;
import com.test.dataprovider.TestDataProvider;

public class LoginTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void loginTest(String username, String password, String expected) {

        System.out.println("User: " + username + " | Thread ID: " + Thread.currentThread().getId());

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(username, password);

        if (expected.equalsIgnoreCase("SUCCESS")) {
            Assert.assertTrue(loginPage.isLoginSuccessful(),
                    "Expected login to be SUCCESS");
        } else {
            Assert.assertTrue(loginPage.isLoginFailed(),
                    "Expected login to FAIL");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

