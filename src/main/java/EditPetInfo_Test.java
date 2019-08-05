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

public class EditPetInfo_Test {
    WebDriver driver;
    Navigation_Links navigationLinks;
    AddandFind_Owners addandFindOwners;
    DisplaySearchResult displaySearchResult;
    Add_OwnerInformation addOwnerInfo;
    EditandDisplay_UserDetails editandDisplayUserDetails;
    AddPet addPet;
    DisplayandAdd_Visits displayandAddVisits;
    Veterinarians_List vetList;



    @Test(dataProvider = "data-provider", testName = "EditPetInfoTest")
    public void EditPetInfoTest(String firstname, String lastname, String address, String city, String phonenumber, String petname, String bdate, String type, String visitdate, String description, int expectedResult, String browser, String Url) {
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
            Assert.assertEquals(editandDisplayUserDetails.edit_petdetails(petname, type), true);
            Reporter.log("--Clicking Edit Pet Info Link::");
            addPet.updatepetBdate(bdate);
            addPet.clickupdatePet();
            Assert.assertEquals(editandDisplayUserDetails.is_PetDetailsTablePresent(), true);
            Reporter.log("--Pet Details table is present");
            String concat_text;
            if (visitdate == null && description == null) {
                concat_text = null;

            } else {
                concat_text = visitdate + " " + description;
            }
            Assert.assertEquals(editandDisplayUserDetails.verify_petdetails(petname, bdate, type, concat_text), true);
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
        String ch_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return new Object[][]{{"Maria", "Escobito", "345 Maple St.", "Madison", "6085557683", "Mulligan", ch_date, "dog", null, null, 1, "Chrome", "http://localhost:8080/owners/find"},  {"Eduardo", "Rodriquez", "2693 Commerce St.", "McFarland", "6085558763", "Rosy", ch_date, "dog", null, null, 1, "IE", "http://localhost:8080/owners/find"}};
    }

    //


    @AfterMethod
    public void destroy() {

        driver.quit();

    }
}
