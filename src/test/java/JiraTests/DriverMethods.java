package JiraTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DriverMethods {
    private static WebDriver driver;

    static void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    void goTo(){
        Assertions.assertDoesNotThrow( () -> driver.get("https://id.atlassian.com/login"),
                "Page is not available");
    }

    void clearCookies(){
        driver.manage().deleteAllCookies();
    }

    static void close() {
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
