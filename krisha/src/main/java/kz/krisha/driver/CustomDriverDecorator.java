package kz.krisha.driver;

import kz.krisha.utils.Logger;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

import static kz.krisha.utils.Utils.highlightElement;

public class CustomDriverDecorator implements WebDriver, TakesScreenshot, JavascriptExecutor {
    protected WebDriver driver;

    public CustomDriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        driver.get(url);
        Logger.info(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        Logger.info(by.toString());
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        Logger.info(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()));
        highlightElement(driver, by);
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        Logger.info("WebDriver Close");
        driver.close();
    }

    @Override
    public void quit() {
        Logger.info("Browser will be closed now...");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        Logger.info("Switch to ");
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        Logger.info("Manage web driver");
        return driver.manage();
    }

    @Override
    public Object executeScript(String s, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(s, args);
    }

    @Override
    public Object executeAsyncScript(String s, Object... args) {
        return ((JavascriptExecutor) driver).executeAsyncScript(s, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        Logger.info("Taking screenshot");
        return (X) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
}
