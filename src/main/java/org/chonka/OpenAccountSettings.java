package org.chonka;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OpenAccountSettings {
    // test to open Account settings page for atlassian user
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

        //waiting for private page loading
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("button[data-testid = nav__profile-menu-trigger]")));

        // step6: opening header profile menu
        WebElement profileButton = driver.findElement(By.cssSelector("button[data-testid=\"nav__profile-menu-trigger\"]"));
        profileButton.click();

        // step7: opening account settings
        WebElement accountSettingsButton = driver.findElement(By.cssSelector("a[data-testid=\"nav__manage-profile-btn\"]"));
        accountSettingsButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ArrayList<String> windowTabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window(windowTabs.get(1));

        //waiting for account settings page loading
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("div[data-test-selector=\"field-edit-full-name\"] div[data-test-selector=\"profile-about-item-read-view\"]")));

        // getting account settings page username
        WebElement accountSettingsName = driver.findElement(By.cssSelector("div[data-test-selector=\"field-edit-full-name\"] div[data-test-selector=\"profile-about-item-read-view\"]"));
        String profileName = accountSettingsName.getText();

        //assert on profile name
        if(profileName.equals("Inna")) System.out.println("Open account settings page test passed!");
        //Завершаем работу с ресурсом
        // driver.quit();
    }
}
