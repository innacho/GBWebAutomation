package JiraTests.PageObjects;

import JiraTests.utils.DriverMethods;
import JiraTests.utils.MyUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProjectPage extends DriverMethods {

    @FindBy(css = "span[data-test-id=\"ak-spotlight-target-profile-spotlight\"]")
    private WebElement headerMenuButton;

    @FindBy(css="div[role=\"group\"]:nth-child(2) div[data-ds--menu--heading-item=\"true\"]+a[href=\"/jira/people/60212468988758006877a1a6\"]")
    private WebElement profileMenu;

    @FindBy(css="div[data-test-selector=\"profile-about-item-email\"]")
    private WebElement profileEmail;

    @FindBy(id="createGlobalItem")
    private WebElement createButton;

    @FindBy(css="div[id=\"issue-create.ui.modal.create-form.type-picker.issue-type-select\"] span[aria-label=\"open\"]")
    private WebElement openIssueTypeButton;

    @FindBy(id="summary-field")
    private WebElement issueSummaryField;

    @FindBy(css="div[aria-label=\"Main content area\"] p")
    private WebElement issueDescriptionField;

    @FindBy(css="button[data-testid=\"issue-create-commons.ui.assignee-field.assing-to-me-button\"]")
    private WebElement assignToMeButton;

    @FindBy(css="button[data-testid=\"issue-create.common.ui.footer.create-button\"]")
    private WebElement createIssueButton;

    @FindBy(css="div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"]")
    private WebElement successAlert;

    @FindBy(css="span[aria-label=\"Success\"] + span")
    private WebElement successAreaText;

    @FindBy(css="div[data-testid=\"jira-issue-create.modal.create-form.success-flag\"] div a")
    private WebElement viewIssueLink;

    @FindBy(css="div[data-test-id=\"issue.views.issue-base.foundation.breadcrumbs.breadcrumb-current-issue-container\"] button")
    private WebElement issueTypeButton;

    @FindBy(id="issue-create.ui.modal.modal-body")
    private WebElement createIssueModal;

    public ProjectPage (){
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Open header drop-down menu on the JIRA project page")
    public ProjectPage openMenu(){
        try {
            headerMenuButton.click();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            MyUtils.makeScreenshot(getDriver(),
                    "failure- org.example.projectPage.openMenu" + System.currentTimeMillis() + ".png");
        }
        return this;
    }


    @Step("Click \"Profile\" link in header drop-down menu on the JIRA project page")
    public ProjectPage openProfile(){
        profileMenu.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("jira/people"));
        return this;
    }

    @Step("Get profile email on profile page")
    public String getProfileEmail(){
        return profileEmail.getText();
    }

    @Step("Click \"Create\" button in header the JIRA project page")
    public ProjectPage clickCreateButton(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("chonkaproject.atlassian.net"));

        createButton.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(createIssueModal));
        return this;
    }

    @Step("Open issue types list on Create issue modal")
    public ProjectPage openIssueType(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(openIssueTypeButton));
        openIssueTypeButton.click();
        return this;
    }

    @Step("Select issue type on Create issue modal")
    public ProjectPage selectIssueType(int number){
        try {
            WebElement selectTypeButton = new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("react-select-4-option-"+ number)));
            selectTypeButton.click();
        } catch (NoSuchElementException | TimeoutException e) { }
        return this;
    }

    @Step("Fill issue summary on Create issue modal")
    public ProjectPage fillIssueSummaryField(String text){
        issueSummaryField.sendKeys(text);
        return this;
    }

    @Step("Fill issue description on Create issue modal")
    public ProjectPage fillIssueDescriptionField(String text){
        issueDescriptionField.sendKeys(text);
        return this;
    }

    @Step("Click \"Assign to me\" button on Create issue modal")
    public ProjectPage clickAssignToMeButton(){
        assignToMeButton.click();
        return this;
    }

    @Step("Click \"Create issue\" button on Create issue modal")
    public ProjectPage clickCreateIssueButton(){
        createIssueButton.click();
        return this;
    }

    @Step("Check if success alert is visible")
    public boolean getSuccess(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(successAlert));

        return successAlert.isDisplayed();
    }

    @Step("Get success alert text")
    public String getSuccessAreaText(){
        return successAreaText.getText();
    }

    @Step("Click \"View issue\" link on the success alert")
    public ProjectPage clickViewIssueLink(){
        viewIssueLink.click();
        return this;
    }

    @Step("Get issue type from issue button")
    public String getIssueTypeButtonText(){
        return issueTypeButton.getAttribute("aria-label");
    }

}