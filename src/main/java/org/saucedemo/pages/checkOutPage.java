package org.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalTime;

import java.util.List;

public class checkOutPage {
    private static final Logger logger = LogManager.getLogger(checkOutPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;


    public checkOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes all @FindBy annotated elements
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

    }


    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartLink;

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemNames;

    @FindBy(id = "checkout")
    WebElement checkoutButton;
    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;
    @FindBy(id = "continue")
    WebElement continueButton;
    @FindBy(id = "finish")
    WebElement finishButton;
    @FindBy(className = "summary_total_label")
    WebElement totalAmountLabel;
    @FindBy(className = "complete-header")
    WebElement orderCompletionHeader;

    public void checkOutTest() throws InterruptedException {

        shoppingCartLink.click();
        // Thread.sleep(5000);
        logger.info("Navigated to: {}" , driver.getTitle());
        List<String> itemNames = inventoryItemNames.stream()
                .map(WebElement::getText)
                .toList();
        logger.info("Items in inventory: {}" , itemNames);
        checkoutButton.click();
    }

    public void completeCheckOut(String firstname, String lastname,  String postalcode) {
        LocalTime currentTime = LocalTime.now();

        firstNameField.sendKeys(firstname + currentTime.getMinute());
        lastNameField.sendKeys(lastname + currentTime.getMinute());
        postalCodeField.sendKeys(postalcode + currentTime.getMinute());
        continueButton.click();


        System.out.println("Checkout Completed Successfully");
        System.out.println("Total Amount: " + totalAmountLabel.getText());
        finishButton.click();

        logger.info("Navigate to: {}" , orderCompletionHeader.getText());


    }
}
