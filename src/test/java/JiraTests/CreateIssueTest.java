package JiraTests;

import JiraTests.PageObjects.ProjectPage;
import JiraTests.PageObjects.StartPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CreateIssueTest extends AbstractTestPrivate{

    @ParameterizedTest
    @CsvFileSource(resources = "/issueTypesParams.csv")
    void createIssueTest(String type, int number) {

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

        boolean isSuccess = new ProjectPage()
                .clickCreateButton()
                .openIssueType()
                .selectIssueType(number)
                .fillIssueSummaryField("Test summary description for " + type)
                .fillIssueDescriptionField("Test description for testing description field for type "+type)
                .clickAssignToMeButton()
                .clickCreateIssueButton()
                .getSuccess();

        Assertions.assertTrue(isSuccess);

        String successText = new ProjectPage().getSuccessAreaText();
        Assertions.assertTrue(successText.contains("created"));

        String buttonText = new ProjectPage()
                .clickViewIssueLink()
                .getIssueTypeButtonText();
        Assertions.assertTrue(buttonText.contains(type));
    }
}
