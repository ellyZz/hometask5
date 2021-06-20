package kz.krisha.driver.singleton;

import kz.krisha.config.Config;
import kz.krisha.utils.ReadConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static kz.krisha.utils.Constants.TWENTY;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static final Config config = ReadConfig.getConfig();

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(config.getStartUrl());
            driver.manage().timeouts().implicitlyWait(TWENTY, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
