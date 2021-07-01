package kz.krisha.utils;

import kz.krisha.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static kz.krisha.utils.Constants.ONE;


public class Utils extends AbstractPage {

    public Utils() {
        PageFactory.initElements(driver, this);
    }

    public Utils switchTab() {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(ONE));
        return this;
    }

    public static void highlightElement(WebDriver driver, By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", driver.findElement(by));
    }
}
