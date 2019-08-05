

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;



public class Navigation_Links {
    WebDriver driver;
    @FindBy(css = "#main-navbar > ul > li.active > a > span:nth-child(2)") WebElement home_page ;
    @FindBy(css = "#main-navbar > ul > li:nth-child(3) > a > span:nth-child(2)") WebElement find_owners ;
    @FindBy(css = "#main-navbar > ul > li:nth-child(4) > a > span:nth-child(2)") WebElement vet_list ;
    @FindBy(css = "#main-navbar > ul > li:nth-child(5) > a > span:nth-child(2)") WebElement error ;
    public Navigation_Links(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }

    public void click_home()
    {
        home_page.click();
    }
    public void click_findOwners()
    {
        find_owners.click();
    }
    public void click_vetList()
    {
        vet_list.click();
    }
    public void click_error()
    {
        error.click();
    }
}
