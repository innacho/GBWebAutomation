package JiraTests;

import JiraTests.PageObjects.ProjectPage;
import JiraTests.PageObjects.StartPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Feature("Create JIRA issue")
public class CreateIssueTest extends AbstractTestPrivate{

    @ParameterizedTest
    @DisplayName("Creating JIRA issues of different types")
    @Description("Parameterized test, which creates issues of all possible types and assigns them to current user")
    @Severity(SeverityLevel.NORMAL)
    @CsvFileSource(resources = "/issueTypesParams.csv")
    void createIssueTest(String type, int number) throws Exception {

        new StartPage().openJiraProjectPage();

        boolean isSuccess = new ProjectPage()
                .clickCreateButton()
                .openIssueType()
                .selectIssueType(number)
                .fillIssueSummaryField("Test summary description for " + type)
                .fillIssueDescriptionField("Test description for testing description field for type "+type)
                .clickAssignToMeButton()
                .clickCreateIssueButton()
                .getSuccess();

        try {
            Assertions.assertTrue(isSuccess);

            String successText = new ProjectPage().getSuccessAreaText();
            Assertions.assertTrue(successText.contains("created"));

            String buttonText = new ProjectPage()
                    .clickViewIssueLink()
                    .getIssueTypeButtonText();
            Assertions.assertTrue(buttonText.contains(type));
        } catch ( AssertionFailedError e){
            Logger logger = LoggerFactory.getLogger("assertions");
            logger.error("Test assertion failed "+ e.getMessage());
            saveScreenshot();
            throw e;
        }
    }
}