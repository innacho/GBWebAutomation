package Cucumber;

import JiraTests.Helpers.AuthenticationMethods;
import JiraTests.PageObjects.LoginPage;
import JiraTests.PageObjects.StartPage;
import JiraTests.utils.ConfigProperties;
import JiraTests.utils.DriverMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraAuthCucumberTest extends AuthenticationMethods {
    @Given("on login page")
    public void open_login_page(){
        init();
        goToLogin();
        if(DriverMethods.getDriver().getCurrentUrl().contains(ConfigProperties.getProperty("START_PAGE"))) return;
    }

    @When("password is {string}")
    public void fill_credentials(String password){
        new LoginPage()
                .fillLoginField(ConfigProperties.getProperty("LOGIN"))
                .clickLoginButton()
                .fillPasswordField(password)
                .clickLoginButton();

    }

    @Then("auth result is {string}")
    public void perform_assert(String expectedName){
        String name = new StartPage()
                .openMenu()
                .getProfileMenuName();

        //assert on profile name
        Assertions.assertEquals(expectedName, name, "Wrong profile name" );
        close();
    }

    @Then("false auth result is {string}")
    public void not_authorized_assert(String result){
        String errorLink = new LoginPage().getErrorLoginLink();
        //assert on error message link
        Assertions.assertTrue(errorLink.contains(result));
        close();
    }
}
