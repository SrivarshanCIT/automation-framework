package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(css = "button[class$='btn_inventory ']")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "button[id^='remove']")
    private List<WebElement> removeButtons;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        return isElementDisplayed(pageTitle) && getElementText(pageTitle).equals("Products");
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    public void addProductToCart(int index) {
//        if (index >= 0 && index < addToCartButtons.size()) {
//            clickElement(addToCartButtons.get(index));
//        }
        clickElement(addToCartButtons.get(index));
    }

    public void addProductToCartByName(String productName) {
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equalsIgnoreCase(productName)) {
                clickElement(addToCartButtons.get(i));
                break;
            }
        }
    }

    public void removeProductFromCart(int index) {
        if (index >= 0 && index < removeButtons.size()) {
            clickElement(removeButtons.get(index));
        }
    }

    public CartPage clickCartIcon() {
        clickElement(cartIcon);
        return new CartPage(driver);
    }

    public String getCartBadgeCount() {
        if (isElementDisplayed(cartBadge)) {
            return getElementText(cartBadge);
        }
        return "0";
    }

    public void clickMenuButton() {
        clickElement(menuButton);
        waitHelper.waitForElementToBeVisible(logoutLink);
    }

    public LoginPage logout() {
        clickMenuButton();
        clickElement(logoutLink);
        return new LoginPage(driver);
    }

    public boolean isCartBadgeDisplayed() {
        return isElementDisplayed(cartBadge);
    }
}
