/**
 * Created by mehtanikhil on 03.08.2019.
 */


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EditandDisplay_UserDetails {

    WebDriver driver;

    @FindBy(linkText = "Edit Owner")
    WebElement editOwner_btn;

    @FindBy(linkText = "Add New Pet")
    WebElement addPet_btn;

    @FindBy(css = "body > div > div > table:nth-child(2)")
    WebElement ownerDetails_tbl;

    @FindBy(css = "body > div > div > table:nth-child(9)")
    WebElement petDetails_tbl;

    @FindBys(value = @FindBy(css = "body > div > div > table:nth-child(2)"))
    List<WebElement> is_ownerTabPresent;

    @FindBys(value = @FindBy(css = "body > div > div > table:nth-child(9)"))
    List<WebElement> is_petTabPresent;

    public EditandDisplay_UserDetails(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }

    boolean is_PetDetailsTablePresent() {
        return is_petTabPresent.size() > 0;
    }

    boolean is_OwnerDetailsTablePresent() {
        return is_ownerTabPresent.size() > 0;
    }

    public void clickEditOwner() {

        editOwner_btn.click();

    }

    public void clickAddPet() {

        addPet_btn.click();

    }

    public void getResult() {


        List<WebElement> rows = ownerDetails_tbl.findElements(By.xpath("/html/body/div/div/table[1]/tbody/tr"));

        for (int j = 0; j < rows.size(); j++) {
            System.out.println(rows.get(j).getText());

        }

    }

    public void verify_details(String name, String address, String city, String phone) {
        List<WebElement> rows = ownerDetails_tbl.findElements(By.xpath("/html/body/div/div/table[1]/tbody/tr"));
        if (rows.size() == 4) {
            Assert.assertEquals("Name", ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/th")).getText());
            Assert.assertEquals("Address", ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[2]/th")).getText());
            Assert.assertEquals("City", ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[3]/th")).getText());
            Assert.assertEquals("Telephone", ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[4]/th")).getText());

            Assert.assertEquals(name, ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[1]/td/b")).getText());
            Assert.assertEquals(address, ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[2]/td")).getText());
            Assert.assertEquals(city, ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[3]/td")).getText());
            Assert.assertEquals(phone, ownerDetails_tbl.findElement(By.xpath("/html/body/div/div/table[1]/tbody/tr[4]/td")).getText());


        } else {

            System.out.println("Inconsistent number of Rows:4 rows should be present");
        }

    }


    public boolean verify_petdetails(String name, String bdate, String type, String visit_details) {
        List<WebElement> rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));
        boolean visits_flag = false;
        boolean flag;
        System.out.println(rows.size());
        if (rows.size() == 1) {
            flag = true;
        } else {
            flag = false;
        }
        for (int j = 1; j <= rows.size(); j++) {

            if (name.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[1]")).getText()) && bdate.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[2]")).getText()) && type.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[3]")).getText())) {
                List<WebElement> sub_rows;
                System.out.println("Inside condition:" + j);
                System.out.println("flag:" + flag);
                if (flag) {
                    sub_rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr/td[2]/table/tbody/tr"));
                } else {
                    sub_rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr"));
                }

                System.out.println(sub_rows.size());
                if (visit_details == null && sub_rows.size() == 1) {
                    return true;
                }
                for (int k = 0; k < sub_rows.size(); k++) {
                    System.out.println(sub_rows.get(k).getText());
                    if (sub_rows.get(k).getText().equals(visit_details)) {
                        visits_flag = true;
                        System.out.println("Visit found:");
                        break;
                    }

                }
                if (visits_flag) {
                    return true;
                }
            }


        }
        return false;
    }


    public boolean edit_petdetails(String name, String type) {
        List<WebElement> rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));

        for (int j = 1; j <= rows.size(); j++) {

            if (name.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[1]")).getText()) && type.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[3]")).getText())) {
                System.out.println("Inside condition");
                List<WebElement> sub_rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr"));

                if (sub_rows.size() > 0) {
                    petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr[" + sub_rows.size() + "]/td[1]/a")).click();

                } else {
                    petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr/td[1]/a")).click();

                }
                return true;
            }

        }
        return false;
    }

    public int addvisit_pet(String name, String bdate, String type) {

        List<WebElement> rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr"));

        for (int j = 1; j <= rows.size(); j++) {

            if (name.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[1]")).getText()) && bdate.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[2]")).getText()) && type.equals(petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[1]/dl/dd[3]")).getText())) {
                System.out.println("Inside condition");
                List<WebElement> sub_rows = petDetails_tbl.findElements(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr"));

                if (sub_rows.size() > 0) {
                    petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr[" + sub_rows.size() + "]/td[2]/a")).click();
                } else {
                    petDetails_tbl.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr[" + j + "]/td[2]/table/tbody/tr/td[2]/a")).click();

                }
                return 1;
            }

        }
        return 0;

    }


}
