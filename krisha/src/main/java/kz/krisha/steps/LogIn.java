package kz.krisha.steps;

import kz.krisha.bisness_objects.User;
import kz.krisha.pages.AbstractPage;
import kz.krisha.pages.LoginPage;
import org.openqa.selenium.WebDriver;


public class LogIn extends AbstractPage {
    public LogIn(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage logIn(User user) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillPhoneNumber(user.getPhoneNumber())
                .clickLoginButton()
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return loginPage;
    }

}
