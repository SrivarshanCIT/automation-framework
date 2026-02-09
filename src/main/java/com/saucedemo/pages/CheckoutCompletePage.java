package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(className = "pony_express")
    private WebElement successImage;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutCompletePageDisplayed() {
        return isElementDisplayed(pageTitle) && getElementText(pageTitle).contains("Checkout: Complete!");
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    public String getCompleteHeader() {
        return getElementText(completeHeader);
    }

    public String getCompleteText() {
        return getElementText(completeText);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(completeHeader) &&
               getCompleteHeader().contains("Thank you for your order!");
    }

    public ProductsPage clickBackToProducts() {
        clickElement(backToProductsButton);
        return new ProductsPage(driver);
    }

    public boolean isSuccessImageDisplayed() {
        return isElementDisplayed(successImage);
    }
}
