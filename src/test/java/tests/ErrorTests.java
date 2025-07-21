package tests;

import config.ConfigMenager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

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

    @Test(priority = 1, description = "Verify error message for locked user")
    public void lockedUser() {
        loginPage.login(ConfigMenager.getUser("locked.user"), ConfigMenager.getUser("password"));
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Sorry, this user has been locked out.");
    }

    @Test(priority = 2, description = "Verify error message for empty user")
    public void loginWithoutUsername() {
        loginPage.login("", ConfigMenager.getUser("password"));
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Username is required");
    }

    @Test(priority = 3, description = "Verify error message for empty Password")
    public void loginWithoutPassword() {
        loginPage.login(ConfigMenager.getUser("normal.user"), "");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Password is required");
    }

    @Test(priority = 4, description = "Verify error message for First Name Last Name and zipCode on the checkout screen")
    public void validateCheckoutRequiredFields() {

        loginPage.login(ConfigMenager.getUser("normal.user"), ConfigMenager.getUser("password"));
        homePage.addFirstProductToCart();
        homePage.openCart();
        checkoutPage.startCheckout();
        checkoutPage.errorMessageCheckout("Jakub", "Zembrowski", "00-001");

    }


}
