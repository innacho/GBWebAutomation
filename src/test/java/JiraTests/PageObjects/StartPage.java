package JiraTests.PageObjects;

import JiraTests.utils.DriverMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage extends DriverMethods {

    @FindBy(css = "button[data-testid = nav__profile-menu-trigger]")
    private WebElement startPageMenuButton;

    @FindBy(css = "button[data-testid=\"nav__logout-btn\"]")
    private WebElement startPageLogoutButton;

    @FindBy(css="div[data-testid=\"nav__profile-menu\"] div[data-ds--menu--heading-item=\"true\"")
    private WebElement profileMenuName;

    @FindBy(css="a[data-testid=\"nav__manage-profile-btn\"]")
    private WebElement accountSettingsLink;

    @FindBy(css="div[data-testid=\"product-container\"] a:nth-child(1) button")
    private WebElement jiraProjectButton;

    public StartPage (){
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Open header menu on the start page")
    public StartPage openMenu(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(startPageMenuButton));
        startPageMenuButton.click();
        return this;
    }

    @Step("Click \"logout\" in header drop-down menu on the start page")
    public StartPage clickLogoutButton(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(startPageLogoutButton));
        startPageLogoutButton.click();
        return this;
    }

    @Step("Get profile name from header drop-down menu on the start page")
    public String getProfileMenuName(){
        return profileMenuName.getText();
    }

    @Step("Click \"Account Settings\" link in header drop-down menu on the start page")
    public StartPage openAccountSettingsPage(){
        accountSettingsLink.click();
        return this;
    }

    @Step("Click \"JIRA project\" button on the start page")
    public StartPage openJiraProjectPage(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(jiraProjectButton));
        jiraProjectButton.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("/jira/projects"));
        return this;
    }
}