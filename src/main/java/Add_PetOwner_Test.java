/**
 * Created by mehtanikhil on 03.08.2019.
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

public class Add_PetOwner_Test {

    WebDriver driver = null;
    Navigation_Links navigationLinks;
    AddandFind_Owners addandFindOwners;
    DisplaySearchResult displaySearchResult;
    Add_OwnerInformation addOwnerInfo;
    EditandDisplay_UserDetails editandDisplayUserDetails;
    AddPet addPet;
    DisplayandAdd_Visits displayandAddVisits;
    Veterinarians_List vetList;

    @BeforeClass
    public void setUpBeforeClass() throws Exception {

    }

    @Test(dataProvider = "data-provider", testName = "AddOwner_Test")
    public void AddnewPetOwnerTest(String firstname, String lastname, String address, String city, String phonenumber, int expectedResult, String browser, String Url) {
        browser_Selection browser_selection = new browser_Selection(driver);
        driver = browser_selection.setBrowser(browser, Url);
        addandFindOwners = new AddandFind_Owners(driver);
        addOwnerInfo = new Add_OwnerInformation(driver);
        editandDisplayUserDetails = new EditandDisplay_UserDetails(driver);
        try {
            addandFindOwners.click_addOwner();
            Reporter.log("--Clicking Add Owner Button::");
            addOwnerInfo.setFname(firstname);
            Reporter.log("--Setting Firstname in firstname textbox::");
            addOwnerInfo.setLname(lastname);
            Reporter.log("--Setting Lastname in Lastname textbox::");
            addOwnerInfo.setAddress(address);
            Reporter.log("--Setting Address in Address textbox::");
            addOwnerInfo.setCity(city);
            Reporter.log("--Setting City in city textbox::");
            addOwnerInfo.setPhone(phonenumber);
            Reporter.log("--Setting Phonenumber in phonenumber textbox::");
            addOwnerInfo.clickaddOwner();
            if (addOwnerInfo.isRedCrossPresent() && addOwnerInfo.isValidationErrPresent()) {
                Reporter.log("--Validation error for Phone Number:numeric value out of bounds (<10 digits>.<0 digits> expected)::");
                Assert.assertEquals(0, expectedResult);
                Reporter.log("--Expected and Actual Result matches");
            } else {
                Assert.assertEquals(true, editandDisplayUserDetails.is_OwnerDetailsTablePresent());
                Reporter.log("--User Details displayed::");
                Assert.assertEquals(1, expectedResult);
                Reporter.log("--Expected and Actual Result matches");
            }

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
        return new Object[][]{{"Test" + ch_date, "Automation" + ch_date, "Testgasse", "Wien", "01234567890", 1, "Chrome", "http://localhost:8080/owners/find"}, {"TestNG" + ch_date, "AutomationNG" + ch_date, "Testgasse", "Wien", "01234567890", 1, "IE", "http://localhost:8080/owners/find"}};
    }

    @AfterMethod
    public void destroy() {

        driver.quit();

    }

}
