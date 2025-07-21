package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Helpers;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private final AppiumDriver driver;
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

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "TODO")
    private static WebElement checkoutErrorMessage;

    public CheckoutPage(AppiumDriver driver) {
        this.driver = driver;
        this.helpers = new Helpers(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void proceedToCheckout(String firstName, String lastName, String zipCode, String expectedTitle, String expectedPrice) {
        startCheckout();
        fillCheckoutForm(firstName, lastName, zipCode, expectedTitle, expectedPrice);
        finishCheckout();
    }

    public boolean isCheckoutComplete() {
        try {
            return checkoutCompleteHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void errorMessageCheckout(String firstName, String lastName, String zipCode) {

        List<Runnable> steps = List.of(
                () -> {},
                () -> firstNameInput.sendKeys(firstName),
                () -> lastNameInput.sendKeys(lastName)
        );

        List<String> expectedErrors = List.of(
                "First Name is required",
                "Last Name is required",
                "Postal Code is required"
        );

        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).run();
            continueButton.click();
            String actualError = CheckoutPage.getCheckoutErrorMessage();
            Assert.assertEquals(actualError, expectedErrors.get(i), "Nieprawidłowy komunikat błędu na kroku " + (i + 1));
        }
        zipCodeInput.sendKeys(zipCode);
        ;
        continueButton.click();
    }

    public void startCheckout() {
        checkoutButton.click();
    }

    private void fillCheckoutForm(String firstName, String lastName, String zipCode, String expectedTitle, String expectedPrice) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        zipCodeInput.sendKeys(zipCode);
        continueButton.click();

        CheckoutOverviewPage checkoutOverview = new CheckoutOverviewPage(driver);
        checkoutOverview.verifyProductDetails(0, expectedTitle, expectedPrice);
    }

    private void finishCheckout() {
        helpers.scrollToElement(By.xpath("//android.view.ViewGroup[@content-desc='test-FINISH']"), 5);
        finishButton.click();
    }

    private static String getCheckoutErrorMessage() {
        try {
            return checkoutErrorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }
}
