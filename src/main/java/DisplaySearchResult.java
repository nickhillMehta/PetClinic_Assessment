
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
//import java.text.ParseException;
import java.util.List;

public class DisplaySearchResult {
    WebDriver driver;

    //@FindBy(css = "#lastNameGroup > label")
    //WebElement lastname_lbl;

    @FindBy(id = "owners")
    WebElement owner_table;

    @FindBys(value = @FindBy(id = "owners"))
    List<WebElement> is_resultTabPresent ;

    //@FindBy(css = "#search-owner-form > div:nth-child(2) > div > button")
    //WebElement find_btn;

    //@FindBy(linkText = "Add Owner")
    //WebElement addOwner_btn;

    public DisplaySearchResult(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }

    boolean is_ResultTablePresent()
    {
        return is_resultTabPresent.size()> 0 ;
    }
    // display result

    public int getResult() {

        List <WebElement> head = owner_table.findElements(By.xpath("//*[@id=\"owners\"]/thead")) ;

        List <WebElement> rows = owner_table.findElements(By.xpath("//*[@id=\"owners\"]/tbody/tr"));
        if(rows.size()>=1) {

                System.out.println(owner_table.findElement(By.xpath("//*[@id=\"owners\"]/thead/tr")).getText());
                //System.out.println(columns.size());

            for (int j = 0; j < rows.size(); j++) {
                System.out.println(rows.get(j).getText());

            }

            return 1;
        }
        else
            return 0;

    }
    // Match result based on name and phone

    public int clickFind(String name,String phone) {
        List <WebElement> rows = owner_table.findElements(By.xpath("//*[@id=\"owners\"]/tbody/tr"));
        for(int j = 1; j <= rows.size(); j++) {
            System.out.println(":"+owner_table.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr["+j+"]/td[1]/a")).getText()+":");
            System.out.println(":"+owner_table.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr["+j+"]/td[4]")).getText()+":");
            if(name.equals(owner_table.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr["+j+"]/td[1]/a")).getText()) && phone.equals(owner_table.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr["+j+"]/td[4]")).getText()))
            {
                System.out.println("Inside condition");
                owner_table.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr["+j+"]/td[1]/a")).click();
                return 1;
            }

        }
        return 0;

    }
	/*
	public void click_addOwner() {

		addOwner_btn.click();

	}

	public void findbyLastname(String strlname) {

		// Fill last name

		this.setlname(strlname);

		// Click find button

		this.clickFind();
	}
*/
}
