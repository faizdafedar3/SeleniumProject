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

        if (result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    DriverFactory.getDriver(),
                    result.getName()
            );

            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
