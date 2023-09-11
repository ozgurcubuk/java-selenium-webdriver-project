import factory.SearchFactory;

import obj.SearchObject;
import org.junit.Test;
import pages.GooglePage;
import pages.YandexPage;

import java.util.ArrayList;
import java.util.List;

public class SearchTest extends TestBase {

    SearchFactory searchFactory;

    public SearchTest() {
        super(System.getProperty("browser"));
    }

    @Test
    public void listMatchesOnTwoSearchEngine() throws InterruptedException {
        searchFactory = new SearchFactory(driver);
        System.out.println(info+"navigation to \"https://www.google.com.tr\"");
        driver.get("https://www.google.com.tr");

        System.out.println(info+"searching for: "+System.getProperty("keyword"));
        searchFactory.buildASearchWith("google");

        System.out.println(info+"navigation to \"https://www.yandex.com.tr\"");
        driver.get("https://www.yandex.com.tr");

        System.out.println(info+"searching for: "+System.getProperty("keyword"));
        searchFactory.buildASearchWith("yandex");

        List<String> googleSearchHeaders = new ArrayList<>();
        List<String> yandexSearchHeaders = new ArrayList<>();

        for (SearchObject searchObjectInGoogleList: GooglePage.googleSearchResultList) {
            googleSearchHeaders.add(searchObjectInGoogleList.getHeader());
        }

        for (SearchObject searchObjectInYandexList: YandexPage.yandexSearchResultList) {
            yandexSearchHeaders.add(searchObjectInYandexList.getHeader());
        }
        googleSearchHeaders.retainAll(yandexSearchHeaders);

        List<SearchObject> matchedSearchObjectList = new ArrayList<>();
        for (String googleHeader:googleSearchHeaders) {
            for (SearchObject googleSearchObjectInList:GooglePage.googleSearchResultList) {
                if (googleHeader.equalsIgnoreCase(googleSearchObjectInList.getHeader())) {
                    matchedSearchObjectList.add(GooglePage.googleSearchResultList.get(GooglePage.googleSearchResultList.indexOf(googleSearchObjectInList)));
                }
            }
        }

        System.out.println(info+"LISTING MATCHED OBJECTS...");
        int i = 1;
        for (SearchObject matchedSearchObject:matchedSearchObjectList) {
            System.out.println("MATCH-"+i+" HEADER: "+matchedSearchObject.getHeader()+"\nMATCH-"+i+" URL: "+matchedSearchObject.getUrl()+"\nMATCH-"+i+" DESCRIPTION: "+matchedSearchObject.getDescription());
            i++;
        }

        System.out.println(info+"killing driver session");
        driver.quit();
    }
}