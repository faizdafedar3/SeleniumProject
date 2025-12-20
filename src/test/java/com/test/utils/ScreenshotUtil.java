package com.test.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/screenshots";

        new File(screenshotDir).mkdirs();

        String screenshotPath =
                screenshotDir + "/" + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
