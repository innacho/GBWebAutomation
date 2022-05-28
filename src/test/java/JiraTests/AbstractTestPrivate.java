package JiraTests;
import JiraTests.Helpers.AuthenticationMethods;
import JiraTests.utils.MyUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
        MyUtils.getLogs(getDriver());
        clearCookies();
    }

    @AfterAll
    static void closeCall() {
        close();
    }

    @Step("Add screenshot to report")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}