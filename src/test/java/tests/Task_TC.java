package tests;

import com.challenge.pages.Page01_Login;
import com.challenge.pages.Page02_Dashboard;
import com.challenge.pages.Page03_Reservation;
import com.challenge.pages.Page04_AddReservation;
import io.qameta.allure.*;
import org.testng.annotations.Test;
@Epic("Reservation Creation")
@Feature("Add New Reservation")
@Severity(SeverityLevel.CRITICAL)
@Owner("Nour Allah Khaled")
public class Task_TC extends BaseClass {
    @Test
    @Story("Create reservation with valid unit and guest")
    @Description("Test E2E Scenario from Login to create a New Reservation with valid unit and guest")
    public void CreateNewReservation() {
       new Page01_Login(driver).EnterUsername(getUsername()).EnterPassword(getPassword())
               .EnterAccessCode(getAccessCode())
               .waitForCaptchaToBeVerified()
               .ClickLoginbtn().VerifySuccessfulLogin(ExpectedDashboardURL);

       new Page02_Dashboard(driver).ClosePopups().ClickonReservation()
               .VerifyReservationPage(ExpectedReservationURL);

       new Page03_Reservation(driver).ClickonNewReservation().VerifyAddReservationPage(ExpectedAddReservationURL);

      new Page04_AddReservation(driver).ClickonVisitPurposeField(getOption())
              .ClickonReservationSourceField().ClickonSelectUnitlink().VerifySelectUnitPopup(getText())
              .ClickonLoadMorebtn().ClickonSelectunit()
              .ClickonConfirmbtn().VerifyUnitNumberSelectedSuccessfully(getUnitNumber())
              .ClickonSelectguestbtn().EnterGuestID(getGuestID())
              .ClickonSearchbtn().HoverOverGuestName()
              .ClickonConfirmGuestbtn().VerifyReservationCreatedSuccessfully(getIdnumber());
    }
}
