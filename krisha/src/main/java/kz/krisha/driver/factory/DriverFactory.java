package kz.krisha.driver.factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private DriverFactory() {
    }

    private static WebDriver driver;

    public static WebDriver init(String type) {
        switch (type) {
            case "CHROME" -> {
                driver = new ChromeDriverProvider().createWebDriver();
            }
            case "FIREFOX" -> {
                driver = new FireFoxDriverProvider().createWebDriver();
            }
            case "EDGE" -> {
                driver = new EdgeDriverProvider().createWebDriver();
            }
            default -> throw new IllegalArgumentException("Incorrect type" + type);
        }
        return driver;
    }
}
