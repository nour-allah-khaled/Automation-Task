package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.WaitManager;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Page04_AddReservation {
    private final GUIFactory driver;
    private final WaitManager wait;
    private final By visitPurposeField = By.xpath(" (//span[@class='k-input'])[1]");
    private final By reservationSourceField = By.xpath(" (//span[@class='k-input'])[2]");
    private final By selectUnitlink = By.xpath("//div//span [contains(@class,'primary-link')]");
    private final By selectUnitPopup = By.xpath("//div[text()=' Select Unit(s)']");
    private final By loadMorebtn= By.xpath("//button[.=' Load More... ']");
    private final By selectunit = By.xpath("//p[@ng-reflect-text='17']");
    private final By confirmbtn= By.xpath("//span[.='Confirm']");
    private final By selectguestbtn = By.xpath("//span[.='Select guest now']");
    private final By guestId = By.xpath("//input[@placeholder='ID Number']");
    private final By searchbtn = By.xpath("//button[.='Search']");
    private final By hoveroverguest = By.xpath("//span[.='Test Test']");
    private final By ConfirmGuestbtn = By.xpath("//button[text()='Confirm']");
    private final By Unitnum = By.xpath("//td[text()=' 17 ']");
    private final By Idnumber = By.xpath("//div[text()=' 123456789 ']");
    public Page04_AddReservation(GUIFactory driver) {
        this.driver = driver;
        this.wait = new WaitManager(GUIFactory.getDriver());
    }
    @Step("Select Visit Purpose option: {option}")
    public Page04_AddReservation ClickonVisitPurposeField(String option){
        wait.waitForElementClickable(visitPurposeField);
        driver.getElementAction().clicking(visitPurposeField);
        By dropdownList = By.xpath("//ul[contains(@class,'k-list')]");
        wait.waitForElementVisible(dropdownList);
        By optionLocator = By.xpath("//ul[contains(@class,'k-list')]//li[contains(@class,'k-item') and normalize-space(text())='" + option + "']");
        wait.waitForElementClickable(optionLocator);
        driver.getElementAction().clicking(optionLocator);
        LogsManager.info("Selected Visit Purpose option: " + option);
        return this;
    }
    @Step("Select first option from Reservation Source dropdown")
    public Page04_AddReservation ClickonReservationSourceField(){
        wait.waitForElementClickable(reservationSourceField);
        driver.getElementAction().clicking(reservationSourceField);
        By dropdownList = By.xpath("//ul[contains(@class,'k-list')]");
        wait.waitForElementVisible(dropdownList);
        By firstOptionLocator = By.xpath("(//ul[contains(@class,'k-list')]//li[contains(@class,'k-item')])[1]");
        wait.waitForElementClickable(firstOptionLocator);
        driver.getElementAction().clicking(firstOptionLocator);
        LogsManager.info("Selected first Reservation Source option");
        return this;
    }
    @Step("Click on Select Unit link")
    public Page04_AddReservation ClickonSelectUnitlink(){
        wait.waitForElementVisible(selectUnitlink);
        driver.getElementAction().clicking(selectUnitlink);
        return this;
    }
    @Step("Click on Load More button")
    public Page04_AddReservation ClickonLoadMorebtn(){
        driver.getElementAction().clicking(loadMorebtn);
        return this;
    }
    @Step("Select valid unit number 17")
    public Page04_AddReservation ClickonSelectunit(){
        wait.waitForElementVisible(selectunit);
        driver.getElementAction().clicking(selectunit);
        return this;
    }
    @Step("Click on the Confirm button after selecting the valid unit")
    public Page04_AddReservation ClickonConfirmbtn(){
        wait.waitForElementClickable(confirmbtn);
        driver.getElementAction().clicking(confirmbtn);
        return this;
    }
    @Step("Click on the Select Guest Now button")
    public Page04_AddReservation ClickonSelectguestbtn(){
        wait.waitForElementClickable(selectguestbtn);
        driver.getElementAction().clicking(selectguestbtn);
        return this;
    }
    @Step("Enter Guest ID: {idNumber}")
    public Page04_AddReservation EnterGuestID(String idNumber){
        wait.waitForElementClickable(guestId);
        driver.getElementAction().sendkeys(guestId, idNumber);
        return this;
    }
    @Step("Click on Search button to find guest")
    public Page04_AddReservation ClickonSearchbtn(){
        wait.waitForElementClickable(searchbtn);
        driver.getElementAction().clicking(searchbtn);
        return this;
    }
    @Step("Hover over the guest name to select")
    public Page04_AddReservation HoverOverGuestName(){
        wait.waitForElementVisible(hoveroverguest);
        driver.getElementAction().clicking(hoveroverguest);
        return this;
    }
    @Step("Click on Confirm Guest button")
    public Page04_AddReservation ClickonConfirmGuestbtn(){
        wait.waitForElementClickable(ConfirmGuestbtn);
        driver.getElementAction().clicking(ConfirmGuestbtn);
        return this;
    }
    @Step("Verify the Select Unit popup is displayed")
    public Page04_AddReservation VerifySelectUnitPopup(String expected){
        wait.waitForElementVisible(selectUnitPopup);
        String actual= driver.getElementAction().getText(selectUnitPopup);
        driver.getSoftAssertion().assertEquals(actual,expected,"Failed to open Select Unit popup");
        return this;
    }
    @Step("Verify the valid Unit Number is selected successfully: {expected}")
    public Page04_AddReservation VerifyUnitNumberSelectedSuccessfully(String expected){
        wait.waitForElementVisible(Unitnum);
        String actual= driver.getElementAction().getText(Unitnum);
        driver.getSoftAssertion().assertEquals(actual,expected,"Failed to select Unit Number successfully");
        return this;
    }
    @Step("Verify Reservation is Created successfully with ID: {expected}")
    public void VerifyReservationCreatedSuccessfully(String expected){
        wait.waitForElementVisible(Idnumber);
        String actual= driver.getElementAction().getText(Idnumber);
        driver.getSoftAssertion().assertEquals(actual,expected,"Failed to add Reservation successfully");
    }
}
