package org.saucedemo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.saucedemo.pages.checkOutPage;
import org.saucedemo.baseSetup.BaseTest;
import org.saucedemo.utils.ConfigReader;
import org.saucedemo.utils.DriverManager;
import org.testng.annotations.Test;

public class CheckOut extends BaseTest {
    private static final Logger logger = LogManager.getLogger(CheckOut.class);
    String firstname = ConfigReader.getProperty("firstname");
    String lastname = ConfigReader.getProperty("lastname");
    String postalcode = ConfigReader.getProperty("postalcode");

    @Test (priority = 3)
    public void testCheckout() throws InterruptedException {



        checkOutPage checkOutPage = new checkOutPage(DriverManager.getDriver());
        checkOutPage.checkOutTest();
        checkOutPage.completeCheckOut(firstname, lastname, postalcode);
       // checkOutPage.checkOutProcess("John", "Doe", "12345");


    }
}
