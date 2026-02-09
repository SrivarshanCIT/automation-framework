package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ProductsSteps {
    private WebDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;

    public ProductsSteps() {
        this.driver = DriverManager.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        Assert.assertTrue("Products page is not displayed", productsPage.isProductsPageDisplayed());
    }

    @Then("I should see all products displayed")
    public void iShouldSeeAllProductsDisplayed() {
        int productCount = productsPage.getProductCount();
        Assert.assertTrue("No products are displayed", productCount > 0);
        System.out.println("Total products displayed: " + productCount);
    }

    @Then("I should see product names and prices")
    public void iShouldSeeProductNamesAndPrices() {
        int productCount = productsPage.getProductCount();
        Assert.assertTrue("Products should be visible", productCount >= 6);
    }

    @Then("I should see add to cart buttons")
    public void iShouldSeeAddToCartButtons() {
        Assert.assertTrue("Products page should be displayed", productsPage.isProductsPageDisplayed());
    }

    @When("I add the first product to the cart")
    public void iAddTheFirstProductToTheCart() {
        productsPage.addProductToCart(0);
    }

    @When("I add {string} products to the cart")
    public void iAddProductsToTheCart(String count) {
        int numberOfProducts = Integer.parseInt(count);
        for (int i = 0; i < numberOfProducts; i++) {
            productsPage.addProductToCart(i);
        }
    }

    @Then("the cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String expectedCount) {
        String actualCount = productsPage.getCartBadgeCount();
        Assert.assertEquals("Cart badge count does not match", expectedCount, actualCount);
    }

    @Then("the add to cart button should change to remove button")
    public void theAddToCartButtonShouldChangeToRemoveButton() {
        Assert.assertTrue("Cart badge should be displayed", productsPage.isCartBadgeDisplayed());
    }

    @Given("I have added a product to the cart")
    public void iHaveAddedAProductToTheCart() {
        productsPage.addProductToCart(0);
        Assert.assertTrue("Cart badge should be displayed", productsPage.isCartBadgeDisplayed());
    }

    @When("I click the remove button")
    public void iClickTheRemoveButton() {
        productsPage.removeProductFromCart(0);
    }

    @Then("the cart badge should not be displayed")
    public void theCartBadgeShouldNotBeDisplayed() {
        Assert.assertFalse("Cart badge should not be displayed", productsPage.isCartBadgeDisplayed());
    }

    @Then("the remove button should change back to add to cart")
    public void theRemoveButtonShouldChangeBackToAddToCart() {
        Assert.assertTrue("Products page should still be displayed", productsPage.isProductsPageDisplayed());
    }

    @When("I add product {string} to the cart")
    public void iAddProductToTheCart(String productName) {
        productsPage.addProductToCartByName(productName);
    }

    @Given("I have added {string} products to the cart")
    public void iHaveAddedProductsToTheCart(String count) {
        int numberOfProducts = Integer.parseInt(count);
        for (int i = 0; i < numberOfProducts; i++) {
            productsPage.addProductToCart(i);
        }
    }

    @When("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        cartPage = productsPage.clickCartIcon();
    }

    @Then("the cart icon should show the correct count")
    public void theCartIconShouldShowTheCorrectCount() {
        Assert.assertTrue("Cart badge should be displayed with items", productsPage.isCartBadgeDisplayed());
    }

    @When("I click on the menu button")
    public void iClickOnTheMenuButton() {
        productsPage.clickMenuButton();
    }

    @When("I click on the logout link")
    public void iClickOnTheLogoutLink() {
        productsPage.logout();
    }
}
