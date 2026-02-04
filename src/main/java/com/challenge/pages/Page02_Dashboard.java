package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.WaitManager;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Page02_Dashboard {
    private final GUIFactory driver;
    private final WaitManager wait;
    private final By Laterbtn= By.xpath("//span[contains(text(),' Later')]");
    private final By Closebtn= By.xpath("//button[contains(text(),'Close')]");
    private final By Reservationbtn = By.xpath("//a[@ng-reflect-text='Reservations']");
    private final By reservationIndicator= By.xpath("//h2[contains(text(),'Reservations')]");
    public Page02_Dashboard(GUIFactory driver) {
        this.driver = driver;
        this.wait = new WaitManager(GUIFactory.getDriver());
    }
    @Step("Click on the Later and Close buttons to close popups")
    public Page02_Dashboard ClosePopups(){
        wait.waitForElementVisible(Laterbtn);
        driver.getElementAction().clicking(Laterbtn);
        try {
            wait.waitForElementVisible(Closebtn);
            driver.getElementAction().clicking(Closebtn);
        } catch (Exception e) {
            LogsManager.info("Close button not found, no popup to close.");
        }
        return this;
    }
    @Step("Click on Reservation button")
    public Page02_Dashboard ClickonReservation(){
        driver.getElementAction().clicking(Reservationbtn);
        return this;
    }
    @Step("Verify navigation to Reservation page")
    public Page03_Reservation VerifyReservationPage(String expected){
        wait.waitForElementVisible(reservationIndicator);
        String actual= driver.getBrowserAction().getCurrentUrl();
        driver.getSoftAssertion().assertEquals(actual,expected,"Failed to reach Reservations page");
        return new Page03_Reservation(driver);
    }
}
