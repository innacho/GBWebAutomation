package JiraTests.PageObjects;

import JiraTests.utils.DriverMethods;
import io.qameta.allure.Step;
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

    @Step("Fill login field")
    public LoginPage fillLoginField(String username){
        loginField.sendKeys(username);
        return this;
    }

    @Step("Click login button")
    public LoginPage clickLoginButton(){
        loginSubmitButton.click();
        return this;
    }

    @Step("Fill password field")
    public LoginPage fillPasswordField(String password){
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Get reset password link")
    public String getErrorLoginLink(){
        return errorLoginLink.getAttribute("href");
    }
}