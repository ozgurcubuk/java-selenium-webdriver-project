package pages;

import obj.SearchObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YandexPage extends SearchPage {

    @FindBy(id = "text")
    WebElement searchBox;

    @FindBy(id = "search-result")
    WebElement searchResult;

    By headerXpath = By.xpath("//*[@id=\"search-result\"]//a/h2");

    By descriptionXpath = By.xpath("./ancestor::div[1]/following-sibling::div[2]");

    public static List<SearchObject> yandexSearchResultList;

    public YandexPage(WebDriver driver) {
        super(driver);
    }

    public void makeAYandexSearch() {
        yandexSearchResultList = super.search(searchBox, searchResult, headerXpath, descriptionXpath);
    }
}