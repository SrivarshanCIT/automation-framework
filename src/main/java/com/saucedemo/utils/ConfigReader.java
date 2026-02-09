package com.saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream file = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    public static String getStandardUser() {
        return properties.getProperty("standard.user");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getLockedUser() {
        return properties.getProperty("locked.user");
    }

    public static String getProblemUser() {
        return properties.getProperty("problem.user");
    }

    public static String getPerformanceUser() {
        return properties.getProperty("performance.user");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public static String getScreenshotPath() {
        return properties.getProperty("screenshot.path");
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(properties.getProperty("screenshot.on.failure"));
    }
}
