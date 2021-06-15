package kz.krisha.pages;

import kz.krisha.bisness_objects.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.pages.Constants.THIRTY;
import static org.testng.Assert.assertEquals;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='login']")
    WebElement inputPhoneNumber;

    @FindBy(xpath = "//button[@class='ui-button ui-button--blue']")
    WebElement loginButton;

    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//a[@class='btn btn-primary a-new-btn']")
    WebElement postAdButton;

    public LoginPage fillPhoneNumber(String phoneNumber) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(inputPhoneNumber));
        inputPhoneNumber.sendKeys(phoneNumber);
        return this;
    }

    public LoginPage fillPassword(String pwd) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.sendKeys(pwd);
        return this;
    }

    public LoginPage clickLoginButton(){
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return this;
    }

    public boolean isPostAdButtonIsDisplayedInCabinet(){
        return postAdButton.isDisplayed();
    }

    public boolean isLogIn(User user) {
        inputPhoneNumber.clear();
        fillPhoneNumber(user.getLogin())
                .clickLoginButton()
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return isPostAdButtonIsDisplayedInCabinet();
        }

}
