package com.test.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.utils.ExtentManager;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUpReport() {
        extent = ExtentManager.getExtent();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
