package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class AppTests extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void successfulLoginNavigatesToHome() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.isMenuButtonVisible(), "Menu button should be visible after login");
    }

    @Test(priority = 2)
    public void userCanAddItemToCartAndCheckout() {
        homePage.addFirstProductToCart();
        homePage.openCart();
        checkoutPage.proceedToCheckout("Jakub", "Zembrowski", "00-001");
        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout should complete successfully");
    }
}