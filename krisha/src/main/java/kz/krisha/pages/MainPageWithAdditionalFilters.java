package kz.krisha.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static kz.krisha.utils.Constants.MIN_SQUARE;

public class MainPageWithAdditionalFilters extends AbstractPage {

    private static final By MIN_SQUARE_LOCATOR = By.xpath("//input[@id='das[live.square][from]']");
    private static final By PAGE_TITLE_LOCATOR = By.xpath("//div[@class='page-title']/h1");

    public MainPageWithAdditionalFilters() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='btn-group']/button[@title=' На любой срок']")
    private WebElement periodDropDownList;

    @FindBy(xpath = "//div[@class='btn-group open']/ul/child::li[4]")
    private WebElement longPeriod;

    @FindBy(xpath = "//label[@for='das[floor_not_first]-checkbox-0']")
    private WebElement notFirstFloorCheckBox;

    @FindBy(xpath = "//input[@id='das[live.square][from]']")
    private WebElement minSquare;

    @FindBy(xpath = "//div[@class='btn-submit-wrapper']/button")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='page-title']/h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//section[@class='a-list a-search-list a-list-with-favs']/section/child::div[3]")
    private WebElement firstAd;

    public MainPageWithAdditionalFilters selectPeriod() {
        waitForWebElementBeClickable(periodDropDownList);
        periodDropDownList.click();
        waitForWebElementBeClickable(longPeriod);
        longPeriod.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectNotFirstCheckBox() {
        waitForWebElementBeClickable(notFirstFloorCheckBox);
        notFirstFloorCheckBox.click();
        return this;
    }

    public MainPageWithAdditionalFilters inputMinSquare(String square) {
        waitForWebElementPresence(MIN_SQUARE_LOCATOR);
        minSquare.sendKeys(square);
        return this;
    }

    public MainPageWithAdditionalFilters clickSearchButton() {
        waitForWebElementBeClickable(searchButton);
        searchButton.click();
        return this;
    }

    public String getTitleTextAfterAdditionalFilters() {
        waitForWebElementPresence(PAGE_TITLE_LOCATOR);
        return pageTitle.getText();
    }

    public void clickToFirstAd() {
        waitForWebElementBeClickable(firstAd);
        firstAd.click();
    }

    public MainPageWithAdditionalFilters fillAdditionalFilters() {
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters();
        mainPageWithAdditionalFilters
                .selectPeriod()
                .selectNotFirstCheckBox()
                .inputMinSquare(MIN_SQUARE)
                .clickSearchButton();
        return this;
    }
}
