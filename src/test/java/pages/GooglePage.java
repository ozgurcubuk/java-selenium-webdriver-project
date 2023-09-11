package pages;

import obj.SearchObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GooglePage extends SearchPage {

    @FindBy(id = "APjFqb")
    WebElement searchBox;

    @FindBy(id = "rso")
    WebElement searchResult;

    By headerXpath = By.xpath("//*[@id=\"rso\"]/div//h3");

    By descriptionXpath = By.xpath("./ancestor::div[3]/following-sibling::div[1]");

    public static List<SearchObject> googleSearchResultList;

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void makeAGoogleSearch() {
        googleSearchResultList = super.search(searchBox, searchResult, headerXpath, descriptionXpath);
    }
}