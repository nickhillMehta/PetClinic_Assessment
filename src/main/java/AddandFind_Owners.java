import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddandFind_Owners {
    WebDriver driver;

    @FindBy(css = "#lastNameGroup > label")
    WebElement lastname_lbl;

    @FindBy(id = "lastName")
    WebElement lastname_txtfld;

    @FindBy(css = "#search-owner-form > div:nth-child(2) > div > button")
    WebElement find_btn;

    @FindBy(linkText = "Add Owner")
    WebElement addOwner_btn;

    @FindBys(value = @FindBy(css = "#lastNameGroup > div > span > div > p"))
    List<WebElement> validation_Err;

    public AddandFind_Owners(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }
    // Set lastname in find textbox

    public void setlname(String strlname) {
        lastname_txtfld.clear();
        lastname_txtfld.sendKeys(strlname);
    }

    // Click on find button

    public void clickFind() {

        find_btn.click();

    }

    public void click_addOwner() {

        addOwner_btn.click();

    }

    boolean is_validationTextPresent() {
        return validation_Err.size() > 0;
    }

    public void findbyLastname(String strlname) {

        // Fill last name

        this.setlname(strlname);

        // Click find button

        this.clickFind();
    }

}
