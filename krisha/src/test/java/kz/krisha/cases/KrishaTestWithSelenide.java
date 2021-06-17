package kz.krisha.cases;

import kz.krisha.bisness_objects.User;
import kz.krisha.config.Config;
import kz.krisha.utils.ReadConfig;
import kz.krisha.pages.for_selenide.LoginPageForSelenide;
import kz.krisha.pages.for_selenide.MainPageForSelenide;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;


public class KrishaTestWithSelenide {
    private final Config config = ReadConfig.getConfig();

    @BeforeMethod
    public void setUp() {
        open(config.getStartUrl());
    }

    @Test(description = "Test with selenide")
    public void isSuccessfulLogin() {
        MainPageForSelenide mainPageForSelenide = new MainPageForSelenide();
        mainPageForSelenide.openLoginPage();
        LoginPageForSelenide loginPageForSelenide = new LoginPageForSelenide();
        loginPageForSelenide.isLogIn(new User());
    }
}
