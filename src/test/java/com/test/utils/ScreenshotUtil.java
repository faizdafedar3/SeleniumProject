package com.test.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        new File(screenshotDir).mkdirs();

        String path = screenshotDir + testName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
