package kz.krisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static kz.krisha.utils.Constants.PHONE_VALIDATE_REGEX;

public class NewBuildingsPage extends AbstractPage {
    private static final By FULL_PHONE_NUMBER = By.xpath("//div[@class='complex-cards complex-cards--search']/child::div[1]//span[@class='complex-card__full-phone']");

    public NewBuildingsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='complex-cards complex-cards--search']/child::div[1]//div[@class='complex-card__phone-block']/div/a")
    private WebElement showPhoneNumber;

    @FindBy(xpath = "//div[@class='complex-cards complex-cards--search']/child::div[1]//span[@class='complex-card__full-phone']")
    private WebElement fullPhoneNumber;

    public boolean isCorrectPhoneNumber() {
        waitForWebElementBeClickable(showPhoneNumber);
        waitForWebElementPresence(FULL_PHONE_NUMBER);
        showPhoneNumber.click();
        String fullPhoneNumberText = fullPhoneNumber.getText();
        return fullPhoneNumberText.matches(PHONE_VALIDATE_REGEX);
    }
}
