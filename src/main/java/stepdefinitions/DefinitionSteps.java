package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignUpPage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 90;
    WebDriver driver;
    SignUpPage signUpPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        signUpPage = pageFactoryManager.getSignUpPage();
        signUpPage.openSignUpPage(url);
    }

    @When("User enters email {string} into email field")
    public void enterEmail(final String email) {
        signUpPage = pageFactoryManager.getSignUpPage();
        signUpPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signUpPage.getEmailField());
        signUpPage.enterEmail(email);
    }

    @Then("User checks error message visibility")
    public void checkErrorMessageVisibility() {
        signUpPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        signUpPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signUpPage.getErrorMessage());
        assertTrue(signUpPage.isErrorMessageVisible());
    }

    @And("User checks that 'Continue' button is disabled")
    public void checkContinueButtonIsDisabled() {
        assertFalse(signUpPage.isContinueButtonEnabled());
    }

    @And("User clicks 'Continue' button")
    public void clickContinueButton() throws InterruptedException {
        sleep(1500);
        signUpPage.clickContinueButton();
    }

    @When("User enters {string} into password field")
    public void enterPassword(final String password) {
        signUpPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signUpPage.getPasswordField());
        signUpPage.enterPassword(password);
    }

    @Then("User checks that the warning message is {string}")
    public void checkWarningMessageText(final String expectedWarningText) {
        signUpPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signUpPage.getWarningMessage());
        assertEquals(signUpPage.getWarningMessageText(), expectedWarningText);
    }

    @And("User checks that warning message is marked {string}")
    public void checkColorWarningMessageText(final String expectedColor){
        assertEquals(signUpPage.getWarningMessageColor(), expectedColor);
    }

    @And("User checks that 'Continue' button is enabled")
    public void checkContinueButtonIsEnabled() {
        assertTrue(signUpPage.isContinueButtonEnabled());
    }

    @Then("User checks that the error message is {string}")
    public void checkErrorMessageText(final String expectedErrorText) {
        signUpPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signUpPage.getErrorMessage());
        assertEquals(signUpPage.getErrorMessageText(), expectedErrorText);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

