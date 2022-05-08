package JiraTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginTest extends AbstractTestPublic {

    @DisplayName("Positive login test")
    @Test
    void positiveTest() {
        // step2: filling username field
        WebElement loginField = getDriver().findElement(By.id("username"));
        loginField.sendKeys("chonkainna@mail.ru");

        // step3: click login button
        WebElement loginSubmitButton = getDriver().findElement(By.id("login-submit"));
        loginSubmitButton.click();

        // step4: filling password field
        WebElement passwordField = getDriver().findElement(By.id("password"));
        passwordField.sendKeys("inna1234");

        // step5: click login button
        loginSubmitButton.click();

        //waiting for private page loading to perform assert
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("button[data-testid = nav__profile-menu-trigger]")));


        WebElement profileMenuButton = getDriver().findElement(By.cssSelector("button[data-testid = nav__profile-menu-trigger]"));
        // opening profile menu
        profileMenuButton.click();
        WebElement profileMenuName = getDriver().findElement(By
                .cssSelector("div[data-testid=\"nav__profile-menu\"] div[data-ds--menu--heading-item=\"true\""));
        // getting profile name
        String profileName = profileMenuName.getText();

        //assert on profile name
        Assertions.assertEquals("INNA", profileName, "Wrong profile name" );
    }

    @DisplayName("Negative login test: user with wrong password cannot login")
    @Test
    void negativeTest() {
        // step2: filling username field
        WebElement loginField = getDriver().findElement(By.id("username"));
        loginField.sendKeys("chonkainna@mail.ru");

        // step3: click login button
        WebElement loginSubmitButton = getDriver().findElement(By.id("login-submit"));
        loginSubmitButton.click();

        // step4: filling password field
        WebElement passwordField = getDriver().findElement(By.id("password"));
        passwordField.sendKeys("inna1239");

        // step5: click login button
        loginSubmitButton.click();

        //waiting for error message
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("div[id=\"login-error\"]")));


        WebElement errorLoginLink = getDriver().findElement(By.cssSelector("div[id=\"login-error\"] span a"));
        String errorLink = errorLoginLink.getAttribute("href");
        String expectedLink = "https://id.atlassian.com/login/resetpassword?email=chonkainna%40mail.ru&errorCode";
        //assert on error message link
        Assertions.assertEquals(expectedLink, errorLink, "Wrong reset password link" );
    }

}
