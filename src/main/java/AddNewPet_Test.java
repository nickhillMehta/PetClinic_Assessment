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

public class AddNewPet_Test {
    WebDriver driver;
    Navigation_Links navigationLinks;
    AddandFind_Owners addandFindOwners;
    DisplaySearchResult displaySearchResult;
    Add_OwnerInformation addOwnerInfo;
    EditandDisplay_UserDetails editandDisplayUserDetails;
    AddPet addPet;
    DisplayandAdd_Visits displayandAddVisits;
    Veterinarians_List vetList;

//AddnewPetTest method is used for adding pet to the existing owner  as send by testdata

    @Test(dataProvider = "data-provider", testName = "AddNewPet_Test")
    public void AddnewPetTest(String firstname, String lastname, String address, String city, String phonenumber, String petname, String bdate, String type, int expectedResult, String browser, String Url) {
        browser_Selection browser_selection = new browser_Selection(driver);
        driver = browser_selection.setBrowser(browser, Url);
        addandFindOwners = new AddandFind_Owners(driver);
        addOwnerInfo = new Add_OwnerInformation(driver);
        editandDisplayUserDetails = new EditandDisplay_UserDetails(driver);
        displaySearchResult = new DisplaySearchResult(driver);
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

            editandDisplayUserDetails.clickAddPet();
            Reporter.log("--Clicking Add Pet Button::");
            addPet.setPetName(petname);
            Reporter.log("--Setting name in name textbox::");
            addPet.setpetBdate(bdate);
            Reporter.log("--Setting bdate in bdate textbox::");
            addPet.setpettype(type);
            Reporter.log("--Setting type in type textbox::");
            addPet.clickAddPet();
            Reporter.log("--Add Pet Button Clicked::");
            /*if(addPet.isRedCrossPresent() && addPet.isValidationErrPresent())
            {
                Reporter.log("--Validation error for bdate:numeric value out of bounds (<10 digits>.<0 digits> expected)::");
                Assert.assertEquals(0,expectedResult);
                Reporter.log("--Expected and Actual Result matches");
            }*/

            Assert.assertEquals(true, editandDisplayUserDetails.is_OwnerDetailsTablePresent());
            Reporter.log("--User Details displayed::");
            Assert.assertEquals(true, editandDisplayUserDetails.is_PetDetailsTablePresent());
            Assert.assertEquals(1, expectedResult);
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
        return new Object[][]{{"Jean", "Coleman", "105 N. Lake St.", "Monona", "6085552654", "Cissy" + ch_date, "2009-10-10", "dog", 1, "Chrome", "http://localhost:8080/owners/find"}, {"Carlos", "Estaban", "2335 Independence La.", "Waunakee", "6085555487", "tweetsie" + ch_date, "2015-10-10", "bird", 1, "Firefox", "http://localhost:8080/owners/find"}};
    }


    @AfterMethod
    public void destroy() {

        driver.quit();

    }
}
