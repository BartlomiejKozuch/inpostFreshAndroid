package tests;

import config.ConfigMenager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

import java.util.List;

public class ErrorTests extends BaseTest {

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
    public void lockedUser() {
        loginPage.login(ConfigMenager.getUser("locked.user"), ConfigMenager.getUser("password"));
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Sorry, this user has been locked out.");
    }

    @Test(priority = 2)
    public void loginWithoutUsername() {
        loginPage.login("", ConfigMenager.getUser("password"));
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Username is required");
    }

    @Test(priority = 3)
    public void loginWithoutPassword() {
        loginPage.login(ConfigMenager.getUser("normal.user"), "");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Password is required");
    }

    @Test(priority = 4)
    public void validateCheckoutRequiredFields() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Zaloguj się i przejdź do checkout
        loginPage.login(ConfigMenager.getUser("normal.user"), ConfigMenager.getUser("password"));
        homePage.addFirstProductToCart();
        homePage.openCart();
        checkoutPage.startCheckout();
        checkoutPage.errorMessageCheckout("Jakub", "Zembrowski", "00-001");

    }


}
