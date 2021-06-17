package kz.krisha.pages.for_selenide;

import kz.krisha.bisness_objects.User;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginPageForSelenide {
    private static final By INPUT_PHONE_NUMBER = By.xpath("//input[@id='login']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@class='ui-button ui-button--blue']");
    private static final By INPUT_PASSWORD = By.xpath("//input[@id='password']");
    private static final By POST_AD_BUTTON = By.xpath("//a[@class='btn btn-primary a-new-btn']");

    public LoginPageForSelenide fillPhoneNumber(String phoneNumber) {
        $(INPUT_PHONE_NUMBER).shouldBe(visible).sendKeys(phoneNumber);
        return this;
    }

    public LoginPageForSelenide fillPassword(String pwd) {
        $(INPUT_PASSWORD).shouldBe(visible).sendKeys(pwd);
        return this;
    }

    public LoginPageForSelenide clickLoginButton() {
        $(LOGIN_BUTTON).shouldBe(enabled).click();
        return this;
    }

    public boolean isPostAdButtonIsDisplayedInCabinet() {
        return $(POST_AD_BUTTON).isDisplayed();
    }

    public boolean isLogIn(User user) {
        $(INPUT_PHONE_NUMBER).clear();
        fillPhoneNumber(user.getPhoneNumber())
                .clickLoginButton()
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return isPostAdButtonIsDisplayedInCabinet();
    }

}
