@Cart @Regression
Feature: Shopping Cart Functionality
  As a logged-in user with items in cart
  I want to manage my shopping cart
  So that I can review and modify my selections before checkout

  Background:
    Given I am logged in as a standard user
    And I have added products to the cart
    And I am on the cart page

  @Smoke @Positive
  Scenario: View cart page with items
    Then I should see the cart page title "Your Cart"
    And I should see all added items in the cart
    And I should see item names and prices

  @Positive
  Scenario: Remove item from cart
    Given I have "2" items in the cart
    When I remove the first item from the cart
    Then the cart should contain "1" item

  @Positive
  Scenario: Continue shopping from cart
    When I click on continue shopping button
    Then I should be redirected to the products page

  @Positive
  Scenario: Proceed to checkout from cart
    When I click on the checkout button
    Then I should be redirected to the checkout information page
    And the page title should contain "Checkout"

  @Positive
  Scenario: Empty cart by removing all items
    When I remove all items from the cart
    Then the cart should be empty
    And the checkout button should still be visible

  @Negative
  Scenario: View cart with no items
    Given I have removed all items from the cart
    Then the cart should be empty
    And I should not see any cart items
