package org.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(loginPage.class);

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes all @FindBy annotated elements
    }

    @FindBy( css = "#user-name")
    WebElement usernameField;

    @FindBy(css = "#password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;


    public void login(String username, String password) {
        logger.info("Attempting to log in with username: {}", username);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
       String pageTitle =  driver.getTitle();
         System.out.println("Page Title after login: " + pageTitle);
    }


}
