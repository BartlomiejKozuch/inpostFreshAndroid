package tests;

import config.ConfigMenager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login( ConfigMenager.getUser("normal.user"), ConfigMenager.getUser("password"));
        Assert.assertFalse(loginPage.isLoginButtonVisible(), "Login button still visible");
        loginPage.logout();
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button should be visible after logout");
    }
}
