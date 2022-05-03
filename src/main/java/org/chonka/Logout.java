package org.chonka;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Logout {
    // testing logout from atlassian account
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

        //waiting for private page loading to perform logout
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("button[data-testid = nav__profile-menu-trigger]")));

        // step6: opening profile menu
        WebElement profileMenuButton = driver.findElement(By.cssSelector("button[data-testid = nav__profile-menu-trigger]"));
        profileMenuButton.click();

        // step7: click logout button
        WebElement profileMenuLogoutButton = driver.findElement(By
                .cssSelector("button[data-testid=\"nav__logout-btn\"]"));
        profileMenuLogoutButton.click();

        // step8: confirm logout
        WebElement logoutSubmitButton = driver.findElement(By.id("logout-submit"));
        logoutSubmitButton.click();

        //waiting for public page loading to assert logout
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id("username")));

        //assert on login page, has username field
        WebElement loginFieldAfter = driver.findElement(By.id("username"));
        boolean isLoginField = loginFieldAfter.isDisplayed();
        if(isLoginField) System.out.println("Logout test passed!");
        //Завершаем работу с ресурсом
        // driver.quit();
    }
}
