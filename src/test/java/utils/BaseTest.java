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

        String appPath = ConfigMenager.get("app.path");
        if (!Paths.get(appPath).isAbsolute()) {
            appPath = System.getProperty("user.dir") + "/" + appPath;
        }

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigMenager.get("platform.name"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigMenager.get("device.name"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigMenager.get("automation.name"));
        capabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.*");
        capabilities.setCapability(MobileCapabilityType.APP, appPath);

        System.out.println("APK install: " + appPath);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

        System.out.println("Driver ON");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> ((AndroidDriver) d).getSessionId() != null);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println(">>> Driver zamknięty.");
        }
    }
}
