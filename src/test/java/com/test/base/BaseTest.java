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
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeMethod
    public void setupExtent() {
        extent = ExtentManager.getExtent();
    }

    protected void createTest(String testName) {
        extentTest.set(extent.createTest(testName));
    }

    protected ExtentTest getTest() {
        return extentTest.get();
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {

        // 📸 Screenshot ONLY on FAILURE
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

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
