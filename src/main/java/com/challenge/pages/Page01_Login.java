package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.WaitManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Page01_Login {
    private final GUIFactory driver;
    private final WaitManager wait ;
    private final By UsernameField= By.id("usern");
    private final By PasswordField= By.id("pass");
    private final By AccessCodeField= By.id("acc");
    private final By LoginButton= By.xpath("//button[contains(text(),'Login')]");
    private final By Capatcha=By.cssSelector("#recaptcha-accessible-status");
    private final By dashboardIndicator = By.xpath("//div[contains(text(),'User Verification')]");
    public Page01_Login(GUIFactory driver) {
        this.driver = driver;
        this.wait = new WaitManager(GUIFactory.getDriver());
    }
    @Step("Enter Username: {username}")
    public Page01_Login EnterUsername(String username){
        driver.getElementAction().sendkeys(UsernameField, username);
        return this;
    }
    @Step("Enter Password: {password}")
    public Page01_Login EnterPassword(String password){
        driver.getElementAction().sendkeys(PasswordField, password);
        return this;
    }
    @Step("Enter Access Code: {accesscode}")
    public Page01_Login EnterAccessCode(String accesscode) {
        driver.getElementAction().sendkeys(AccessCodeField, accesscode);
        return this;
    }
    @Step("Wait for Captcha to be verified")
    public Page01_Login waitForCaptchaToBeVerified() {
        wait.waitForCaptcha(Capatcha, "You are verified");
        return this;
    }
    @Step("Click Login Button")
    public Page01_Login ClickLoginbtn(){
        driver.getElementAction().clicking(LoginButton);
        return this;
    }
    @Step("Verify Successful Login to Dashboard page")
    public Page02_Dashboard VerifySuccessfulLogin(String expected){
        wait.waitForElementVisible(dashboardIndicator);
        String actual= driver.getBrowserAction().getCurrentUrl();
        driver.getSoftAssertion().assertEquals(actual,expected,"Failed to Login and reach Dashboard page");
        return new Page02_Dashboard(driver);
    }
}
