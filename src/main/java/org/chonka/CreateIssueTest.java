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

public class CreateIssueTest {
    // creating jira issue test
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


        // step6: opening jira product
        WebElement jiraProductButton = driver.findElement(By.cssSelector("div[data-testid=\"product-container\"] a:nth-child(1) button"));
        jiraProductButton.click();

        // waiting for jira page to load
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id("createGlobalItem")));

        // step7: click "create" button
        WebElement createButton = driver.findElement(By.id("createGlobalItem"));
        createButton.click();

        // step8: filling issue summary field
        WebElement issueSummaryField = driver.findElement(By.id("summary-field"));
        issueSummaryField.sendKeys("Test issue summary description");

        // step9: click "Assign to me" button
        WebElement assignToMeButton = driver.findElement(By
                .cssSelector("button[data-testid=\"issue-create-commons.ui.assignee-field.assing-to-me-button\"]"));
        assignToMeButton.click();

        // step9: click "Create Issue" button
        WebElement createIssueButton = driver.findElement(By
                .cssSelector("button[data-testid=\"issue-create.ui.modal.footer.create-button\"]"));
        createIssueButton.click();

        // wait to perform assert
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .cssSelector("div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"]")));

        WebElement successAlert = driver.findElement(By
                .cssSelector("div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"]"));
        boolean isSuccess = successAlert.isDisplayed();
        if(isSuccess) System.out.println("Create issue test has passed!");

        // getting success area text to assert
        WebElement successAreaText = driver.findElement(By.cssSelector("span[aria-label=\"Success\"] + span"));
        String successText = successAreaText.getText();
        System.out.println(successText);

        //Завершаем работу с ресурсом
        // driver.quit();
    }
}
