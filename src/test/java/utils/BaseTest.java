package utils;

import config.ConfigMenager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String appPath = ConfigMenager.getConfig("app.path");
        if (!Paths.get(appPath).isAbsolute()) {
            appPath = System.getProperty("user.dir") + "/" + appPath;
        }

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigMenager.getConfig("platform.name"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigMenager.getConfig("device.name"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigMenager.getConfig("automation.name"));
        capabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.*");
        capabilities.setCapability(MobileCapabilityType.APP, appPath);

        System.out.println("APK install: " + appPath);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

        System.out.println("Driver ON");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> ((AndroidDriver) d).getSessionId() != null);

        Helpers helpers = new Helpers(driver);
        helpers.waitForElementVisible(new pages.LoginPage(driver).loginButton, 5);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println(">>> Driver OFF.");
        }
    }
}
