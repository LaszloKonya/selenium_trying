//package Screens;
//
//import Util.AppiumDriverBuilder;
//import io.appium.java_client.android.AndroidDriver;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.net.MalformedURLException;
//import java.util.Random;
//
///**
// * Created by Laci on 2016. 11. 10..
// */
//public class RegistrationScreenTest {
//    private static By male_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_male");
//    private static By female_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_female");
//    private static By other_locator = By.id("com.attrecto.flagr:id/iv_gender_selector_other");
//    private static By bubble_locator = By.id("com.attrecto.flagr:id/tv_lower_bubble_age");
//    private static By ageBox_locator = By.id("com.attrecto.flagr:id/et_age_selector");
//    private static By forward_locator = By.id("com.attrecto.flagr:id/iv_reg_forward");
//
//    private AndroidDriver driver;
//    private Random random = new Random();
//
//    @BeforeTest
//    public void setUp() throws MalformedURLException {
//        AppiumDriverBuilder appiumDriverBuilder = new AppiumDriverBuilder();
//        driver = appiumDriverBuilder.prepareAndroidForAppium();
//    }
//
//    @AfterTest
//    public void tearDown() {
//        driver.close();
//        driver.quit();
//    }
//
//    @Test
//    public void FemaleAge() {
//        int rAge = 15 + random.nextInt(99 - 15);
//        Integer ageFromBublbe = getAgeFromBublbe(rAge, female_locator);
//
//        if (isBetween(rAge, 15, 26)) {
//            Assert.assertEquals(ageFromBublbe, Integer.valueOf(rAge + 4), "Nem megfelelő a szövegbuborékban lévő kor");
//        } else if (isBetween(rAge, 27, 46)) {
//            Assert.assertEquals(ageFromBublbe, Integer.valueOf(rAge - 5), "Nem megfelelő a szövegbuborékban lévő kor");
//        } else {
//            Assert.assertEquals(ageFromBublbe, Integer.valueOf(rAge - 10), "Nem megfelelő a szövegbuborékban lévő kor");
//        }
//    }
//
//    @Test
//    public void MaleAge() {
//        int rAge = 15 + random.nextInt(99 - 15);
//        Integer ageFromBublbe = getAgeFromBublbe(rAge, male_locator);
//        if (isBetween(rAge, 15, 36)) {
//            Assert.assertEquals(ageFromBublbe, Integer.valueOf(rAge + 5), "Nem megfelelő a szövegbuborékban lévő kor");
//        } else if (isBetween(rAge, 35, 99)) {
//            Assert.assertEquals(ageFromBublbe, Integer.valueOf(rAge - 5), "Nem megfelelő a szövegbuborékban lévő kor");
//        }
//    }
//
//    @Test
//    public void OtherAge() throws Exception {
//        int rAge = 15 + random.nextInt(99 - 15);
//        Integer ageFromBublbe = getAgeFromBublbe(rAge, other_locator);
//        Assert.assertTrue(rAge - 10 <= ageFromBublbe && ageFromBublbe <= rAge + 10, "Nem megfelelő a szövegbuborékban lévő kor");
//    }
//
//
//    private Integer getAgeFromBublbe(int rAge, By locator) {
//        clickForwardArrow();
//        waitForVisibilityOf(locator);
//        driver.findElement(locator).click();
//        clickForwardArrow();
//        waitForVisibilityOf(ageBox_locator);
//        driver.findElement(ageBox_locator).sendKeys(String.valueOf(rAge));
//        waitForVisibilityOf(bubble_locator);
//        String age = driver.findElement(bubble_locator).getText();
//        age = age.replaceAll("[^0-9]", "");
//        return Integer.valueOf(age);
//    }
//
//    private void clickForwardArrow() {
//        waitForVisibilityOf(forward_locator);
//        driver.findElement(forward_locator).click();
//    }
//
//    private void waitForVisibilityOf(By locator) {
//        WebDriverWait wait = new WebDriverWait(this.driver, 30L);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    public boolean isBetween(int x, int lower, int upper) {
//        return lower <= x && x <= upper;
//    }
//
//
//        public void ChoosGenderRandom() {
//        int randomInt = 1 + random.nextInt(3);
//        switch (randomInt) {
//            case 1:
//                driver.findElement(male_locator).click();
//                break;
//            case 2:
//                driver.findElement(female_locator).click();
//                break;
//            case 3:
//                driver.findElement(other_locator).click();
//                break;
//        }
//    }
//}
