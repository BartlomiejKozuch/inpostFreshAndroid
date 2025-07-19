package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.BaseTest;

public class LoginPage extends BaseTest {

    private final AppiumDriver driver;

    private final By usernameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton   = AppiumBy.accessibilityId("test-LOGIN");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        enterCredentials(username, password);
        driver.findElement(loginButton).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    private void enterCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public boolean isLoginButtonVisible() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
