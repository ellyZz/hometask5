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
import static org.testng.Assert.assertTrue;

public class TrashPage extends AbstractPage {
    private static final String RESTORE_TEXT = "восстановлен";

    public TrashPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='listing__items']/child::div[1]")
    WebElement firstFileInTrash;

    @FindBy(xpath = "//div[@class='root__content-inner root__content-inner_white root__content-inner_page_listing']")
    WebElement filesArea;

    @FindBy(xpath = "//button[@class='Button2 Button2_view_transparent Button2_size_m ufo-resources-action-bar__primary-button ufo-resources-action-bar__primary-button_desktop ufo-resources-action-bar__primary-button_action_restore']")
    WebElement restoreButton;

    @FindBy(xpath = "//div[@class='notifications__text js-message']")
    WebElement notificationMessage;

    public boolean isFileInTrash() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(firstFileInTrash));
        return firstFileInTrash.isDisplayed();
    }

    public TrashPage selectFileForRestore() {
        new Actions(driver)
                .moveToElement(filesArea, -700, -20)
                .clickAndHold()
                .moveToElement(filesArea,-500, -200)
                .release()
                .build()
                .perform();
        return this;
    }

    public void restoreFile() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(restoreButton));
        new Actions(driver).click(restoreButton).build().perform();
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(notificationMessage));
        String notificationMessageText = notificationMessage.getText();
        assertTrue(notificationMessageText.contains(RESTORE_TEXT), "Text not found!");
    }

}
