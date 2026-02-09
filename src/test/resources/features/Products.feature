@Products @Regression
Feature: Products Page Functionality
  As a logged-in user
  I want to view and interact with products
  So that I can select items to purchase

  Background:
    Given I am logged in as a standard user
    And I am on the products page

  @Smoke @Positive
  Scenario: View products on the products page
    Then I should see all products displayed
    And I should see product names and prices
    And I should see add to cart buttons

  @Positive
  Scenario: Add a product to cart
    When I add the first product to the cart
    Then the cart badge should display "1"
    And the add to cart button should change to remove button

  @Positive
  Scenario: Add multiple products to cart
    When I add "3" products to the cart
    Then the cart badge should display "3"
    And the cart icon should show the correct count

  @Positive
  Scenario: Remove a product from cart
    Given I have added a product to the cart
    When I click the remove button
    Then the cart badge should not be displayed
    And the remove button should change back to add to cart

  @Positive
  Scenario: Add product by name
    When I add product "Sauce Labs Backpack" to the cart
    Then the cart badge should display "1"

  @Positive
  Scenario: Navigate to cart page
    Given I have added "2" products to the cart
    When I click on the cart icon
    Then I should be redirected to the cart page
    And I should see "2" items in the cart

  @Positive
  Scenario: Logout from products page
    When I click on the menu button
    And I click on the logout link
    Then I should be redirected to the login page
