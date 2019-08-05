/**
 * Created by mehtanikhil on 03.08.2019.
 */

import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

//import org.openqa.selenium.*;
//import org.openqa.selenium.By;


public class Search_Pet_Owners_Test {
    WebDriver driver = null;
    Navigation_Links navigationLinks;
    AddandFind_Owners addandFindOwners;
    DisplaySearchResult displaySearchResult;
    Add_OwnerInformation addOwnerInfo;
    EditandDisplay_UserDetails editandDisplayUserDetails;
    AddPet addPet;
    DisplayandAdd_Visits displayandAddVisits;
    Veterinarians_List vetList;

    //SearchOwnerTest method is used for Searching the existing owner  as send by testdata

    @Test(dataProvider = "data-provider", testName = "SearchOwner_Test")
    public void SearchOwnerTest(String name, int expectedResult, String browser, String Url) {


        browser_Selection browser_selection = new browser_Selection(driver);
        driver = browser_selection.setBrowser(browser, Url);


        navigationLinks = new Navigation_Links(driver);
        addandFindOwners = new AddandFind_Owners(driver);
        displaySearchResult = new DisplaySearchResult(driver);
        addOwnerInfo = new Add_OwnerInformation(driver);
        editandDisplayUserDetails = new EditandDisplay_UserDetails(driver);
        //      ap = new AddPet(driver);
        //      dav = new DisplayandAdd_Visits(driver);
        //      vl = new Veterinarians_List(driver);
        try {
            Reporter.log("--Testing Search Owner functionality with Parameters::");
            addandFindOwners.setlname(name);
            Reporter.log("--Setting last name in search box::");
            addandFindOwners.clickFind();
            Reporter.log("--Clicking Find Owner Button::");

            if (addandFindOwners.is_validationTextPresent()) {
                Reporter.log("--No Result found for given search string:has not been found: Validation Text on Screen::");
            } else {
                if (displaySearchResult.is_ResultTablePresent()) {
                    Assert.assertEquals(displaySearchResult.getResult(), expectedResult);
                    Reporter.log("After Assertion passes");
                    navigationLinks.click_findOwners();
                } else {
                    Assert.assertEquals(true, editandDisplayUserDetails.is_OwnerDetailsTablePresent());

                    Reporter.log("--one specific result along with its details is displayed::");
                }
            }
        } catch (Exception ex) {

            TakeScreenshot ts = new TakeScreenshot(driver);
            ts.capture("screenshot", "TC.Search_Pet_Owner");
            System.out.println("In case of any element not found:");


        }
        System.out.println("After Try and catch");

    }


    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"Davis", 1, "Chrome", "http://localhost:8080/owners/find"}, {"", 1, "Chrome", "http://localhost:8080/owners/find"}, {"EE", 1, "Firefox", "http://localhost:8080/owners/find"}, {"Da", 1, "Firefox", "http://localhost:8080/owners/find"}, {"bl", 1, "IE", "http://localhost:8080/owners/find"}};
    }

    @AfterMethod
    public void destroy() {

        driver.quit();

    }
}

