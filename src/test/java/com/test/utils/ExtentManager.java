package com.test.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("extent-report/ExtentReport.html");

            spark.config().setReportName("Selenium Automation Report");
            spark.config().setDocumentTitle("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // 🌐 System & Environment Info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));

            // 🌐 Browser (static for now)
            extent.setSystemInfo("Browser", "Chrome");

            // 🧱 Jenkins Info (works only in Jenkins)
            String jobName = System.getenv("JOB_NAME");
            String buildNumber = System.getenv("BUILD_NUMBER");

            if (jobName != null) {
                extent.setSystemInfo("Jenkins Job", jobName);
            }

            if (buildNumber != null) {
                extent.setSystemInfo("Jenkins Build", buildNumber);
            }
        }

        return extent;
    }
}
