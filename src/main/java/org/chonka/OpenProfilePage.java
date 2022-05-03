package org.chonka;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OpenProfilePage {
    // test for opening jira user profile page
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

        // step6: opening jira product
        WebElement jiraProductButton = driver.findElement(By.cssSelector("div[data-testid=\"product-container\"] a:nth-child(1) button"));
        jiraProductButton.click();

        // waiting for jira page to load
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By
                        .cssSelector("span[data-test-id=\"ak-spotlight-target-profile-spotlight\"]")));

        // step7: opening header profile menu
        WebElement headerProfileMenuButton = driver.findElement(By.cssSelector("span[data-test-id=\"ak-spotlight-target-profile-spotlight\"]"));
        headerProfileMenuButton.click();

        // step8: click profile menu element
        WebElement profileMenuElement = driver.findElement(By
                .cssSelector("div[role=\"group\"]:nth-child(2) div[data-ds--menu--heading-item=\"true\"]+a[href=\"/jira/people/60212468988758006877a1a6\"]"));
        profileMenuElement.click();

        //assert on profile email
        WebElement profileEmail = driver.findElement(By.cssSelector("div[data-test-selector=\"profile-about-item-email\"]"));
        String email = profileEmail.getText();
        if(email.equals("chonkainna@mail.ru")) System.out.println("Open profile page test passed!");
        //Завершаем работу с ресурсом
        // driver.quit();
    }
}
