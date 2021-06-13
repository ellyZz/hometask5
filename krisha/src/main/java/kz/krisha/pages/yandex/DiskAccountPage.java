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

public class DiskAccountPage extends AbstractPage {

    public DiskAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='listing__items']/child::div[1]")
    WebElement firstFileInDisk;

    @FindBy(xpath = "//div[@class='listing__items']/child::div[3]")
    WebElement thirdFileInDisk;

    @FindBy(xpath = "(//div[@class='listing-item__fields'])[last()]")
    WebElement trash;

    public DiskAccountPage moveFirstFileInTrash() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(firstFileInDisk));
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.visibilityOf(trash));
        new Actions(driver).dragAndDrop(firstFileInDisk, trash).build().perform();
        return this;
    }

    public DiskAccountPage openTrash() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(trash));
        new Actions(driver).doubleClick(trash).build().perform();
        return this;
    }

}
