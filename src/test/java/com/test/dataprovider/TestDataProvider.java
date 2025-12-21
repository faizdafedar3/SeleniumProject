/*
 * package com.test.dataprovider;
 * 
 * import org.testng.annotations.DataProvider; import com.test.utils.ExcelUtils;
 * 
 * public class TestDataProvider {
 * 
 * @DataProvider(name = "loginData") public static Object[][] loginData() {
 * return ExcelUtils.getTestData("Login"); } }
 */


package com.test.dataprovider;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData", parallel = true)
    public static Object[][] loginData() {
        return new Object[][]{
            {"standard_user", "secret_sauce", "SUCCESS"},
            {"performance_glitch_user", "secret_sauce", "SUCCESS"},
            {"problem_user", "secret_sauce", "SUCCESS"},
            {"locked_out_user", "secret_sauce", "FAIL"},
            {"standard_user", "wrong_pass", "FAIL"}
        };
    }
}
