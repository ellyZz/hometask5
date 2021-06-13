package kz.krisha.pages.yandex;

import kz.krisha.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.pages.Constants.*;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='passp-field-login']")
    WebElement loginField;

    @FindBy(xpath = "//button[@class='Button2 Button2_size_l Button2_view_action Button2_width_max Button2_type_submit']")
    WebElement loginButton;

    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    WebElement passwordField;

    public LoginPage clickLoginButton() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        return this;
    }

    public LoginPage fillLoginField(String login) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(loginField));
        new Actions(driver).sendKeys(loginField, login).build().perform();
        return this;
    }

    public LoginPage fillPasswordField(String pwd) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(passwordField));
        new Actions(driver).sendKeys(passwordField, pwd).build().perform();
        return this;
    }

    public void login(String login, String pwd) {
        fillLoginField(login);
        highlightElement(loginButton);
        clickLoginButton();
        unHighlightElement(loginButton);
        fillPasswordField(pwd);
        highlightElement(loginButton);
        clickLoginButton();
        unHighlightElement(loginButton);
    }
}
