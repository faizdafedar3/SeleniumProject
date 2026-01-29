package com.test.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            prop = new Properties();
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Config load failed");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
