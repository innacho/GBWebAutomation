package Selenide;

import JiraTests.utils.ConfigProperties;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class JiraAuthSelenideTest {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
    }

    @Test
    void jiraAuthPositiveTest(){
        open(ConfigProperties.getProperty("LOGIN_PAGE"));
        $(By.id("username")).sendKeys(ConfigProperties.getProperty("LOGIN"));
        $(By.id("login-submit")).click();
        $(By.id("password")).sendKeys(ConfigProperties.getProperty("PASSWORD"));
        $(By.id("login-submit")).submit();
        SelenideElement profileMenuButton = $("button[data-testid = nav__profile-menu-trigger]").shouldBe(visible);
        profileMenuButton.click();
        $("div[data-testid=\"nav__profile-menu\"] div[data-ds--menu--heading-item=\"true\"").shouldHave(text("INNA"));
    }

}
