package JiraTests.PageObjects;

import JiraTests.utils.DriverMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage extends DriverMethods {

    @FindBy(id = "logout-submit")
    private WebElement logoutSubmitButton;

    public LogoutPage (){
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Click confirm logout button")
    public LogoutPage clickLogoutButton(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutSubmitButton));
        logoutSubmitButton.click();
        return this;
    }
}