package JiraTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class OpenPagesTest extends AbstractTestPrivate {

    @DisplayName("Open Account Settings page")
    @Test
    void openAccountSettingsTest() {

        //waiting for private page loading
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("button[data-testid = nav__profile-menu-trigger]")));

        // opening header profile menu
        WebElement profileButton = getDriver().findElement(By.cssSelector("button[data-testid=\"nav__profile-menu-trigger\"]"));
        profileButton.click();

        // opening account settings
        WebElement accountSettingsButton = getDriver().findElement(By.cssSelector("a[data-testid=\"nav__manage-profile-btn\"]"));
        accountSettingsButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ArrayList<String> windowTabs = new ArrayList (getDriver().getWindowHandles());
        getDriver().switchTo().window(windowTabs.get(1));

        //waiting for account settings page loading
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("div[data-test-selector=\"field-edit-full-name\"] div[data-test-selector=\"profile-about-item-read-view\"]")));

        // getting account settings page username
        WebElement accountSettingsName = getDriver().findElement(By.cssSelector("div[data-test-selector=\"field-edit-full-name\"] div[data-test-selector=\"profile-about-item-read-view\"]"));
        String profileName = accountSettingsName.getText();

        //assert on profile name
        Assertions.assertEquals("Inna", profileName, "Wrong profile name");
    }

    @DisplayName("Open profile page")
    @Test
    void openProfilePageTest() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // opening jira product
        WebElement jiraProductButton = getDriver().findElement(By.cssSelector("div[data-testid=\"product-container\"] a:nth-child(1) button"));
        jiraProductButton.click();

        // waiting for jira page to load
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.elementToBeClickable(By
                        .cssSelector("span[data-test-id=\"ak-spotlight-target-profile-spotlight\"]")));

        // opening header profile menu
        WebElement headerProfileMenuButton = getDriver().findElement(By.cssSelector("span[data-test-id=\"ak-spotlight-target-profile-spotlight\"]"));
        headerProfileMenuButton.click();

        // click profile menu element
        WebElement profileMenuElement = getDriver().findElement(By
                .cssSelector("div[role=\"group\"]:nth-child(2) div[data-ds--menu--heading-item=\"true\"]+a[href=\"/jira/people/60212468988758006877a1a6\"]"));
        profileMenuElement.click();

        //assert on profile email
        WebElement profileEmail = getDriver().findElement(By.cssSelector("div[data-test-selector=\"profile-about-item-email\"]"));
        String email = profileEmail.getText();

        Assertions.assertEquals("chonkainna@mail.ru", email, "Wrong email value");
    }
}
