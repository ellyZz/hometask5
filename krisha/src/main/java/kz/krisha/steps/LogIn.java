package kz.krisha.steps;

import kz.krisha.bisness_objects.User;

import kz.krisha.pages.LoginPage;
import org.openqa.selenium.WebDriver;


public class LogIn extends LoginPage {
    public LogIn(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage logIn(User user) {
        fillPhoneNumber(user.getPhoneNumber())
                .clickLoginButton()
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return this;
    }

}
