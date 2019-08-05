
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Add_OwnerInformation {

    WebDriver driver;

    @FindBy(id = "firstName")
    WebElement firstname_txtfld;

    @FindBy(id = "lastName")
    WebElement lastname_txtfld;

    @FindBy(id = "address")
    WebElement address_txtfld;

    @FindBy(id = "city")
    WebElement city_txtfld;

    @FindBy(id = "telephone")
    WebElement telephone_txtfld;

    @FindBy(css = "#add-owner-form > div:nth-child(2) > div > button")
    WebElement addowner_btn;

    @FindBy(css = "#add-owner-form > div:nth-child(2) > div > button")
    WebElement editowner_btn;

    @FindBys(value = @FindBy(css = "#add-owner-form > div.form-group.has-feedback > div.form-group.has-error > div > span.glyphicon.glyphicon-remove.form-control-feedback"))
    List<WebElement> is_redCrossPresent ;

    @FindBys(value = @FindBy(css = "#add-owner-form > div.form-group.has-feedback > div.form-group.has-error > div > span.help-inline"))
    List<WebElement> is_phoneNumValidationPresent ;

    public Add_OwnerInformation(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }

    public void setFname(String strfname) {

        firstname_txtfld.sendKeys(strfname);
    }
    public void setLname(String strlname) {

        lastname_txtfld.sendKeys(strlname);
    }
    public void setAddress(String straddress) {

        address_txtfld.sendKeys(straddress);
    }
    public void setCity(String strlcity) {

        city_txtfld.sendKeys(strlcity);
    }
    public void setPhone(String strphone) {

        telephone_txtfld.sendKeys(strphone);
    }

    // Click on find button

    public void clickaddOwner() {

        addowner_btn.click();

    }

    public boolean isRedCrossPresent()
    {
        return is_redCrossPresent.size() > 0;
    }

    public boolean isValidationErrPresent()
    {

        return is_phoneNumValidationPresent.size() > 0;
    }

    public void editFname(String strfname) {
        firstname_txtfld.clear();
        firstname_txtfld.sendKeys(strfname);
    }
    public void editLname(String strlname) {
        lastname_txtfld.clear();
        lastname_txtfld.sendKeys(strlname);
    }
    public void editAddress(String straddress) {
        address_txtfld.clear();
        address_txtfld.sendKeys(straddress);
    }
    public void editCity(String strlcity) {
        city_txtfld.clear();
        city_txtfld.sendKeys(strlcity);
    }
    public void editPhone(String strphone) {
        telephone_txtfld.clear();
        telephone_txtfld.sendKeys(strphone);
    }


}
