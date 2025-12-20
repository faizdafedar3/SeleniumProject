package com.test.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.utils.DriverFactory;
import com.test.utils.ExtentManager;
import com.test.utils.ScreenshotUtil;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getExtent();
    }

    @BeforeMethod
    public void startTest(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void captureTestResult(ITestResult result) {

        WebDriver driver = DriverFactory.getDriver();

        if (result.getStatus() == ITestResult.FAILURE) {

            test.fail(result.getThrowable());

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(driver, result.getName());

            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed successfully");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
