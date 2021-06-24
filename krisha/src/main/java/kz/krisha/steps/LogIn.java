package kz.krisha.steps;

import kz.krisha.bisness_objects.User;
import kz.krisha.pages.AbstractPage;
import kz.krisha.pages.LoginPage;


public class LogIn extends AbstractPage {

    public LoginPage logIn(User user) {
        LoginPage loginPage = new LoginPage();
        loginPage.fillPhoneNumber(user.getPhoneNumber())
                .clickLoginButton()
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return loginPage;
    }

}
