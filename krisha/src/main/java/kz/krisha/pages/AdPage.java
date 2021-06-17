package kz.krisha.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.utils.Constants.*;

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

    public AdPage pressHideHint() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(hideHint));
        hideHint.click();
        return this;
    }

    public boolean checkFloorIsNotFirst() {
        String floorNumberText = floorInfo.getText();
        var floorNumber = Integer.parseInt(floorNumberText.substring(0, floorNumberText.indexOf(SPACE_CHAR)));
        return floorNumber != FIRST_FLOOR;
    }

    public boolean checkSquareIsMoreThanMin(String minSquare) {
        String squareNumberText = squareInfo.getText();
        var squareNumber = Double.parseDouble(squareNumberText.substring(ZERO, squareNumberText.indexOf(SPACE_CHAR)));
        return (squareNumber >= Double.parseDouble(minSquare));
    }

    public boolean checkOfferPriceLessThanMaxPrice(String maxPrice) {
        String offerPriceText = offerPrice.getText().replaceAll(SPACE_REGEX, "");
        var intOfferPrice = Integer.parseInt(offerPriceText.substring(ZERO, offerPriceText.indexOf("〒")));
        return intOfferPrice <= Integer.parseInt(maxPrice);
    }

    public void checkAdMainPhotoIsDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
        boolean isDisplayed = !driver.findElements(By.xpath("//div[@class='gallary__main']/img")).isEmpty();
        softAssertions.assertThat(isDisplayed)
                .isTrue();
        softAssertions.assertAll();
    }

}
