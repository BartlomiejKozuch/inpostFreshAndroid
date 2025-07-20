package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage  {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement usernameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement passwordInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement loginButton;

    public LoginPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void login(String username, String password) {
        enterCredentials(username, password);
        loginButton.click();
        try {
            Thread.sleep(5000);  // DO zmiany później
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enterCredentials(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public boolean isLoginButtonVisible() {
        try {
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
