package JiraTests.PageObjects;

import JiraTests.Helpers.DriverMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LogoutPage extends DriverMethods {

    @FindBy(id = "logout-submit")
    private WebElement logoutSubmitButton;

    public LogoutPage (){
        PageFactory.initElements(getDriver(), this);
    }

    public LogoutPage clickLogoutButton(){
        logoutSubmitButton.click();
        return this;
    }
}
