/**
 * Created by mehtanikhil on 05.08.2019.
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

public class VeterinarianList_Test {
    WebDriver driver = null;
    Navigation_Links navigationLinks;

    Veterinarians_List vetList;



    @Test(dataProvider = "data-provider", testName = "VetList_Test")
    public void SearchVetListTest(String name, String speciality,  int expectedResult, String browser, String Url) {
        browser_Selection browser_selection = new browser_Selection(driver);
        driver = browser_selection.setBrowser(browser, Url);
        navigationLinks = new Navigation_Links(driver);
        navigationLinks.click_vetList();
        Reporter.log("--Veterinarian List Page displayed::");
        vetList = new Veterinarians_List(driver);
        try {
            Assert.assertEquals(vetList.verify_vet_info(name, speciality), 1);
            Reporter.log("--Expected result matches actual result::");
        }
   catch (Exception ex) {

            TakeScreenshot ts = new TakeScreenshot(driver);
            ts.capture("screenshot", "TC.Add_Pet_Owner");
            System.out.println("In case of any element not found:");


        }
        System.out.println("After Try and catch");
    }


    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        String ch_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return new Object[][]{{"James Carter", "none", 1, "Chrome", "http://localhost:8080/owners/find"}, {"Helen Leary", "radiology", 1, "Chrome", "http://localhost:8080/owners/find"},{"Linda Douglas", "dentistry surgery", 1, "Chrome", "http://localhost:8080/owners/find"},{"Rafael Ortega", "surgery", 1, "Chrome", "http://localhost:8080/owners/find"},{"Henry Stevens", "radiology", 1, "Chrome", "http://localhost:8080/owners/find"},{"Sharon Jenkins", "none", 1, "Chrome", "http://localhost:8080/owners/find"},};
    }


    @AfterMethod
    public void destroy() {

        driver.quit();

    }

}
