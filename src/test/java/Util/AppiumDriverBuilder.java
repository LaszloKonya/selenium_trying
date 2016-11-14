package Util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Szandra on 2016. 11. 07..
 */
public class AppiumDriverBuilder {

    protected AndroidDriver driver;

    public AppiumDriverBuilder() {
    }

    public AndroidDriver prepareAndroidForAppium() throws MalformedURLException {
        /*File appDir = new File("C:\\Users\\Szandra\\Downloads\\Flagr");
        File app = new File(appDir,"app-stage.apk")----> ehelyett inkább az appiumban húzzuk be*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("appPackage", "com.attrecto.flagr");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appActivity", "com.attrecto.flagr.ui.splash.SplashActivity");
        driver = new AndroidDriver(new URL("http://0.0.0.0:6777/wd/hub"), capabilities);
        return driver;
    }
}
