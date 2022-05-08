package JiraTests;
import org.junit.jupiter.api.*;

public class AbstractTestPrivate extends AuthenticationMethods{

    @BeforeAll
    static void initCall(){
        init();
    }

    @BeforeEach
    void goToCall(){
        goTo();
        loginMethod();
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
