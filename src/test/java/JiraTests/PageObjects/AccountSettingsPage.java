package JiraTests.PageObjects;

import JiraTests.Helpers.DriverMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSettingsPage extends DriverMethods {

    @FindBy(css = "div[data-test-selector=\"field-edit-full-name\"] div[data-test-selector=\"profile-about-item-read-view\"]")
    private WebElement accountSettingsName;

    public AccountSettingsPage (){
        PageFactory.initElements(getDriver(), this);
    }

    public String getAccountSettingsName(){
        return accountSettingsName.getText();
    }
}
