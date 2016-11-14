package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Random;

/**
 *
 * Created by Szandra on 2016. 11. 07..
 */
public class RegistrationScreen extends AbstractScreen {

    private By male_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_male");
    private By female_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_female");
    private By other_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_other");
    private By bubble_locator = By.id("com.attrecto.flagr:id/tv_lower_bubble_age");
    private By ageBox_locator = By.id("com.attrecto.flagr:id/et_age_selector");
    private Random random;

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
            Assert.assertFalse(false,"szar");
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

    private int getRandomIntBetween(int bottomBound, int topBound) {
        return bottomBound + random.nextInt(topBound - bottomBound);
    }

    private boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
