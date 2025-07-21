package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BaseTest {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement productsHeader;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement menuButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement cartButton;

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc='test-PRODUCTS']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement productList;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement firstAddToCartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']/preceding-sibling::android.view.ViewGroup[1]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement shopTabButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='test-Item title'])[1]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement firstProductTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc='test-Price'])[1]")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement firstProductPrice;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Modal Selector Button']")
    @iOSXCUITFindBy(xpath = "TODO")
    private WebElement sortButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (low to high)']")
    @iOSXCUITFindBy(xpath = "TODO")

    private WebElement sortLowToHighOption;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'$')]")
    @iOSXCUITFindBy(xpath = "TODO")
    private List<WebElement> productPrices;


    public HomePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isProductsHeaderVisible() {
        try {
            return productsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMenuButtonVisible() {
        try {
            return menuButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openCart() {
        cartButton.click();
    }

    public void sortByPriceLowToHigh() {
        sortButton.click();
        sortLowToHighOption.click();
    }

    public WebElement getMenuButton() {
        return menuButton;
    }

    public List<Double> getVisibleProductPrices() {
        return productPrices.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public void addFirstProductToCart() {
        firstAddToCartButton.click();
    }

    public String getFirstProductTitle() {
        return firstProductTitle.getText();
    }

    public String getFirstProductPrice() {
        return firstProductPrice.getText();
    }
}
