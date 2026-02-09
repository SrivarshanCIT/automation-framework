package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.*;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class EndToEndSteps {
    private WebDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    public EndToEndSteps() {
        this.driver = DriverManager.getDriver();
    }

    @When("I navigate to cart")
    public void iNavigateToCart() {
        productsPage = new ProductsPage(driver);
        cartPage = productsPage.clickCartIcon();
        Assert.assertTrue("Cart page should be displayed", cartPage.isCartPageDisplayed());
    }

    @Then("all selected products should be visible in cart")
    public void allSelectedProductsShouldBeVisibleInCart() {
        int itemCount = cartPage.getCartItemCount();
        Assert.assertTrue("Cart should contain selected products", itemCount > 0);
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        checkoutPage = cartPage.clickCheckout();
        Assert.assertTrue("Checkout page should be displayed", checkoutPage.isCheckoutPageDisplayed());
    }

    @When("I complete checkout with name {string} and zip {string}")
    public void iCompleteCheckoutWithNameAndZip(String fullName, String zipCode) {
        String[] nameParts = fullName.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "User";

        checkoutPage.fillCheckoutInformation(firstName, lastName, zipCode);
        checkoutOverviewPage = checkoutPage.clickContinue();
        Assert.assertTrue("Checkout overview page should be displayed",
                checkoutOverviewPage.isCheckoutOverviewPageDisplayed());

        checkoutCompletePage = checkoutOverviewPage.clickFinish();
    }

    @Then("order should be placed successfully")
    public void orderShouldBePlacedSuccessfully() {
        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue("Checkout complete page should be displayed",
                checkoutCompletePage.isCheckoutCompletePageDisplayed());
    }

    @Then("confirmation message should be displayed")
    public void confirmationMessageShouldBeDisplayed() {
        Assert.assertTrue("Success message should be displayed",
                checkoutCompletePage.isSuccessMessageDisplayed());
    }
}
