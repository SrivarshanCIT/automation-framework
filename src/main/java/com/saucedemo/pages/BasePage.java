package com.saucedemo.pages;

import com.saucedemo.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected WaitHelper waitHelper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element) {
        waitHelper.waitForElementToBeClickable(element);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        waitHelper.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getElementText(WebElement element) {
        waitHelper.waitForElementToBeVisible(element);
        return element.getText();
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitHelper.waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
