package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageDisplayed() {
        return isElementDisplayed(pageTitle) && getElementText(pageTitle).contains("Checkout");
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    public void enterFirstName(String firstName) {
        enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        enterText(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        enterText(postalCodeField, postalCode);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public CheckoutOverviewPage clickContinue() {
        clickElement(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public CartPage clickCancel() {
        clickElement(cancelButton);
        return new CartPage(driver);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }
}
