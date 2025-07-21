package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
    @iOSXCUITFindBy(xpath = "TODO")
    public WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement usernameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement passwordInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement menuButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGOUT']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement logoutButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement errorMessage;

    private final AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void login(String username, String password) {
        enterCredentials(username, password);
        loginButton.click();
        try {
            Thread.sleep(2000); // tymczasowy sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enterCredentials(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public boolean isLoginButtonVisible() {
        try {
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        menuButton.click();
        logoutButton.click();
    }

    public String getLoginErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }

}
