import Screens.RegistrationScreen;
import Util.AppiumDriverBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by Szandra on 2016. 11. 07..
 */
public class TestCases extends AppiumDriverBuilder {
    @BeforeTest
    public void setUp() throws MalformedURLException {
        prepareAndroidForAppium();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    /*@Test
        public void allowButton(){new LoginScreen(driver).AcceptButton();}*/

    /*@Test
        public void yuhuuu(){
        new LoginScreen(driver).clickArrow();
        new RegistrationScreen(driver).ChoosGenderRandom();
        new LoginScreen(driver).clickArrow();
        new RegistrationScreen(driver).FemaleAge();
    }*/

    @Test
    public void FemaleAgeTest() throws Exception {
        new RegistrationScreen(driver).FemaleAge();
    }

    @Test
    public void MaleAgeTest() throws Exception {
        new RegistrationScreen(driver).MaleAge();
    }

    @Test
    public void OtherAgeTest() throws Exception {
        new RegistrationScreen(driver).OtherAge();
    }


}
