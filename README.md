# Sauce Demo E2E Automation Framework

## Overview
This is a comprehensive end-to-end UI automation framework for testing the Sauce Demo e-commerce web application. The framework is built using Selenium WebDriver with Java and Cucumber BDD, following industry best practices.

## Technology Stack
- **Java 11** - Programming language
- **Selenium WebDriver 4.40.0** - Web automation tool
- **Cucumber 7.14.0** - BDD framework
- **JUnit 5** - Testing framework
- **Maven** - Build and dependency management
- **WebDriverManager** - Automatic driver management
- **Log4j2** - Logging framework

## Project Structure
```
automation-framework/
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── pages/              # Page Object Model classes
│   │   │   ├── BasePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── CartPage.java
│   │   │   ├── CheckoutPage.java
│   │   │   ├── CheckoutOverviewPage.java
│   │   │   └── CheckoutCompletePage.java
│   │   └── utils/              # Utility classes
│   │       ├── ConfigReader.java
│   │       ├── DriverManager.java
│   │       ├── ScreenshotUtil.java
│   │       └── WaitHelper.java
│   └── test/
│       ├── java/com/saucedemo/
│       │   ├── runners/        # Test runner classes
│       │   │   ├── TestRunner.java
│       │   │   ├── SmokeTestRunner.java
│       │   │   ├── RegressionTestRunner.java
│       │   │   └── E2ETestRunner.java
│       │   └── stepdefinitions/  # Cucumber step definitions
│       │       ├── Hooks.java
│       │       ├── LoginSteps.java
│       │       ├── ProductsSteps.java
│       │       ├── CartSteps.java
│       │       ├── CheckoutSteps.java
│       │       └── EndToEndSteps.java
│       └── resources/
│           ├── features/       # Cucumber feature files
│           │   ├── Login.feature
│           │   ├── Products.feature
│           │   ├── Cart.feature
│           │   ├── Checkout.feature
│           │   └── EndToEnd.feature
│           ├── config.properties
│           └── log4j2.xml
├── pom.xml
├── Jenkinsfile
└── README.md
```

## Framework Features

### 1. Page Object Model (POM)
- Implements POM design pattern for better maintainability
- Separate page classes for each application page
- Reusable methods with clear naming conventions
- BasePage class with common functionality

### 2. BDD with Cucumber
- Gherkin syntax (Given-When-Then) for readable test scenarios
- Feature files organized by functionality
- Parameterized scenarios using Examples
- Comprehensive test coverage with positive and negative scenarios

### 3. Cross-Browser Support
- Chrome, Firefox, and Edge browser support
- Configurable browser selection via properties file
- Headless mode support for CI/CD execution
- Automatic driver management using WebDriverManager

### 4. Explicit Waits
- Custom WaitHelper class for handling dynamic elements
- WebDriverWait for element visibility, clickability
- Configurable wait timeouts

### 5. Reporting
- Allure Report
- JSON and XML reports for CI/CD integration
- Detailed logging with Log4j2

### 6. CI/CD Integration
- Jenkins pipeline configuration (Jenkinsfile)
- Parameterized builds for test suite selection
- Automated report generation and archiving



## Test Scenarios Covered

### Login Functionality
- ✅ Successful login with valid credentials
- ✅ Login with different user types
- ✅ Login with invalid credentials
- ✅ Login with empty fields
- ✅ Locked user login attempt

### Products Page
- ✅ View all products
- ✅ Add single/multiple products to cart
- ✅ Remove products from cart
- ✅ Navigate to cart
- ✅ Logout functionality

### Shopping Cart
- ✅ View cart items
- ✅ Remove items from cart
- ✅ Continue shopping
- ✅ Proceed to checkout
- ✅ Empty cart validation

### Checkout Process
- ✅ Complete checkout with valid information
- ✅ Checkout validation (empty fields)
- ✅ Order summary verification
- ✅ Order completion
- ✅ Success message validation

### End-to-End Flows
- ✅ Complete shopping journey (login to order completion)
- ✅ Multiple products purchase flow
- ✅ Cart modifications during checkout


## Best Practices Implemented

1. **Separation of Concerns**
   - Page objects separate from test logic
   - Utility classes for common operations
   - Configuration externalized in properties file

2. **Reusability**
   - Common methods in BasePage
   - Reusable step definitions
   - Parameterized test scenarios

3. **Maintainability**
   - Clear naming conventions
   - Well-organized project structure
   - Comprehensive documentation

4. **Scalability**
   - Easy to add new pages and tests
   - Modular design
   - Support for parallel execution

5. **Reliability**
   - Explicit waits for dynamic elements
   - Screenshot capture on failures
   - Proper exception handling


Run commands Different Test Suites

# All tests
mvn test

# Smoke tests only
mvn test -Dtest=SmokeTestRunner

# Regression tests
mvn test -Dtest=RegressionTestRunner

# E2E tests
mvn test -Dtest=E2ETestRunner

Run with Different Browsers

mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge