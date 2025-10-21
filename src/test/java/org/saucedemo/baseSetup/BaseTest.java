package org.saucedemo.baseSetup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.saucedemo.utils.ConfigReader;
import org.saucedemo.utils.DriverManager;
import org.testng.annotations.*;

import java.sql.Driver;
import java.time.Duration;

public class BaseTest {
    public  WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriverWait wait;

    public void browserSetup() {

        String Browser = ConfigReader.getProperty("browser");
        logger.info("Setting up browser: {}", Browser);

        switch (Browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + Browser);
        }
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

   @BeforeSuite
    public void launchApp() {
        String URL = ConfigReader.getProperty("URL");

        logger.info("Launching Browser and Navigating to  {}", URL);
        browserSetup();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get(URL);
       String PageTitle = driver.getTitle();
        logger.info("Login Page Title is{}", PageTitle);


    }

    @AfterSuite
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            //DriverManager.removeDriver();
        }
        logger.info("Browser Closed Successfully");

    }
}
