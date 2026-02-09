package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    public LoginSteps() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("I am on the Sauce Demo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        Assert.assertTrue("Login page is not displayed", loginPage.isLoginPageDisplayed());
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        productsPage = loginPage.login(username, password);
    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToTheProductsPage() {
        productsPage = new ProductsPage(driver);
        Assert.assertTrue("Products page is not displayed", productsPage.isProductsPageDisplayed());
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        productsPage = new ProductsPage(driver);
        String actualTitle = productsPage.getPageTitle();
        Assert.assertEquals("Page title does not match", expectedTitle, actualTitle);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
    }

    @Then("the error message should contain {string}")
    public void theErrorMessageShouldContain(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue("Error message does not contain expected text. Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }

    @Then("I should see the products inventory")
    public void iShouldSeeTheProductsInventory() {
        productsPage = new ProductsPage(driver);
        int productCount = productsPage.getProductCount();
        Assert.assertTrue("No products are displayed", productCount > 0);
    }

    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsAStandardUser() {
        loginPage = new LoginPage(driver);
        productsPage = loginPage.login(ConfigReader.getStandardUser(), ConfigReader.getPassword());
        Assert.assertTrue("Login was not successful", productsPage.isProductsPageDisplayed());
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue("Login page is not displayed", loginPage.isLoginPageDisplayed());
    }
}
