package JiraTests;
import JiraTests.Helpers.AuthenticationMethods;
import org.junit.jupiter.api.*;

public class AbstractTestPrivate extends AuthenticationMethods {

    @BeforeAll
    static void initCall(){
        init();
    }

    @BeforeEach
    void goToCall(){
        goToLogin();
        loginMethod("PASSWORD");
    }

    @AfterEach
    void logoutCall(){
        clearCookies();
    }

    @AfterAll
    static void closeCall() {
        close();
    }
}
