package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;

import java.time.Duration;

public class CheckoutPage {

    private final Helpers helpers;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CHECKOUT']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement checkoutButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-First Name']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement firstNameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Last Name']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement lastNameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Zip/Postal Code']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement zipCodeInput;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CONTINUE']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement continueButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-FINISH']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement finishButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement checkoutCompleteHeader;

    public CheckoutPage(AppiumDriver driver) {
        this.helpers = new Helpers(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void proceedToCheckout(String firstName, String lastName, String zipCode) {
        startCheckout();
        fillCheckoutForm(firstName, lastName, zipCode);
        finishCheckout();
    }

    public boolean isCheckoutComplete() {
        try {
            return checkoutCompleteHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void startCheckout() {
        checkoutButton.click();
    }

    private void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        zipCodeInput.sendKeys(zipCode);
        continueButton.click();
    }

    private void finishCheckout() {
        helpers.scrollToElement(By.xpath("//android.view.ViewGroup[@content-desc='test-FINISH']"), 5);
        finishButton.click();
    }
}
