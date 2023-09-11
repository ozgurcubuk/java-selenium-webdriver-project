package pages;

import obj.SearchObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage implements SearchEngine {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public List<SearchObject> search(WebElement searchBox, WebElement searchResult, By headerXpath, By descriptionXpath) {
        searchBox.sendKeys(System.getProperty("keyword"));
        searchBox.sendKeys(Keys.ENTER);

        List<SearchObject> searchObjectList = new ArrayList<>();

        List<WebElement> headers = null;
        headers = searchResult.findElements(headerXpath);

        for (WebElement header:headers) {
            if (!header.getText().equalsIgnoreCase("")&!header.getText().contains("YouTube")) {
                SearchObject searchObject = new SearchObject();
                searchObject.setHeader(header.getText());
                searchObject.setUrl(header.findElement(By.xpath(".//parent::a")).getAttribute("href").replace("\n", ""));
                try {
                    searchObject.setDescription(header.findElement(descriptionXpath).getText().replace("\n", " "));
                } catch (NoSuchElementException e) {
                    searchObject.setDescription("NO DESCRIPTION");
                }

                searchObjectList.add(searchObject);
            }
        }

        if (searchObjectList.size() > 10) {
            searchObjectList = searchObjectList.subList(0,10);
        }

        return searchObjectList;
    }
}
