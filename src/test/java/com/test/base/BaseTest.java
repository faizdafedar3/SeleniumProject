package com.test.base;

import org.openqa.selenium.WebDriver;
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
    public void attachScreenshotOnFailure(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                WebDriver driver = DriverFactory.getDriver();
                String screenshotPath =
                        ScreenshotUtil.captureScreenshot(driver, result.getName());

                test.fail("Test Failed: " + result.getThrowable());
                test.addScreenCaptureFromPath(screenshotPath);

            } catch (Exception e) {
                test.fail("Screenshot capture failed: " + e.getMessage());
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport() {
        extent.flush();
    }
}
