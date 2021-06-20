package kz.krisha.cases;

import kz.krisha.driver.WebDriverCustomizer;
import kz.krisha.driver.factory.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        driver = WebDriverCustomizer.get(BrowserType.EDGE);
//        driver = WebDriverSingleton.getWebDriverInstance();
    }

    @AfterMethod
    public void closeDriver() {
        WebDriverCustomizer.quitDriver();
//        WebDriverSingleton.closeDriver();
    }
}
