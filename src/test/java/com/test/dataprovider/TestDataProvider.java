package com.test.dataprovider;

import org.testng.annotations.DataProvider;
import com.test.utils.ExcelUtils;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return ExcelUtils.getTestData("Login");
    }
}
