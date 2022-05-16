package JiraTests.PageObjects;

import JiraTests.Helpers.DriverMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LoginPage extends DriverMethods {

    @FindBy(id = "username")
    private WebElement loginField;

    @FindBy(id = "login-submit")
    private WebElement loginSubmitButton;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "div[id=\"login-error\"] span a")
    private WebElement errorLoginLink;

    public LoginPage (){
        PageFactory.initElements(getDriver(), this);
    }

    public LoginPage fillLoginField(String username){
        loginField.sendKeys(username);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginSubmitButton.click();
        return this;
    }

    public LoginPage fillPasswordField(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public String getErrorLoginLink(){
        return errorLoginLink.getAttribute("href");
    }
}
