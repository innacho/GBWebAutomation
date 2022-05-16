package JiraTests;

import JiraTests.Helpers.ConfigProperties;
import JiraTests.PageObjects.AccountSettingsPage;
import JiraTests.PageObjects.ProjectPage;
import JiraTests.PageObjects.StartPage;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class OpenPagesTest extends AbstractTestPrivate {

    @DisplayName("Open Account Settings page")
    @Test
    void openAccountSettingsTest() {

       new StartPage()
               .openMenu()
               .openAccountSettingsPage();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ArrayList<String> windowTabs = new ArrayList (getDriver().getWindowHandles());
        getDriver().switchTo().window(windowTabs.get(1));

        String name = new AccountSettingsPage()
                .getAccountSettingsName();

        //assert on profile name
        Assertions.assertEquals("Inna", name, "Wrong profile name");
    }

    @DisplayName("Open profile info")
    @Test
    void openProfilePageTest() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        new StartPage().openJiraProjectPage();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        String profileEmail = new ProjectPage()
                .openMenu()
                .openProfile()
                .getProfileEmail();

        Assertions.assertEquals(ConfigProperties.getProperty("LOGIN"), profileEmail, "Wrong email value");
    }
}
