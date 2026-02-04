package com.challenge.utils;

import com.challenge.datareader.PropertyReader;
import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;

public class WaitManager {
    private final WebDriver driver;
    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("Default_Wait"))))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(Exceptionn());
    }
    public FluentWait<WebDriver> fluentWaitForCapatch() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("Capatcha_Wait"))))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(Exceptionn());
    }
    public void waitForCaptcha(By locator, String expectedText) {
        LogsManager.info("Waiting for captcha to be verified manually...");
        try {
            fluentWaitForCapatch().until(d -> {
                try {
                    WebElement element = d.findElement(locator);
                    return element.getText().contains(expectedText);
                } catch (Exception e) {
                    return false;
                }
            });
            LogsManager.info("Captcha verified successfully!");
        } catch (org.openqa.selenium.TimeoutException e) {
            LogsManager.info("Captcha wait time expired. Continuing with test execution...");
        }
    }
    public void waitForElementVisible(By locator) {
        LogsManager.info("Waiting for element to be visible: " + locator);
        fluentWait().until(driver -> {
            try {
                return driver.findElements(locator).stream().anyMatch(WebElement::isDisplayed);
            } catch (Exception e) {
                return false;
            }
        });
        LogsManager.info("Element is now visible: " + locator);
    }
    public boolean waitForElementNotVisible(By locator) {
        LogsManager.info("Waiting for element to be invisible or removed: " + locator);
        try {
            WebElement badge = driver.findElement(locator);
            String text = badge.getText();
            return text == null || text.trim().isEmpty() || text.trim().equals("0");
        } catch (Exception e) {
            return true;
        }
    }
    public void waitForElementClickable(By locator) {
        LogsManager.info("Waiting for element to be clickable: " + locator);
        fluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                return element.isDisplayed() && element.isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
        LogsManager.info("Element is now clickable: " + locator);
    }
    private ArrayList<Class<? extends Exception>> Exceptionn() {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(org.openqa.selenium.NoSuchElementException.class);
        exceptions.add(org.openqa.selenium.StaleElementReferenceException.class);
        exceptions.add(org.openqa.selenium.ElementNotInteractableException.class);
        exceptions.add(org.openqa.selenium.ElementClickInterceptedException.class);
        return exceptions;
    }
}
