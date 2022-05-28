package JiraTests.Helpers;

import JiraTests.PageObjects.LoginPage;
import JiraTests.PageObjects.LogoutPage;
import JiraTests.PageObjects.StartPage;
import JiraTests.utils.ConfigProperties;
import JiraTests.utils.DriverMethods;

public class AuthenticationMethods extends DriverMethods {

    public void loginMethod(String passwordKey){
        goToLogin();
        if(DriverMethods.getDriver().getCurrentUrl().contains(ConfigProperties.getProperty("START_PAGE"))) return;

        new LoginPage()
                .fillLoginField(ConfigProperties.getProperty("LOGIN"))
                .clickLoginButton()
                .fillPasswordField(ConfigProperties.getProperty(passwordKey))
                .clickLoginButton();
    }

    public void logoutMethod(){
        DriverMethods.getDriver().navigate().to(ConfigProperties.getProperty("START_PAGE"));
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // return if not authorized
        if(DriverMethods.getDriver().getCurrentUrl().contains("id.atlassian.com")) return;

        new StartPage()
                .openMenu()
                .clickLogoutButton();
        new LogoutPage()
                .clickLogoutButton();
    }
}