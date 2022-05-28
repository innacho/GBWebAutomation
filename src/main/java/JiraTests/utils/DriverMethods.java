package JiraTests.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DriverMethods extends MyWebDriverEventListener {
    // private static WebDriver driver;
    private static EventFiringWebDriver driver;

    public static void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        // driver = new ChromeDriver(options);
        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyWebDriverEventListener());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void goToLogin(){
        Assertions.assertDoesNotThrow( () -> driver.get(ConfigProperties.getProperty("LOGIN_PAGE")),
                "Page is not available");
    }

    public void clearCookies(){
        driver.manage().deleteAllCookies();
    }

    public static void close() {
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }

}
