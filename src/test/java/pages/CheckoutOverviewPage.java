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

public class CheckoutOverviewPage {

    private final AppiumDriver driver;
    private final Helpers helpers;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
    @iOSXCUITFindBy(xpath = "TODO")
    private List<WebElement> productItems;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Item total')]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement itemTotal;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Tax')]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement tax;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Total')]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement total;

    public CheckoutOverviewPage(AppiumDriver driver) {
        this.driver = driver;
        this.helpers = new Helpers(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void verifyProductDetails(int index, String expectedTitle, String expectedPrice) {
        String actualTitle = getProductTitle(index);
        String actualPrice = getProductPrice(index);

        Assert.assertEquals(actualTitle, expectedTitle, "Product title mismatch!");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch!");
    }

    public String getProductTitle(int index) {
        return productItems.get(index)
                .findElement(By.xpath(".//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]"))
                .getText();
    }

    public String getProductPrice(int index) {
        return productItems.get(index)
                .findElement(By.xpath(".//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView"))
                .getText();
    }

    public String getItemTotal() {
        return itemTotal.getText();
    }

    public String getTax() {
        return tax.getText();
    }

    public String getTotal() {
        return total.getText();
    }
}
