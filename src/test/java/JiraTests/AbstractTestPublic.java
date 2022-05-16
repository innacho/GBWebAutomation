package JiraTests;
import JiraTests.Helpers.AuthenticationMethods;
import org.junit.jupiter.api.*;

public class AbstractTestPublic extends AuthenticationMethods {

    @BeforeAll
    static void initCall(){
       init();
    }

    @BeforeEach
    void goToCall(){
        goToLogin();
    }

    @AfterEach
    void logoutCall(){
       logoutMethod();
    }

    @AfterAll
    static void closeCall() {
        close();
    }
}
