package org.saucedemo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.saucedemo.baseSetup.BaseTest;
import org.saucedemo.pages.inventoryPage;
import org.saucedemo.utils.ConfigReader;
import org.saucedemo.utils.DriverManager;
import org.testng.annotations.Test;

public class addToCart extends BaseTest {
    private static final Logger logger = LogManager.getLogger(addToCart.class);
    @Test(priority = 2)
    public void testAddToCart() {

        String itemsFromConfig = ConfigReader.getProperty("itemsToBuy");
       // String[] itemsToAdd = itemsFromConfig.split(",");
        String[] trimmedItemsToAdd = itemsFromConfig.trim().split("\\s*,\\s*");

//        String[] itemsToAdd = {
//                "Sauce Labs Fleece Jacket",
//                "Sauce Labs Bolt T-Shirt",
//                "Sauce Labs Backpack"
//        };


        inventoryPage inventoryPage = new inventoryPage(DriverManager.getDriver());
        inventoryPage.getAllInventoryItems();
        inventoryPage.createItemToCartButtonMap();
        for (String itemName : trimmedItemsToAdd) {

            inventoryPage.addToCartByName(itemName);
        }
        //   inventoryPage.addItemToCart();
        // wait.until(ExpectedConditions.titleContains("Sauce Labs Bolt T-Shirt"));
    }


}
