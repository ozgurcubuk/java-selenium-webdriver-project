import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    protected String info = "[\u001B[34mINFO\u001B[0m] ";

    public TestBase(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            if(System.getProperty("server").equalsIgnoreCase("container")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("no-sandbox");
                this.driver = new ChromeDriver(options);
                System.out.println(info+"chrome driver has been initiated as per user preferences with desired options for container");
            } else if(System.getProperty("server").equalsIgnoreCase("local")) {
                this.driver = new ChromeDriver();
                System.out.println(info+"chrome driver has been initiated as per user preferences with desired options for local");
            }
        } else if (browser.equalsIgnoreCase("Firefox")) {
            System.out.println(info+"firefox driver has been initiated as per user preferences");
            this.driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        System.out.println(info+"all cookies of the browser has been deleted for clearer initial state");
    }

}
