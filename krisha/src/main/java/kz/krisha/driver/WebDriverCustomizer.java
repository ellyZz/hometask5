package kz.krisha.driver;

import kz.krisha.config.Config;
import kz.krisha.driver.factory.DriverFactory;
import kz.krisha.utils.ReadConfig;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static kz.krisha.utils.Constants.TWENTY;


public class WebDriverCustomizer {
    private static WebDriver driver;
    private static final Config config = ReadConfig.getConfig();


    private WebDriverCustomizer() {
    }

    public static WebDriver get() {
        if (driver != null) {
            return driver;
        }
        driver = new CustomDriverDecorator(DriverFactory.init(config.getBrowser()));
        driver.getCurrentUrl();
        driver.get(config.getStartUrl());
        driver.manage().timeouts().implicitlyWait(TWENTY, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
