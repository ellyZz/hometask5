package kz.krisha.cases;

import kz.krisha.driver.WebDriverCustomizer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest {
    protected WebDriver driver;

//    @Parameters("Browser")
//    @BeforeMethod(groups = "UITest")
//    public void setDriver(String browser) {
//        driver = WebDriverCustomizer.get(browser);
////        driver = WebDriverSingleton.getWebDriverInstance();
//    }

    @AfterMethod(groups = "UITest")
    public void closeDriver() {
        WebDriverCustomizer.quitDriver();
//        WebDriverSingleton.closeDriver();
    }
}
