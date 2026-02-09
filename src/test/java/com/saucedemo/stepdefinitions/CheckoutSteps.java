package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    private ProductsPage productsPage;

    public CheckoutSteps() {
        this.driver = DriverManager.getDriver();
        this.checkoutPage = new CheckoutPage(driver);
    }

    @Given("I am on the checkout information page")
    public void iAmOnTheCheckoutInformationPage() {
        Assert.assertTrue("Checkout page is not displayed", checkoutPage.isCheckoutPageDisplayed());
    }

    @When("I enter first name {string}")
    public void iEnterFirstName(String firstName) {
        checkoutPage.enterFirstName(firstName);
    }

    @When("I enter last name {string}")
    public void iEnterLastName(String lastName) {
        checkoutPage.enterLastName(lastName);
    }

    @When("I enter postal code {string}")
    public void iEnterPostalCode(String postalCode) {
        checkoutPage.enterPostalCode(postalCode);
    }

    @When("I click continue button")
    public void iClickContinueButton() {
        checkoutOverviewPage = checkoutPage.clickContinue();
    }

    @When("I fill checkout information with {string}, {string}, and {string}")
    public void iFillCheckoutInformationWith(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInformation(firstName, lastName, postalCode);
    }

    @Then("I should be on the checkout overview page")
    public void iShouldBeOnTheCheckoutOverviewPage() {
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue("Checkout overview page is not displayed",
                checkoutOverviewPage.isCheckoutOverviewPageDisplayed());
    }

    @Then("I should see order summary with items and prices")
    public void iShouldSeeOrderSummaryWithItemsAndPrices() {
        int itemCount = checkoutOverviewPage.getItemCount();
        Assert.assertTrue("Order summary should contain items", itemCount > 0);

        String subtotal = checkoutOverviewPage.getSubtotal();
        String tax = checkoutOverviewPage.getTax();
        String total = checkoutOverviewPage.getTotal();

        Assert.assertFalse("Subtotal should not be empty", subtotal.isEmpty());
        Assert.assertFalse("Tax should not be empty", tax.isEmpty());
        Assert.assertFalse("Total should not be empty", total.isEmpty());
    }

    @When("I click cancel button")
    public void iClickCancelButton() {
        checkoutPage.clickCancel();
    }

    @When("I verify the order details")
    public void iVerifyTheOrderDetails() {
        Assert.assertTrue("Overview page should be displayed",
                checkoutOverviewPage.isCheckoutOverviewPageDisplayed());
    }

    @When("I click finish button")
    public void iClickFinishButton() {
        checkoutCompletePage = checkoutOverviewPage.clickFinish();
    }

    @Then("I should see the order confirmation page")
    public void iShouldSeeTheOrderConfirmationPage() {
        checkoutCompletePage = new CheckoutCompletePage(driver);
        Assert.assertTrue("Checkout complete page is not displayed",
                checkoutCompletePage.isCheckoutCompletePageDisplayed());
    }

    @Then("I should see {string} message")
    public void iShouldSeeMessage(String expectedMessage) {
        String actualMessage = checkoutCompletePage.getCompleteHeader();
        Assert.assertTrue("Success message does not match. Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }

    @Then("the success image should be displayed")
    public void theSuccessImageShouldBeDisplayed() {
        Assert.assertTrue("Success image is not displayed",
                checkoutCompletePage.isSuccessImageDisplayed());
    }

    @Then("I should see subtotal amount")
    public void iShouldSeeSubtotalAmount() {
        String subtotal = checkoutOverviewPage.getSubtotal();
        Assert.assertFalse("Subtotal should be displayed", subtotal.isEmpty());
        Assert.assertTrue("Subtotal should contain 'Item total'", subtotal.contains("Item total"));
    }

    @Then("I should see tax amount")
    public void iShouldSeeTaxAmount() {
        String tax = checkoutOverviewPage.getTax();
        Assert.assertFalse("Tax should be displayed", tax.isEmpty());
        Assert.assertTrue("Tax should contain 'Tax'", tax.contains("Tax"));
    }

    @Then("I should see total amount")
    public void iShouldSeeTotalAmount() {
        String total = checkoutOverviewPage.getTotal();
        Assert.assertFalse("Total should be displayed", total.isEmpty());
        Assert.assertTrue("Total should contain 'Total'", total.contains("Total"));
    }

    @Given("I am on the checkout overview page with valid information")
    public void iAmOnTheCheckoutOverviewPageWithValidInformation() {
        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutOverviewPage = checkoutPage.clickContinue();
        Assert.assertTrue("Checkout overview page is not displayed",
                checkoutOverviewPage.isCheckoutOverviewPageDisplayed());
    }

    @Then("the complete header should contain {string}")
    public void theCompleteHeaderShouldContain(String expectedText) {
        String actualHeader = checkoutCompletePage.getCompleteHeader();
        Assert.assertTrue("Complete header does not contain expected text. Actual: " + actualHeader,
                actualHeader.contains(expectedText));
    }

    @Then("I should see the back to products button")
    public void iShouldSeeTheBackToProductsButton() {
        Assert.assertTrue("Checkout complete page should be displayed",
                checkoutCompletePage.isCheckoutCompletePageDisplayed());
    }

    @And("I should see {string} items in the order summary")
    public void iShouldSeeItemsInTheOrderSummary(String expectedCount) {
        int actualCount = checkoutOverviewPage.getItemCount();
        Assert.assertEquals("Order summary item count does not match",
                Integer.parseInt(expectedCount), actualCount);
    }

    @When("I click back to products button")
    public void iClickBackToProductsButton() {
        productsPage = checkoutCompletePage.clickBackToProducts();
    }
}
