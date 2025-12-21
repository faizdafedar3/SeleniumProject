package com.test.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {
        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("extent-report/ExtentReport.html");

            reporter.config().setReportName("Selenium Automation Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("OS", "Windows 11");
            extent.setSystemInfo("Java", "21");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Executed By", "Jenkins");

        }
        return extent;
    }
}
