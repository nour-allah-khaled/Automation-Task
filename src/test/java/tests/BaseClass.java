package tests;

import com.challenge.datareader.JsonReader;
import com.challenge.datareader.PropertyReader;
import com.challenge.drivers.GUIFactory;
import io.qameta.allure.Epic;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    protected GUIFactory driver;
    protected JsonReader testData;
    public String ExpectedDashboardURL;
    public String ExpectedReservationURL;
    public String ExpectedAddReservationURL;
    public String URL;
    @BeforeClass
    public void  Setup(){
        URL = PropertyReader.getProperty("Nazeel_URL");
        testData = new JsonReader("Data");
        ExpectedDashboardURL = PropertyReader.getProperty("DashboardURL");
        ExpectedReservationURL = PropertyReader.getProperty("ReservationURL");
        ExpectedAddReservationURL = PropertyReader.getProperty("AddReservationURL");
    }
    @BeforeMethod
    public void BeforeTest(){
        driver = new GUIFactory();
        driver.getBrowserAction().maximize();
        driver.getBrowserAction().navigateTo(URL);
    }
    protected String getUsername(){
        return testData.getJsonData("Username");
    }
    protected String getPassword(){
        return testData.getJsonData("Password");
    }
    protected String getAccessCode(){
        return testData.getJsonData("AccessCode");
    }
    protected String getText(){
        return testData.getJsonData("Text");
    }
    protected String getOption()
    {
        return testData.getJsonData("Option");
    }
    protected String getGuestID(){
        return testData.getJsonData("Guest_ID");
    }
    protected String getUnitNumber(){
        return testData.getJsonData("Unit_num");
    }
    protected String getIdnumber(){
        return testData.getJsonData("ID_number");
    }
    @AfterMethod
    public void quit(){
        driver.quitDriver();
    }
}
