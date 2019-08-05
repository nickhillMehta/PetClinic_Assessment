/**
 * Created by mehtanikhil on 04.08.2019.
 */

import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.Assertion;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class AddNewVisit_Test {
    WebDriver driver;
    Navigation_Links navigationLinks;
    AddandFind_Owners addandFindOwners;
    DisplaySearchResult displaySearchResult;
    Add_OwnerInformation addOwnerInfo;
    EditandDisplay_UserDetails editandDisplayUserDetails;
    AddPet addPet;
    DisplayandAdd_Visits displayandAddVisits;
    Veterinarians_List vetList;



    @Test(dataProvider = "data-provider", testName = "AddnewPetVisit_Test")
    public void AddnewVisitTest(String firstname, String lastname, String address, String city, String phonenumber, String petname, String bdate, String type, String visitdate, String description, int expectedResult, String browser, String Url) {
        browser_Selection browser_selection = new browser_Selection(driver);
        driver = browser_selection.setBrowser(browser, Url);
        addandFindOwners = new AddandFind_Owners(driver);
        addOwnerInfo = new Add_OwnerInformation(driver);
        editandDisplayUserDetails = new EditandDisplay_UserDetails(driver);
        displaySearchResult = new DisplaySearchResult(driver);
        displayandAddVisits = new DisplayandAdd_Visits(driver);
        addPet = new AddPet(driver);
        try {
            Reporter.log("--Testing Search Owner functionality with Parameters::");
            addandFindOwners.setlname(lastname);
            Reporter.log("--Setting last name in search box::");
            addandFindOwners.clickFind();
            Reporter.log("--Clicking Find Owner Button::");
            Reporter.log("displaySearchResult.is_ResultTablePresent()" + displaySearchResult.is_ResultTablePresent());
            if (displaySearchResult.is_ResultTablePresent()) {
                Assert.assertEquals(displaySearchResult.clickFind(firstname + " " + lastname, phonenumber), expectedResult);
                Reporter.log("--Specific result is clicked and its details are displayed::");
            }
            Reporter.log("--editandDisplayUserDetails.is_OwnerDetailsTablePresent()::" + editandDisplayUserDetails.is_OwnerDetailsTablePresent());
            Assert.assertEquals(editandDisplayUserDetails.is_OwnerDetailsTablePresent(), true);
            Reporter.log("--Details are displayed::");
            Assert.assertEquals(editandDisplayUserDetails.addvisit_pet(petname,bdate,type),expectedResult);
            Reporter.log("--Clicking Add Visit Link::");
            displayandAddVisits.verify_petdetails(petname,bdate,type,firstname+" "+lastname);
            displayandAddVisits.setDate(visitdate); Reporter.log("--Setting date in date textbox::");
            displayandAddVisits.setDescription(description); Reporter.log("--Setting description in description textbox::");
            displayandAddVisits.clickAddVisit(); Reporter.log("--Clicking Add visit button::");
            Assert.assertEquals(editandDisplayUserDetails.is_PetDetailsTablePresent(), true);
            Reporter.log("--Pet Details table is present");
            Assert.assertEquals(true, editandDisplayUserDetails.verify_petdetails(petname, bdate, type, visitdate + " " + description));
            Reporter.log("--Expected and Actual Result matches");


        } catch (Exception ex) {

            TakeScreenshot ts = new TakeScreenshot(driver);
            ts.capture("screenshot", "TC.Add_Pet_Owner");
            System.out.println("In case of any element not found:");


        }
        System.out.println("After Try and catch");
    }


    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        String ch_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return new Object[][]{{"Peter", "McTavish", "2387 S. Fair Way", "Madison", "6085552765", "George", "2010-01-20", "snake", "2019-08-10", "Exercise1", 1, "Chrome", "http://localhost:8080/owners/find"}, {"George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023", "Leo", "2010-09-07", "cat", "2019-08-11", "Exercise2", 1, "IE", "http://localhost:8080/owners/find"}};
    }

    //


    @AfterMethod
    public void destroy() {

        driver.quit();

    }
}
