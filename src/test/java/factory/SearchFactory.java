package factory;

import org.openqa.selenium.WebDriver;
import pages.GooglePage;
import pages.YandexPage;

public class SearchFactory {

    WebDriver driver;

    public SearchFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void buildASearchWith(String searchEngineName) throws InterruptedException {
        if (searchEngineName.equalsIgnoreCase("google")) {
            GooglePage googlePage = new GooglePage(driver);
            googlePage.makeAGoogleSearch();
        } else if (searchEngineName.equalsIgnoreCase("yandex")) {
            Thread.sleep(60000);
            YandexPage yandexPage = new YandexPage(driver);
            yandexPage.makeAYandexSearch();
        }
    }
}
