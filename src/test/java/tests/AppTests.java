package tests;
import config.ConfigMenager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    @Test(priority = 1, description = "Verifies that a user can log in with valid credentials and is navigated to the home screen.")
    public void successfulLoginNavigatesToHome() {
        loginPage.login( ConfigMenager.getUser("normal.user"), ConfigMenager.getUser("password"));
        Assert.assertTrue(homePage.isMenuButtonVisible(), "Menu button should be visible after login");
    }

    @Test(priority = 2, description ="Checks that products are correctly sorted in ascending order by price.")
    public void productsAreSortedByPriceLowToHigh() {
        homePage.sortByPriceLowToHigh();
        List<Double> prices = homePage.getVisibleProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Assert.assertEquals(prices, sorted, "Prices are not sorted Low to High");
    }

    @Test(priority = 3, description = "Tests the complete purchase flow from adding an item to the cart through successful checkout.")
    public void userCanAddItemToCartAndCheckout() {
        String expectedTitle = homePage.getFirstProductTitle();
        String expectedPrice = homePage.getFirstProductPrice();

        homePage.addFirstProductToCart();
        homePage.openCart();
        checkoutPage.proceedToCheckout("Jakub", "Zembrowski", "00-001", expectedTitle, expectedPrice);
        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout should complete successfully");
    }
}