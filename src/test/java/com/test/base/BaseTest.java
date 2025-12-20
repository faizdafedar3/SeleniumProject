package com.test.base;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.test.utils.*;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getExtent();
    }

    @AfterMethod
    public void captureResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    DriverFactory.getDriver(),
                    result.getName()
            );

            test.fail("Test failed");
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
