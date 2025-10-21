package org.saucedemo.tests;

import org.saucedemo.baseSetup.BaseTest;
import org.saucedemo.pages.loginPage;
import org.saucedemo.utils.ConfigReader;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

public    String username =  ConfigReader.getProperty("Standarduser");
   public String password = ConfigReader.getProperty("password");

   @Test(priority = 1)
    public void testLogin() {
        loginPage LoginPage = new loginPage(driver);

        LoginPage.login(username, password);



    }
}