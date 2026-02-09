package com.saucedemo.stepdefinitions;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverManager;
import com.saucedemo.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting Scenario: " + scenario.getName());
        DriverManager.setDriver(ConfigReader.getBrowser());
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getBaseUrl());
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverManager.getDriver();

        if (scenario.isFailed() && ConfigReader.isScreenshotOnFailure()) {

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Failure - " + scenario.getName(),
                    new ByteArrayInputStream(screenshot)
            );
        }

        Allure.addAttachment(
                "Scenario Status",
                scenario.getStatus().toString()
        );

        DriverManager.quitDriver();
    }
}
