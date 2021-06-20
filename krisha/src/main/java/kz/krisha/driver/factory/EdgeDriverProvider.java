package kz.krisha.driver.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverProvider implements WebDriverProvider {
    public WebDriver createWebDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
