package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = "button[id^='remove']")
    private List<WebElement> removeButtons;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return isElementDisplayed(pageTitle) && getElementText(pageTitle).equals("Your Cart");
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public String getItemName(int index) {
        if (index >= 0 && index < itemNames.size()) {
            return getElementText(itemNames.get(index));
        }
        return "";
    }

    public String getItemPrice(int index) {
        if (index >= 0 && index < itemPrices.size()) {
            return getElementText(itemPrices.get(index));
        }
        return "";
    }

    public void removeItem(int index) {
        if (index >= 0 && index < removeButtons.size()) {
            clickElement(removeButtons.get(index));
        }
    }

    public ProductsPage clickContinueShopping() {
        clickElement(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public CheckoutPage clickCheckout() {
        clickElement(checkoutButton);
        return new CheckoutPage(driver);
    }

    public boolean isCheckoutButtonDisplayed() {
        return isElementDisplayed(checkoutButton);
    }
}
