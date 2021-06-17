package kz.krisha.pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.utils.Constants.MIN_SQUARE;
import static kz.krisha.utils.Constants.THIRTY;
import static org.testng.Assert.assertEquals;

public class MainPageWithAdditionalFilters extends AbstractPage {

    public MainPageWithAdditionalFilters(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='search-btn-main']")
    WebElement additionalSearchButton;

    @FindBy(xpath = "//div[@class='btn-group']/button[@title=' На любой срок']")
    WebElement periodDropDownList;

    @FindBy(xpath = "//div[@class='btn-group open']/ul/child::li[4]")
    WebElement longPeriod;

    @FindBy(xpath = "//label[@for='das[floor_not_first]-checkbox-0']")
    WebElement notFirstFloorCheckBox;

    @FindBy(xpath = "//input[@id='das[live.square][from]']")
    WebElement minSquare;

    @FindBy(xpath = "//div[@class='btn-submit-wrapper']/button")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='page-title']/h1")
    WebElement pageTitle;

    @FindBy(xpath = "//section[@class='a-list a-search-list a-list-with-favs']/section/child::div[3]")
    WebElement firstAd;

    public MainPageWithAdditionalFilters selectPeriod() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(periodDropDownList));
        periodDropDownList.click();
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(longPeriod));
        longPeriod.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectNotFirstCheckBox() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(notFirstFloorCheckBox));
        notFirstFloorCheckBox.click();
        return this;
    }

    public MainPageWithAdditionalFilters inputMinSquare(String square) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='das[live.square][from]']")));
        minSquare.sendKeys(square);
        return this;
    }

    public MainPageWithAdditionalFilters clickSearchButton() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        return this;
    }

    public void checkTitleTextAfterAdditionalFilters(String textFromDoc) {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='page-title']/h1")));
        String textOnPage = pageTitle.getText();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(textOnPage).containsIgnoringCase(textFromDoc);
        softAssertions.assertAll();
    }

    public void clickToFirstAd() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(firstAd));
        firstAd.click();
    }

    public MainPageWithAdditionalFilters fillAdditionalFilters() {
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters(driver);
        mainPageWithAdditionalFilters
                .selectPeriod()
                .selectNotFirstCheckBox()
                .inputMinSquare(MIN_SQUARE)
                .clickSearchButton();
        return this;
    }
}
