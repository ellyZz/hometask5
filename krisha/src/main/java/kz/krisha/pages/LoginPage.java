package kz.krisha.pages;

import kz.krisha.bisness_objects.User;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.utils.Constants.THIRTY;
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

    public LoginPage clickLoginButton() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return this;
    }

    public void isPostAdButtonIsDisplayedInCabinet() {
        SoftAssertions softAssertions = new SoftAssertions();
        boolean isDisplayed = !driver.findElements(By.xpath("//a[@class='btn btn-primary a-new-btn']")).isEmpty();
        softAssertions.assertThat(isDisplayed)
                .isTrue();
        softAssertions.assertAll();
    }

    public void isLogIn(User user) {
        inputPhoneNumber.clear();
        fillPhoneNumber(user.getPhoneNumber())
                .clickLoginButton()
                .fillPassword(user.getPassword())
                .clickLoginButton();
        isPostAdButtonIsDisplayedInCabinet();
    }

}
