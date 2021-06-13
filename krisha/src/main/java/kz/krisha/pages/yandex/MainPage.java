package kz.krisha.pages.yandex;

import kz.krisha.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static kz.krisha.pages.Constants.THIRTY;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='button button_login header__login-link']")
    WebElement loginLink;

    public MainPage clickLoginIcon() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(loginLink));
        new Actions(driver).click(loginLink).build().perform();
        return this;
    }

}
