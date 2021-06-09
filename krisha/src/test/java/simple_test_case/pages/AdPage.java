package simple_test_case.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class AdPage extends AbstractPage {

    public AdPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='kr-btn kr-btn--gray-gradient']")
    WebElement hideHint;

    @FindBy(xpath = "//div[@data-name='flat.floor']/div[@class='offer__advert-short-info']")
    WebElement floorInfo;

    @FindBy(xpath = "//div[@data-name='live.square']/div[@class='offer__advert-short-info']")
    WebElement squareInfo;

    @FindBy(xpath = "//div[@class='offer__price']")
    WebElement offerPrice;

    @FindBy(xpath = "//div[@class='gallary__main']/img")
    WebElement adMainPhoto;

    public AdPage switchTab() {
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
        return this;
    }

    public AdPage pressHideHint() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(hideHint));
        hideHint.click();
        return this;
    }

    public boolean checkFloorIsNotFirst() {
        String floorNumberText = floorInfo.getText();
        int floorNumber = Integer.parseInt(floorNumberText.substring(0, floorNumberText.indexOf("\u0020")));
        return floorNumber != 1;
    }

    public boolean checkSquareIsMoreThanMin(String minSquare) {
        String squareNumberText = squareInfo.getText();
        int squareNumber = Integer.parseInt(squareNumberText.substring(0, squareNumberText.indexOf("\u0020")));
        return (squareNumber >= Integer.parseInt(minSquare));
    }

    public boolean checkOfferPriceLessThanMaxPrice(String maxPrice) {
        String offerPriceText = offerPrice.getText().replaceAll("\\s+", "");
        int offerPrice = Integer.parseInt(offerPriceText.substring(0, offerPriceText.indexOf("〒")));
        return offerPrice <= Integer.parseInt(maxPrice);
    }

    public boolean checkAdMainPhotoIsDisplayed() {
        return adMainPhoto.isDisplayed();
    }

}
