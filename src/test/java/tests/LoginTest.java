package tests;

import config.ConfigMenager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;
import utils.Helpers;

public class LoginTest extends BaseTest {

    @Test (priority = 1, description = "Verifying that login and logout functionality works correctly")
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Helpers helpers = new Helpers(driver);

        loginPage.login(ConfigMenager.getUser("normal.user"), ConfigMenager.getUser("password"));
        helpers.waitForElementVisible(homePage.getMenuButton(), 2);
        Assert.assertFalse(loginPage.isLoginButtonVisible(), "Login button still visible");
        loginPage.logout();
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button should be visible after logout");
    }
}
