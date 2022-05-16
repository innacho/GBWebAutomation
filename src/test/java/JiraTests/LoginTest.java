package JiraTests;

import JiraTests.PageObjects.LoginPage;
import JiraTests.PageObjects.StartPage;
import org.junit.jupiter.api.*;


public class LoginTest extends AbstractTestPublic {

    @DisplayName("Positive login test")
    @Test
    void positiveTest() {
        loginMethod("PASSWORD");

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String name = new StartPage()
                .openMenu()
                .getProfileMenuName();

        //assert on profile name
        Assertions.assertEquals("INNA", name, "Wrong profile name" );
    }

    @DisplayName("Negative login test: user with wrong password cannot login")
    @Test
    void negativeTest() {
        loginMethod("WRONG_PASSWORD");

        String errorLink = new LoginPage().getErrorLoginLink();
        String expectedLink = "https://id.atlassian.com/login/resetpassword?email=chonkainna%40mail.ru&errorCode";
        //assert on error message link
        Assertions.assertEquals(expectedLink, errorLink, "Wrong reset password link" );
    }

}
