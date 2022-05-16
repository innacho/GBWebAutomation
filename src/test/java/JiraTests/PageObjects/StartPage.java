package JiraTests.PageObjects;

import JiraTests.Helpers.DriverMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

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

    public StartPage openMenu(){
        startPageMenuButton.click();
        return this;
    }

    public StartPage clickLogoutButton(){
        startPageLogoutButton.click();
        return this;
    }

    public String getProfileMenuName(){
        return profileMenuName.getText();
    }

    public StartPage openAccountSettingsPage(){
        accountSettingsLink.click();
        return this;
    }

    public StartPage openJiraProjectPage(){
        jiraProjectButton.click();
        return this;
    }
}
