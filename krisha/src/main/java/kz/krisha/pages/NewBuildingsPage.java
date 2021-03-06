package kz.krisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.pages.Constants.*;

public class NewBuildingsPage extends AbstractPage {

    public NewBuildingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='complex-cards complex-cards--search']/child::div[1]//div[@class='complex-card__phone-block']/div/a")
    WebElement showPhoneNumber;

    @FindBy(xpath = "//div[@class='complex-cards complex-cards--search']/child::div[1]//span[@class='complex-card__full-phone']")
    WebElement fullPhoneNumber;

    public boolean isCorrectPhoneNumber() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(showPhoneNumber));
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='complex-cards complex-cards--search']/child::div[1]//span[@class='complex-card__full-phone']")));
        showPhoneNumber.click();
        String fullPhoneNumberText = fullPhoneNumber.getText();
        return fullPhoneNumberText.matches(PHONE_VALIDATE_REGEX);
    }
}
