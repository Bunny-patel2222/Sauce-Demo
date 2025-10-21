package org.saucedemo.utils;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {


    public WebDriver driver;
    public static Properties properties;

    static {
        properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }
}
