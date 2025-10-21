package org.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(inventoryPage.class);

    public inventoryPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this); // Initializes all @FindBy annotated elements
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

    }



    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemName;

    @FindBy(className = "inventory_details_price")
    WebElement inventoryItemPrice;

    @FindBy(id = "add-to-cart")
    WebElement addsingleToCartButton;

    @FindBy(css = "button[id^='add-to-cart']")
    List<WebElement> addToCartButtons;

    private Map<String, WebElement> itemToCartButtonMap = new HashMap<>();





    public List<WebElement> getInventoryItemNames() {
        return inventoryItemName;

    }

    public void getAllInventoryItems() {
        List<WebElement> items = getInventoryItemNames();
        for (WebElement item : items) {
            logger.info("Item Name: {}", item.getText());

            String itemName = item.getText();

            HashMap<WebElement, String> itemMap = new HashMap<WebElement, String>();
            itemMap.put(item, itemName);

        }
    }

    public void createItemToCartButtonMap() {
        wait.until(ExpectedConditions.visibilityOfAllElements(getInventoryItemNames()));
        for (int i = 0; i < getInventoryItemNames().size(); i++) {
            String itemName = getInventoryItemNames().get(i).getText();
            WebElement addToCartButton = addToCartButtons.get(i);
            itemToCartButtonMap.put(itemName, addToCartButton);
            logger.info("Mapped item: {} to button: {}", itemName, addToCartButton.getAttribute("id"));
           // System.out.println("Mapped item: " + itemName + " to button: " + addToCartButton.getAttribute("id"));
        }
    }


    public void chooseItemByName(String name) {
        List<WebElement> items = getInventoryItemNames();
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(name)) {
               logger.info("Clicked on item: {}", item.getText());
                //System.out.println("Clicked on item: " + item.getText());
                item.click();
                addSingeItemToCart(item.getText());
                break;
            }
        }
    }

    public void addToCartByName(String name){
        List<WebElement> items = getInventoryItemNames();
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(name)) {
                logger.info("Found item: {}", item.getText());
              //  System.out.println("Found item: " + item.getText());
                WebElement addToCartButton = itemToCartButtonMap.get(name);
                if (addToCartButton != null) {
                    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
                    addToCartButton.click();
                    logger.info("Added to cart: {}", name);
                    //System.out.println("Added to cart: " + name);
                }
                break;
            }
        }

    }


    public void addSingeItemToCart(String item) {

        String itemPrice = inventoryItemPrice.getText();
        System.out.println("Item Price: " + itemPrice);
        addsingleToCartButton.click();
        logger.info("Item added to cart: {}", item);
        System.out.println("Item added to cart." + item);
    }
}
