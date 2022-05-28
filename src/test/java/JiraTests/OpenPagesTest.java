package JiraTests;

import JiraTests.utils.ConfigProperties;
import JiraTests.PageObjects.AccountSettingsPage;
import JiraTests.PageObjects.ProjectPage;
import JiraTests.PageObjects.StartPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

@Feature("Page navigation")
public class OpenPagesTest extends AbstractTestPrivate {

    @Story("Go to Account Settings page")
    @DisplayName("Open Account Settings page")
    @Description("User can open Account Settings page from Start page header menu")
    @Severity(SeverityLevel.MINOR)
    @Test
    void openAccountSettingsTest() {

       new StartPage()
               .openMenu()
               .openAccountSettingsPage();

        ArrayList<String> windowTabs = new ArrayList (getDriver().getWindowHandles());
        getDriver().switchTo().window(windowTabs.get(1));

        String name = new AccountSettingsPage()
                .getAccountSettingsName();

        //assert on profile name
        Assertions.assertEquals("Inna", name, "Wrong profile name");
    }

    @Story("Go to Profile page")
    @DisplayName("Open profile info")
    @Description("User can open Profile page from Project page header menu")
    @Severity(SeverityLevel.MINOR)
    @Test
    void openProfilePageTest() {

        new StartPage().openJiraProjectPage();

        String profileEmail = new ProjectPage()
                .openMenu()
                .openProfile()
                .getProfileEmail();

        Assertions.assertEquals(ConfigProperties.getProperty("LOGIN"), profileEmail, "Wrong email value");
    }
}
