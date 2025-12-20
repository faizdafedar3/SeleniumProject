package com.test.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.utils.DriverFactory;
import com.test.utils.ExtentManager;
import com.test.utils.ScreenshotUtil;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUpReport() {
        extent = ExtentManager.getExtent();
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {

        String screenshotPath =
                ScreenshotUtil.takeScreenshot(
                        DriverFactory.getDriver(),
                        result.getName()
                );

        if (result.getStatus() == ITestResult.FAILURE) {

            test.fail("Test Failed: " + result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            test.pass("Test Passed");
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP) {

            test.skip("Test Skipped: " + result.getThrowable());
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
