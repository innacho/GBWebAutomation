package JiraTests.PageObjects;

import JiraTests.utils.DriverMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountSettingsPage extends DriverMethods {

    @FindBy(css = "div[data-test-selector=\"field-edit-full-name\"] div[data-test-selector=\"profile-about-item-read-view\"]")
    private WebElement accountSettingsName;

    public AccountSettingsPage (){
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Get user name from Account Settings page")
    public String getAccountSettingsName(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("manage-profile"));
        return accountSettingsName.getText();
    }
}