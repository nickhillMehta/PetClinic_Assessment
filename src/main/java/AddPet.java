
//import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class AddPet {

    WebDriver driver;

    @FindBy(css = "body > div > div > form > div:nth-child(3) > div > button")
    WebElement addPet_btn;

    @FindBy(css = "body > div > div > form > div:nth-child(3) > div > button")
    WebElement updatePet_btn;

    @FindBy(css = "body > div > div > form > div.form-group.has-feedback > div:nth-child(1) > div > span")
    WebElement ownername_lbl_value;

    @FindBy(id = "name")
    WebElement petname_txtfld;

    @FindBy(xpath = "//*[@id=\"birthDate\"]")
    WebElement bdate_fld;

    @FindBy(name = "type")
    WebElement petType_drpdwn;


    public AddPet(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }

    public void setPetName(String strname) {

        petname_txtfld.sendKeys(strname);
    }

    public void setpetBdate(String strdate) {

        bdate_fld.sendKeys(strdate);
    }

    public void setpettype(String strtype) {
        Select type_drpdown = new Select(petType_drpdwn);
        type_drpdown.selectByVisibleText(strtype);
    }

    public void clickAddPet() {

        addPet_btn.click();

    }

    public void clickupdatePet() {

        updatePet_btn.click();

    }

    public void updatePetName(String strname) {
        petname_txtfld.clear();
        petname_txtfld.sendKeys(strname);
    }

    public void updatepetBdate(String strdate) {
        bdate_fld.clear();
        bdate_fld.sendKeys(strdate);
    }

}
