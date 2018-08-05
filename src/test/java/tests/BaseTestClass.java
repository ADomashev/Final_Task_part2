package tests;

import driver.DriverSingleton;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTestClass {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = DriverSingleton.DRIVER_INSTANCE.getDriver();
        driver.manage().window().fullscreen();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}

