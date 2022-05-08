package JiraTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AuthenticationMethods extends DriverMethods {

    public void loginMethod(){
        goTo();
        if(getDriver().getCurrentUrl().contains("https://start.atlassian.com/")) return;
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
    }

    void logoutMethod(){
        getDriver().navigate().to("https://start.atlassian.com/");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // return if not authorized
        if(getDriver().getCurrentUrl().contains("id.atlassian.com")) return;

        // opening profile menu
        WebElement profileMenuButton = getDriver().findElement(By.cssSelector("button[data-testid = nav__profile-menu-trigger]"));
        profileMenuButton.click();

        // click logout button
        WebElement profileMenuLogoutButton = getDriver().findElement(By
                .cssSelector("button[data-testid=\"nav__logout-btn\"]"));
        profileMenuLogoutButton.click();

        // confirm logout
        WebElement logoutSubmitButton = getDriver().findElement(By.id("logout-submit"));
        logoutSubmitButton.click();
    }
}
