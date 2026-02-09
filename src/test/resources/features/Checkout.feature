@Checkout @Regression
Feature: Checkout Process
  As a logged-in user with items in cart
  I want to complete the checkout process
  So that I can purchase the items

  Background:
    Given I am logged in as a standard user
    And I have added products to the cart
    And I am on the checkout information page

  @Smoke @Positive
  Scenario: Complete checkout with valid information
    When I enter first name "John"
    And I enter last name "Doe"
    And I enter postal code "12345"
    And I click continue button
    Then I should be on the checkout overview page
    And I should see order summary with items and prices

  @Positive
  Scenario Outline: Checkout with different valid information
    When I fill checkout information with "<firstName>", "<lastName>", and "<postalCode>"
    And I click continue button
    Then I should be on the checkout overview page

    Examples:
      | firstName | lastName | postalCode |
      | John      | Doe      | 12345      |
      | Jane      | Smith    | 67890      |
      | Michael   | Johnson  | ABCDE      |

  @Negative
  Scenario: Checkout with empty first name
    When I enter last name "Doe"
    And I enter postal code "12345"
    And I click continue button
    Then I should see an error message
    And the error message should contain "First Name is required"

  @Negative
  Scenario: Checkout with empty last name
    When I enter first name "John"
    And I enter postal code "12345"
    And I click continue button
    Then I should see an error message
    And the error message should contain "Last Name is required"

  @Negative
  Scenario: Checkout with empty postal code
    When I enter first name "John"
    And I enter last name "Doe"
    And I click continue button
    Then I should see an error message
    And the error message should contain "Postal Code is required"

  @Positive
  Scenario: Cancel checkout and return to cart
    When I click cancel button
    Then I should be redirected to the cart page

  @Smoke @E2E
  Scenario: Complete end-to-end purchase flow
    When I fill checkout information with "John", "Doe", and "12345"
    And I click continue button
    Then I should be on the checkout overview page
    When I verify the order details
    And I click finish button
    Then I should see the order confirmation page
    And I should see "Thank you for your order!" message
    And the success image should be displayed

  @Positive
  Scenario: Verify order summary on overview page
    When I fill checkout information with "John", "Doe", and "12345"
    And I click continue button
    Then I should be on the checkout overview page
    And I should see subtotal amount
    And I should see tax amount
    And I should see total amount

  @Positive
  Scenario: Complete order from overview page
    Given I am on the checkout overview page with valid information
    When I click finish button
    Then I should see the order confirmation page
    And the complete header should contain "Thank you for your order!"
    And I should see the back to products button
