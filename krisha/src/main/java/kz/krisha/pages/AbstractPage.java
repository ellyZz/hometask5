package kz.krisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.utils.Constants.THIRTY;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWebElementDisplayed(By elementLocator){
        return !driver.findElements(elementLocator).isEmpty();
    }

    public void waitForWebElementBeClickable(WebElement element) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForWebElementPresence(By elementLocator) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }
    public void waitForWebElementVisible(WebElement element) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(element));
    }
}
