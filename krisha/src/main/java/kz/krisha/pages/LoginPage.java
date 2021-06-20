package kz.krisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    private static final By POST_ADD_BUTTON = By.xpath("//a[@class='btn btn-primary a-new-btn']");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='login']")
    private WebElement inputPhoneNumber;

    @FindBy(xpath = "//button[@class='ui-button ui-button--blue']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputPassword;


    public LoginPage fillPhoneNumber(String phoneNumber) {
        waitForWebElementVisible(inputPhoneNumber);
        inputPhoneNumber.sendKeys(phoneNumber);
        return this;
    }

    public LoginPage fillPassword(String pwd) {
        waitForWebElementVisible(inputPassword);
        inputPassword.sendKeys(pwd);
        return this;
    }

    public LoginPage clickLoginButton() {
        waitForWebElementBeClickable(loginButton);
        loginButton.click();
        return this;
    }

    public boolean isPostAdButtonIsDisplayedInCabinet() {
        return isWebElementDisplayed(POST_ADD_BUTTON);
    }



}
