package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginHidesLoginButton() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertFalse(loginPage.isLoginButtonVisible(), "Login button still visible");
    }
}
