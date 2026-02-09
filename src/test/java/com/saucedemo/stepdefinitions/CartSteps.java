package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class CartSteps {
    private WebDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public CartSteps() {
        this.driver = DriverManager.getDriver();
        this.cartPage = new CartPage(driver);
    }

    @Given("I have added products to the cart")
    public void iHaveAddedProductsToTheCart() {
        productsPage = new ProductsPage(driver);
        productsPage.addProductToCart(0);
        productsPage.addProductToCart(1);
    }

    @Given("I am on the cart page")
    public void iAmOnTheCartPage() {
        productsPage = new ProductsPage(driver);
        cartPage = productsPage.clickCartIcon();
        Assert.assertTrue("Cart page is not displayed", cartPage.isCartPageDisplayed());
    }

    @Then("I should see the cart page title {string}")
    public void iShouldSeeTheCartPageTitle(String expectedTitle) {
        String actualTitle = cartPage.getPageTitle();
        Assert.assertEquals("Cart page title does not match", expectedTitle, actualTitle);
    }

    @Then("I should see all added items in the cart")
    public void iShouldSeeAllAddedItemsInTheCart() {
        int itemCount = cartPage.getCartItemCount();
        Assert.assertTrue("Cart should contain items", itemCount > 0);
    }

    @Then("I should see item names and prices")
    public void iShouldSeeItemNamesAndPrices() {
        String itemName = cartPage.getItemName(0);
        String itemPrice = cartPage.getItemPrice(0);
        Assert.assertFalse("Item name should not be empty", itemName.isEmpty());
        Assert.assertFalse("Item price should not be empty", itemPrice.isEmpty());
    }

    @Given("I have {string} items in the cart")
    public void iHaveItemsInTheCart(String count) {
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals("Cart item count does not match", Integer.parseInt(count), itemCount);
    }

    @When("I remove the first item from the cart")
    public void iRemoveTheFirstItemFromTheCart() {
        cartPage.removeItem(0);
    }

    @Then("the cart should contain {string} item")
    @Then("the cart should contain {string} items")
    public void theCartShouldContainItem(String expectedCount) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int actualCount = cartPage.getCartItemCount();
        Assert.assertEquals("Cart item count does not match", Integer.parseInt(expectedCount), actualCount);
    }

    @When("I click on continue shopping button")
    public void iClickOnContinueShoppingButton() {
        productsPage = cartPage.clickContinueShopping();
    }

    @When("I click on the checkout button")
    public void iClickOnTheCheckoutButton() {
        checkoutPage = cartPage.clickCheckout();
    }

    @Then("I should be on the checkout information page")
    public void iShouldBeRedirectedToTheCheckoutInformationPage() {
        checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue("Checkout page is not displayed", checkoutPage.isCheckoutPageDisplayed());
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedTitle) {
        String actualTitle = checkoutPage.getPageTitle();
        Assert.assertTrue("Page title does not contain expected text",
                actualTitle.contains(expectedTitle));
    }

    @When("I remove all items from the cart")
    public void iRemoveAllItemsFromTheCart() {
        int itemCount = cartPage.getCartItemCount();
        for (int i = itemCount - 1; i >= 0; i--) {
            cartPage.removeItem(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals("Cart should be empty", 0, itemCount);
    }

    @Then("the checkout button should still be visible")
    public void theCheckoutButtonShouldStillBeVisible() {
        Assert.assertTrue("Checkout button should be visible", cartPage.isCheckoutButtonDisplayed());
    }

    @Given("I have removed all items from the cart")
    public void iHaveRemovedAllItemsFromTheCart() {
        iRemoveAllItemsFromTheCart();
    }

    @Then("I should not see any cart items")
    public void iShouldNotSeeAnyCartItems() {
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals("Cart should not contain any items", 0, itemCount);
    }

    @Then("I should be redirected to the cart page")
    @Then("I should be on the cart page")
    public void iShouldBeOnTheCartPage() {
        cartPage = new CartPage(driver);
        Assert.assertTrue("Cart page is not displayed", cartPage.isCartPageDisplayed());
    }

    @And("I should see {string} items in the cart")
    public void iShouldSeeItemsInTheCart(String expectedCount) {
        int actualCount = cartPage.getCartItemCount();
        Assert.assertEquals("Cart item count does not match",
                Integer.parseInt(expectedCount), actualCount);
    }
}
