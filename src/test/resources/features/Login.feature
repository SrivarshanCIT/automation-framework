@Login @Regression
Feature: Login Functionality
  As a user of Sauce Demo
  I want to be able to login to the application
  So that I can access the product catalog

  Background:
    Given I am on the Sauce Demo login page

  @Smoke @Positive
  Scenario: Successful login with valid credentials
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the products page
    And the page title should be "Products"

  @Positive
  Scenario Outline: Login with different valid users
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the products page
    And I should see the products inventory

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | problem_user            | secret_sauce |

  @Negative
  Scenario: Login with invalid username
    When I enter username "invalid_user"
    And I enter password "secret_sauce"
    And I click on the login button
    Then I should see an error message
    And the error message should contain "Epic sadface"

  @Negative
  Scenario: Login with invalid password
    When I enter username "standard_user"
    And I enter password "invalid_password"
    And I click on the login button
    Then I should see an error message
    And the error message should contain "Epic sadface"

  @Negative
  Scenario: Login with empty credentials
    When I click on the login button
    Then I should see an error message
    And the error message should contain "Username is required"

  @Negative
  Scenario: Login with locked out user
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message
    And the error message should contain "locked out"

  @Negative
  Scenario: Login with empty password
    When I enter username "standard_user"
    And I click on the login button
    Then I should see an error message
    And the error message should contain "Password is required"
