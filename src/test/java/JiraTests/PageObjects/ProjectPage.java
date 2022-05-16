package JiraTests.PageObjects;

import JiraTests.Helpers.DriverMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public ProjectPage (){
        PageFactory.initElements(getDriver(), this);
    }

    public ProjectPage openMenu(){
        headerMenuButton.click();
        return this;
    }

    public ProjectPage openProfile(){
        profileMenu.click();
        return this;
    }

    public String getProfileEmail(){
        return profileEmail.getText();
    }

    public ProjectPage clickCreateButton(){
        createButton.click();
        return this;
    }

    public ProjectPage openIssueType(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        openIssueTypeButton.click();
        return this;
    }

    public ProjectPage selectIssueType(int number){
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
        return this;
    }

    public ProjectPage fillIssueSummaryField(String text){
        issueSummaryField.sendKeys(text);
        return this;
    }

    public ProjectPage fillIssueDescriptionField(String text){
        issueDescriptionField.sendKeys(text);
        return this;
    }

    public ProjectPage clickAssignToMeButton(){
        assignToMeButton.click();
        return this;
    }

    public ProjectPage clickCreateIssueButton(){
        createIssueButton.click();
        return this;
    }

    public boolean getSuccess(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return successAlert.isDisplayed();
    }

    public String getSuccessAreaText(){
        return successAreaText.getText();
    }

    public ProjectPage clickViewIssueLink(){
        viewIssueLink.click();
        return this;
    }

    public String getIssueTypeButtonText(){
        return issueTypeButton.getAttribute("aria-label");
    }
}
