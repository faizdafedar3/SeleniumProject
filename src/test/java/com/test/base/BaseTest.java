package com.test.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.pages.LoginPage;
import com.test.utils.DriverFactory;
import com.test.utils.ExtentManager;
import com.test.utils.ScreenshotUtil;

public class BaseTest {

    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        extent = ExtentManager.getExtent();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get("https://www.saucedemo.com/");

        // ✅ LOGIN ONCE
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("standard_user", "secret_sauce");
    }

    protected void createTest(String testName) {
        extentTest.set(extent.createTest(testName));
    }

    protected ExtentTest getTest() {
        return extentTest.get();
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    DriverFactory.getDriver(),
                    result.getMethod().getMethodName()
            );

            getTest().fail(result.getThrowable());
            getTest().addScreenCaptureFromPath(screenshotPath);
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport() {
        extent.flush();
    }
}
