

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Veterinarians_List {
    WebDriver driver;

    public Veterinarians_List(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }
    @FindBy(css ="body > div > div > h2")
    WebElement Vet_label ;

    @FindBy(id="vets")
    WebElement Vet_tbl ;


    public int verify_vet_info(String name,String specialities)
    {
        Assert.assertEquals("Name Specialties",Vet_tbl.findElement(By.xpath("//*[@id=\"vets\"]/thead")).getText());
        List<WebElement> rows =  Vet_tbl.findElements(By.xpath("//*[@id=\"vets\"]/tbody/tr"));
        for(int i=1;i<=rows.size();i++)
        {
            if(name.equals(Vet_tbl.findElement(By.xpath("//*[@id=\"vets\"]/tbody/tr["+i+"]/td[1]")).getText()) && specialities.equals(Vet_tbl.findElement(By.xpath("//*[@id=\"vets\"]/tbody/tr["+i+"]/td[2]")).getText()))
            {
                return 1;
            }
        }
        return 0;
    }
}
