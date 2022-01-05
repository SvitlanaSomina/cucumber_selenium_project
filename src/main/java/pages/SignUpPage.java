package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage{

    @FindBy(xpath = "//input[@id = 'email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//p[@class = 'mb-0']")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[@data-optimizely-event = 'click.signup_continue.email']")
    private WebElement continueButton;

    @FindBy(xpath = "//p[contains(@class, \"password-validity-summary\")]")
    private WebElement warningMessage;


    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void openSignUpPage(String url) {
        driver.get(url);
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getWarningMessage() {
        return warningMessage;
    }

    public String getWarningMessageText() {
        return warningMessage.getText();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public String getWarningMessageColor() {
        String colorRgba = warningMessage.getCssValue("color");
        String colorHexCode = Color.fromString(colorRgba).asHex();
        return colorHexCode;
    }

    public void enterEmail(final String email) {
        emailField.sendKeys(email);
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.isDisplayed();
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void enterPassword(final String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
}
