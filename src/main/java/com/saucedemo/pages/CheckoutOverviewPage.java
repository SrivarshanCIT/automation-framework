package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutOverviewPageDisplayed() {
        return isElementDisplayed(pageTitle) && getElementText(pageTitle).contains("Checkout: Overview");
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public String getSubtotal() {
        return getElementText(subtotalLabel);
    }

    public String getTax() {
        return getElementText(taxLabel);
    }

    public String getTotal() {
        return getElementText(totalLabel);
    }

    public CheckoutCompletePage clickFinish() {
        clickElement(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public ProductsPage clickCancel() {
        clickElement(cancelButton);
        return new ProductsPage(driver);
    }

    public boolean isFinishButtonDisplayed() {
        return isElementDisplayed(finishButton);
    }
}
