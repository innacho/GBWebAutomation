package JiraTests;
import org.junit.jupiter.api.*;

public class AbstractTestPublic extends AuthenticationMethods{

    @BeforeAll
    static void initCall(){
       init();
    }

    @BeforeEach
    void goToCall(){
        goTo();
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
