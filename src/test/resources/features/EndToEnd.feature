@E2E @Smoke
Feature: End-to-End User Journey
  As a user
  I want to complete a full shopping journey
  So that I can successfully purchase products

  @Critical
  Scenario: Complete shopping journey from login to order completion
    Given I am on the Sauce Demo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the products page

    When I add product "Sauce Labs Backpack" to the cart
    And I add product "Sauce Labs Bike Light" to the cart
    Then the cart badge should display "2"

    When I click on the cart icon
    Then I should be on the cart page
    And the cart should contain "2" items

    When I click on the checkout button
    Then I should be on the checkout information page

    When I fill checkout information with "John", "Doe", and "12345"
    And I click continue button
    Then I should be on the checkout overview page
    And I should see "2" items in the order summary

    When I click finish button
    Then I should see the order confirmation page
    And the complete header should contain "Thank you for your order!"

    When I click back to products button
    Then I should be redirected to the products page

  @Critical
  Scenario: Shopping journey with product removal
    Given I am on the Sauce Demo login page
    When I login with username "standard_user" and password "secret_sauce"
    And I add "3" products to the cart
    Then the cart badge should display "3"

    When I click on the cart icon
    And I remove the first item from the cart
    Then the cart should contain "2" items

    When I click on the checkout button
    And I fill checkout information with "Jane", "Smith", and "67890"
    And I click continue button
    And I click finish button
    Then I should see the order confirmation page

  @Critical @Regression
  Scenario: Multiple products purchase with validation
    Given I am logged in as a standard user
    And I am on the products page
    When I add product "Sauce Labs Backpack" to the cart
    And I add product "Sauce Labs Bolt T-Shirt" to the cart
    And I add product "Sauce Labs Onesie" to the cart
    Then the cart badge should display "3"

    When I navigate to cart
    Then all selected products should be visible in cart

    When I proceed to checkout
    And I complete checkout with name "Michael Johnson" and zip "54321"
    Then order should be placed successfully
    And confirmation message should be displayed
