package Screens;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Created by Szandra on 2016. 11. 07..
 */
public class LoginScreen extends AbstractScreen{


    public LoginScreen(AndroidDriver driver){super (driver);}

    public void AcceptButton()
    {
        waitForVisibilityOf(By.id("com.android.packageinstaller:id/permission_allow_button"));
        //waitForClickabilityOf(By.id("com.android.packageinstaller:id/permission_allow_button"));
        driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
    }



}
