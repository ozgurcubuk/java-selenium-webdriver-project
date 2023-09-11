package pages;

import obj.SearchObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface SearchEngine {

    List<SearchObject> search(WebElement searchBox, WebElement searchResult, By headerXpath, By descriptionXpath);
}
