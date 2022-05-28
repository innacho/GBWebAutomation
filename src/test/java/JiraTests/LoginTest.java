package JiraTests;

import JiraTests.PageObjects.LoginPage;
import JiraTests.PageObjects.StartPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Feature("Authentication")
public class LoginTest extends AbstractTestPublic {

    @DisplayName("Positive atlassian system login test")
    @Description("User with valid credentials can login to atlassian")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void positiveTest() {
        loginMethod("PASSWORD");

        String name = new StartPage()
                .openMenu()
                .getProfileMenuName();

        //assert on profile name
        Assertions.assertEquals("INNA", name, "Wrong profile name" );
    }

    @DisplayName("Negative atlassian system login test")
    @Description("User with wrong password can't login to atlassian")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void negativeTest() {
        loginMethod("WRONG_PASSWORD");

        String errorLink = new LoginPage().getErrorLoginLink();
        String expectedLink = "https://id.atlassian.com/login/resetpassword?email=chonkainna%40mail.ru&errorCode";
        //assert on error message link
        Assertions.assertEquals(expectedLink, errorLink, "Wrong reset password link" );
    }

}