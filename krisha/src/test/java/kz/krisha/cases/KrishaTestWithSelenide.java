package kz.krisha.cases;

import kz.krisha.bisness_objects.User;
import kz.krisha.pages.for_selenide.LoginPageForSelenide;
import kz.krisha.pages.for_selenide.MainPageForSelenide;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

import static kz.krisha.pages.Constants.START_URL;

public class KrishaTestWithSelenide {
    @BeforeMethod
    public void setUp() {
        open(START_URL);
    }

    @Test(description = "Test with selenide")
    public void isSuccessfulLogin() {
        MainPageForSelenide mainPageForSelenide = new MainPageForSelenide();
        mainPageForSelenide.openLoginPage();
        LoginPageForSelenide loginPageForSelenide = new LoginPageForSelenide();
        loginPageForSelenide.isLogIn(new User());
    }
}
