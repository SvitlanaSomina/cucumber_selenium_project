package manager;

import org.openqa.selenium.WebDriver;
import pages.SignUpPage;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public SignUpPage getSignUpPage() {
        return new SignUpPage(driver);
    }
}