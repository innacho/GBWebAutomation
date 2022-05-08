package JiraTests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueTest extends AbstractTestPrivate{

    @ParameterizedTest
    @CsvFileSource(resources = "/issueTypesParams.csv")
    void createIssueTest(String type, int number) {
    //waiting for private page loading
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("button[data-testid = nav__profile-menu-trigger]")));

        // opening jira product
        WebElement jiraProductButton = getDriver().findElement(By.cssSelector("div[data-testid=\"product-container\"] a:nth-child(1) button"));
        jiraProductButton.click();

        // waiting for jira page to load
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.id("createGlobalItem")));

        // click "create" button
        WebElement createButton = getDriver().findElement(By.id("createGlobalItem"));
        createButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

       //  open issue type list
        WebElement openIssueTypeButton = getDriver().findElement(By
                .cssSelector("div[id=\"issue-create.ui.modal.create-form.type-picker.issue-type-select\"] span[aria-label=\"open\"]"));
        openIssueTypeButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // select issue type
        try {
           WebElement selectTypeButton = getDriver().findElement(By
                   .id("react-select-4-option-"+number));
           selectTypeButton.click();
       } catch (NoSuchElementException e) { }

        // filling issue summary field
        WebElement issueSummaryField = getDriver().findElement(By.id("summary-field"));
        issueSummaryField.sendKeys("Test summary description for " + type);

        // filling issue description field
        WebElement issueDescriptionField = getDriver().findElement(By.cssSelector("div[aria-label=\"Main content area\"] p"));
        issueDescriptionField.sendKeys("Test description for testing description field for type "+type);

        // click "Assign to me" button
        WebElement assignToMeButton = getDriver().findElement(By
                .cssSelector("button[data-testid=\"issue-create-commons.ui.assignee-field.assing-to-me-button\"]"));
        assignToMeButton.click();

        // step9: click "Create Issue" button
        WebElement createIssueButton = getDriver().findElement(By
                .cssSelector("button[data-testid=\"issue-create.ui.modal.footer.create-button\"]"));
        createIssueButton.click();

        // wait to perform assert
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .cssSelector("div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"]")));

        WebElement successAlert = getDriver().findElement(By
                .cssSelector("div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"]"));
        boolean isSuccess = successAlert.isDisplayed();
        Assertions.assertTrue(isSuccess);

        // getting success area text to assert
        WebElement successAreaText = getDriver().findElement(By.cssSelector("span[aria-label=\"Success\"] + span"));
        String successText = successAreaText.getText();
        Assertions.assertTrue(successText.contains("created"));

        WebElement viewIssueLink = getDriver().findElement(By
                .cssSelector("div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"] div a"));
        viewIssueLink.click();

        WebElement issueTypeButton = getDriver().findElement(By
                .cssSelector("div[data-test-id=\"issue.views.issue-base.foundation.breadcrumbs.breadcrumb-current-issue-container\"] button"));
        String buttonText = issueTypeButton.getAttribute("aria-label");
        Assertions.assertTrue(buttonText.contains(type));
    }
}
