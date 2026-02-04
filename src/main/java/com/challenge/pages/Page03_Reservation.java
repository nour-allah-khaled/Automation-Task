package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.WaitManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Page03_Reservation {
    private final GUIFactory driver;
    private final WaitManager wait;
    private final By Reservationbtn = By.xpath("//button[text()=' New Reservation ']");
    private final By AddReservationIndicator= By.xpath("//h2[text()=' New reservation ']");
    public Page03_Reservation(GUIFactory driver) {
        this.driver = driver;
        this.wait = new WaitManager(GUIFactory.getDriver());
    }
    @Step("Click on New Reservation button")
    public Page03_Reservation ClickonNewReservation(){
        driver.getElementAction().clicking(Reservationbtn);
        return this;
    }
    @Step("Verify Navigated to Add Reservation page successfully")
    public Page04_AddReservation VerifyAddReservationPage(String expected){
        wait.waitForElementVisible(AddReservationIndicator);
        String actual= driver.getBrowserAction().getCurrentUrl();
        driver.getSoftAssertion().assertEquals(actual,expected,"Failed to reach Add Reservation page");
        return new Page04_AddReservation(driver);
    }
}
