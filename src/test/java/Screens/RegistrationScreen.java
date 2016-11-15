package Screens;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

/**
 *
 * Created by Szandra on 2016. 11. 07..
 */
public class RegistrationScreen extends AbstractScreen {

    private static By male_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_male");
    private static By female_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_female");
    private static By other_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_other");
    private static By bubble_locator = By.id("com.attrecto.flagr:id/tv_lower_bubble_age");
    private static By ageBox_locator = By.id("com.attrecto.flagr:id/et_age_selector");
    private static By forward_locator = By.id("com.attrecto.flagr:id/iv_reg_forward");
    private static Random random;

    public RegistrationScreen(AndroidDriver driver) {
        super(driver);
        random = new Random();
    }

    public void ChoosGenderRandom() {
        int randomInt = getRandomIntBetween(1, 3);
        switch (randomInt) {
        case 1:
            driver.findElement(male_locator).click();
            break;
        case 2:
            driver.findElement(female_locator).click();
            break;
        case 3:
            driver.findElement(other_locator).click();
            break;
        }
    }

    public void FemaleAge() throws Exception {
        int rAge = getRandomIntBetween(15, 99);
        clickArrow();
        waitForVisibilityOf(female_locator);
        driver.findElement(female_locator).click();
        clickArrow();
        waitForVisibilityOf(ageBox_locator);
        driver.findElement(ageBox_locator).sendKeys(String.valueOf(rAge));
        waitForVisibilityOf(bubble_locator);
        String Kor = driver.findElement(bubble_locator).getText();

        Kor = Kor.replaceAll("[^0-9]", "");
        Integer KorSzamkent = Integer.valueOf(Kor);
        // a= new String(Kor.substring(Kor.length()-2)); -> csak akkor ha fix a hossza és gyorsan kell! MERT CSÚNYA

        if (isBetween(rAge, 15, 26)) {
            Assert.assertEquals(KorSzamkent, Integer.valueOf(rAge + 4), "Szar");
            Assert.assertTrue(true, "Szar");
            Assert.assertFalse(false, "szar");
        } else if (isBetween(rAge, 27, 46)) {
            Assert.assertEquals(KorSzamkent, Integer.valueOf(rAge - 5), "Szar");
        } else if (isBetween(rAge, 47, 99)) {
            Assert.assertEquals(KorSzamkent, Integer.valueOf(rAge - 10), "Szar");
        }
    }

    public void MaleAge() throws Exception {
        int rAge = getRandomIntBetween(15, 99);
        Integer KorSzamkent = getAgeFromBubble(rAge, male_locator);
        if (isBetween(rAge, 15, 36)) {
            Assert.assertEquals(KorSzamkent, Integer.valueOf(rAge + 5), "Nem megfelelő a szövegbuborékban lévő kor");
        } else if (isBetween(rAge, 35, 99)) {
            Assert.assertEquals(KorSzamkent, Integer.valueOf(rAge - 5), "Nem megfelelő a szövegbuborékban lévő kor");
        }
    }

    public void OtherAge() throws Exception {
        int rAge = getRandomIntBetween(15, 99);
        clickArrow();
        waitForVisibilityOf(other_locator);
        driver.findElement(other_locator).click();
        clickArrow();
        waitForVisibilityOf(ageBox_locator);
        driver.findElement(ageBox_locator).sendKeys(String.valueOf(rAge));
        waitForVisibilityOf(bubble_locator);
        String Kor = driver.findElement(bubble_locator).getText();

        Kor = Kor.replaceAll("[^0-9]", "");
        Integer KorSzamkent = Integer.valueOf(Kor);
        if (KorSzamkent < -10 || KorSzamkent > 10) {
            throw new Exception("Nem megfelelő a szövegbuborékban lévő kor");
        }

    }

    private void ifElementPresentGoForward() {
        if (isElementPresent(forward_locator)) {
            clickArrow();
            ifElementPresentGoForward();
        }
    }

    private boolean isElementPresent(By locator) {
        //set timeout lower, then appium's timeout: prevents to wait long seconds to find the element
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        boolean empty = driver.findElements(locator).isEmpty();
        //set back appium's timeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return empty;
    }

    private boolean isElementPresent2(By locator)
    {
        //set timeout lower, then appium's timeout: prevents to wait long seconds to find the element
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try
        {
            driver.findElement(locator);
            //set back appium's timeout
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return true;
        }
        catch (NoSuchElementException nosuchelementexception)
        {
            //set back appium's timeout
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return false;
        }
    }

    private Integer getAgeFromBubble(int rAge, By locator) {
        clickArrow();
        waitForVisibilityOf(locator);
        driver.findElement(locator).click();
        clickArrow();
        waitForVisibilityOf(ageBox_locator);
        driver.findElement(ageBox_locator).sendKeys(String.valueOf(rAge));
        waitForVisibilityOf(bubble_locator);

        String Kor = driver.findElement(bubble_locator).getText();
        Kor = Kor.replaceAll("[^0-9]", "");
        return Integer.valueOf(Kor);
    }

    private static int getRandomIntBetween(int bottomBound, int topBound) {
        return bottomBound + random.nextInt(topBound - bottomBound);
    }

    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
