package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;


/**
 * Created by Szandra on 2016. 11. 07..
 */
public class AbstractScreen {

    private static By forward_locator =By.id("com.attrecto.flagr:id/iv_reg_forward");

    protected AndroidDriver driver;

    public AbstractScreen(AndroidDriver driver) {this.driver=driver;}

    protected void waitForVisibilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, 30L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForPresenceOf(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, 30L);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForClickabilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, 30L);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickArrow()
    {
        waitForVisibilityOf(forward_locator);
        driver.findElement(forward_locator).click();
    }
}
