package org.chonka;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.http.io.SessionOutputBuffer;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
// login to atlassian account
public class LoginTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // step1: opening login page
        driver.get("https://id.atlassian.com/login");

        // step2: filling username field
        WebElement loginField = driver.findElement(By.id("username"));
        loginField.sendKeys("chonkainna@mail.ru");

        // step3: click login button
        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();

        // step4: filling password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("inna1234");

        // step5: click login button
        loginSubmitButton.click();

        //waiting for private page loading to perform assert
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("button[data-testid = nav__profile-menu-trigger]")));


        WebElement profileMenuButton = driver.findElement(By.cssSelector("button[data-testid = nav__profile-menu-trigger]"));
        // opening profile menu
        profileMenuButton.click();
        WebElement profileMenuName = driver.findElement(By
                .cssSelector("div[data-testid=\"nav__profile-menu\"] div[data-ds--menu--heading-item=\"true\""));
        // getting profile name
        String profileName = profileMenuName.getText();

        //assert on profile name
        if(profileName.equals("INNA")) System.out.println("Login test passed!");
        //Завершаем работу с ресурсом
        // driver.quit();
    }
}
