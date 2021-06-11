package kz.krisha.utils;

import kz.krisha.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static kz.krisha.pages.Constants.*;


public class Utils extends AbstractPage {
    public Utils(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Utils switchTab() {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(ONE));
        return this;
    }
}
