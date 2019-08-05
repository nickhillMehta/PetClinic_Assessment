//import static org.junit.Assert.*;com.org.

import java.util.concurrent.TimeUnit;
//import org.junit.AfterClass;

//import org.openqa.selenium.*;
//import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetClinic_AppLaunchTest
{


    static WebDriver driver;
    Navigation_Links nl ;
    AddandFind_Owners adf ;
    DisplaySearchResult dsr;
    Add_OwnerInformation aoi;
    EditandDisplay_UserDetails edu ;
    AddPet ap ;
    DisplayandAdd_Visits dav;
    Veterinarians_List vl;


    @BeforeClass
    public static void  openBrowser()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver_win32V63\\chromedriver.exe");
        driver= new ChromeDriver() ;

    }


    public void testLaunch()
    {
        driver.get("http://localhost:8080/owners/find");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        nl = new Navigation_Links(driver);
         adf = new AddandFind_Owners(driver);
         dsr = new DisplaySearchResult(driver);
         aoi = new Add_OwnerInformation(driver);
         edu = new EditandDisplay_UserDetails(driver);
         ap = new AddPet(driver);
         dav = new DisplayandAdd_Visits(driver);
         vl = new Veterinarians_List(driver);
    }

    @Test
    public void testFind()
    {
        testLaunch();
        adf.setlname("Da");
        adf.clickFind();
        //dsr.getResult();
        dsr.clickFind("Betty Davis", "6085551749");
		/*adf.click_addOwner();
		aoi.setFname("autofname");
		aoi.setLname("autolname");
		aoi.setAddress("autoadd") ;
		aoi.setCity("wien") ;
		aoi.setPhone("123456789") ;
		aoi.clickaddOwner() ;
		edu.verify_details("autofname autolname", "autoadd", "wien", "123456789");
		edu.getResult();
		edu.clickEditOwner();

		aoi.editFname("chg_autofname");
		aoi.editLname("chg_autolname");
		aoi.editAddress("chg_autoadd") ;
		aoi.editCity("wien") ;
		aoi.editPhone("1234567890") ;
		aoi.clickaddOwner() ;
		edu.verify_details("chg_autofname chg_autolname", "chg_autoadd", "wien", "1234567890");
		edu.getResult();
		edu.clickAddPet();
		ap.setPetName("Rocky");
		ap.setpetBdate("2010-11-11");
		ap.setpettype("dog");
		ap.clickAddPet();
		edu.edit_petdetails("Rocksy", "2010-12-12", "cat");
		ap.updatepetBdate("2010-12-12");
		ap.updatePetName("Rocksy");
		ap.setpettype("cat");
		ap.clickupdatePet();
//edu.verify_petdetails("Basil", "2012-08-06", "hamster", null);
//		edu.edit_petdetails("Basil", "2012-08-06", "hamster");
		edu.addvisit_pet("Basil", "2012-08-06", "hamster");
		dav.verify_petdetails("Basil", "2012-08-06", "hamster", "Betty Davis");
		dav.setDate("2019-09-11");
		dav.setDescription("Exercises");
		dav.clickAddVisit();*/
        edu.addvisit_pet("Basil", "2012-08-06", "hamster");
        dav.verify_petdetails("Basil", "2012-08-06", "hamster", "Betty Davis");

        int y = dav.verify_petvisits("2019-09-11", "Exercises");
        Assert.assertEquals(y,1);
        nl.click_home();
        nl.click_error();
        nl.click_vetList();
        int x = vl.verify_vet_info("Henry Stevens", "radiology");
        Assert.assertEquals(x,1);
    }
//	@AfterClass	public static void BrowserClose()
//	{
    //	driver.quit();
    //}
}
