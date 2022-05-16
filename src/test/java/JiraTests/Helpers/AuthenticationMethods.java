package JiraTests.Helpers;

import JiraTests.Helpers.ConfigProperties;
import JiraTests.Helpers.DriverMethods;
import JiraTests.PageObjects.LoginPage;
import JiraTests.PageObjects.LogoutPage;
import JiraTests.PageObjects.StartPage;

public class AuthenticationMethods extends DriverMethods {

    public void loginMethod(String passwordKey){
        goToLogin();
        if(getDriver().getCurrentUrl().contains(ConfigProperties.getProperty("START_PAGE"))) return;

        new LoginPage()
                .fillLoginField(ConfigProperties.getProperty("LOGIN"))
                .clickLoginButton()
                .fillPasswordField(ConfigProperties.getProperty(passwordKey))
                .clickLoginButton();
    }

    public void logoutMethod(){
        getDriver().navigate().to(ConfigProperties.getProperty("START_PAGE"));
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // return if not authorized
        if(getDriver().getCurrentUrl().contains("id.atlassian.com")) return;

        new StartPage()
                .openMenu()
                .clickLogoutButton();
        new LogoutPage()
                .clickLogoutButton();
    }
}
