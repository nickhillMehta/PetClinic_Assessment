

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class DisplayandAdd_Visits {
    WebDriver driver;


    @FindBy(css = "body > div > div > form > div:nth-child(2) > div > button")
    WebElement addVisit_btn;

    @FindBy(id = "description")
    WebElement description_txtfld;

    @FindBy(id = "date")
    WebElement date_fld;

    @FindBy(xpath = "/html/body/div/div/table[1]")
    WebElement petdetails_tbl;

    @FindBy(xpath = "/html/body/div/div/table[2]")
    WebElement petVisits_tbl;



    public DisplayandAdd_Visits(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }
    public void setDate(String strdate) {
        date_fld.clear();
        date_fld.sendKeys(strdate);
    }
    public void setDescription(String strdesc) {

        description_txtfld.sendKeys(strdesc);
    }

    public void clickAddVisit() {

        addVisit_btn.click();

    }

    public void verify_petdetails(String name,String bdate,String Type,String owner)
    {
        Assert.assertEquals("Name Birth Date Type Owner",petdetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/thead")).getText());
        Assert.assertEquals(name,petdetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr/td[1]")).getText());
        Assert.assertEquals(bdate,petdetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr/td[2]")).getText());
        Assert.assertEquals(Type,petdetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr/td[3]")).getText());
        Assert.assertEquals(owner,petdetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr/td[4]")).getText());

    }

    public int verify_petvisits(String date,String description)
    {
        Assert.assertEquals("Date Description",petVisits_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[1]")).getText());
        List<WebElement> rows =  petVisits_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));
        for(int i=2;i<=rows.size();i++)
        {
            if(date.equals(petVisits_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr["+i+"]/td[1]")).getText()) && description.equals(petVisits_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr["+i+"]/td[2]")).getText()))
            {
                return 1;
            }
        }
        return 0;
    }
}
