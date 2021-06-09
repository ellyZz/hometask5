package simple_test_case.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class MainPageWithAdditionalFilters extends AbstractPage {

    public MainPageWithAdditionalFilters(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "category-type")
    WebElement categoryDropDownList;

    @FindBy(xpath = "//option[@value='rent']")
    WebElement rentCategory;

    @FindBy(className = "region-dropdown-label")
    WebElement regionDropDown;

    @FindBy(xpath = "//option[@data-label='Алматы']")
    WebElement regionAlmaty;

    @FindBy(xpath = "//option[text()='Медеуский р-н']")
    WebElement medeuskkijRaion;

    @FindBy(xpath = "//div[@class='leveled-select is-visible'][3]/a")
    WebElement selectButton;

    @FindBy(xpath = "//input[@name='das[price][to]']")
    WebElement priceTo;

    @FindBy(xpath = "//label[@for='das[_sys.hasphoto]-checkbox-0']")
    WebElement photoCheckbox;

    @FindBy(xpath = "//label[@for='das[who]-checkbox-0']")
    WebElement fromOwnerCheckBox;

    @FindBy(xpath = "//label[@for='das[checked]-checkbox-0']")
    WebElement fromCheckedAgency;

    @FindBy(css = "select[name='das[live.rooms]']")
    WebElement roomCountDropDownList;

    @FindBy(xpath = "//option[text()='3 комн.']")
    WebElement selectThreeRoom;

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

    public MainPageWithAdditionalFilters selectRentCategory() {
        categoryDropDownList.click();
        rentCategory.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectRegion() {
        regionDropDown.click();
        new WebDriverWait(driver, 21).until(ExpectedConditions.elementToBeClickable(regionAlmaty));
        regionAlmaty.click();
        medeuskkijRaion.click();
        selectButton.click();
        return this;
    }

    public MainPageWithAdditionalFilters inputPrice(String price) {
        priceTo.sendKeys(price);
        return this;
    }

    public MainPageWithAdditionalFilters selectHavePhotoCheckBox() {
        photoCheckbox.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectOwnerCheckBox() {
        fromOwnerCheckBox.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectAgency() {
        fromCheckedAgency.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectRoomCount() {
        roomCountDropDownList.click();
        new WebDriverWait(driver, 21).until(ExpectedConditions.elementToBeClickable(selectThreeRoom));
        selectThreeRoom.click();
        return this;
    }

    public MainPageWithAdditionalFilters pressSearchButton() {
        additionalSearchButton.click();
        return this;
    }

    public boolean isCategoryDropDownListDisplayed() {
        return categoryDropDownList.isDisplayed();
    }

    public boolean isRoomCountDropDownListDisplayed() {
        return roomCountDropDownList.isDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        return searchButton.isDisplayed();
    }

    public MainPageWithAdditionalFilters selectPeriod() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(periodDropDownList));
        periodDropDownList.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(longPeriod));
        longPeriod.click();
        return this;
    }

    public MainPageWithAdditionalFilters selectNotFirstCheckBox() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(notFirstFloorCheckBox));
        notFirstFloorCheckBox.click();
        return this;
    }

    public MainPageWithAdditionalFilters inputMinSquare(String square) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='das[live.square][from]']")));
        minSquare.sendKeys(square);
        return this;
    }

    public MainPageWithAdditionalFilters clickSearchButton() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        return this;
    }

    public void checkTitleTextAfterAdditionalFilters(String textFromDoc) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='page-title']/h1")));
        String textOnPage = pageTitle.getText();
        assertEquals(textOnPage, textFromDoc);
    }

    public void clickToFirstAd() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(firstAd));
        firstAd.click();
    }
}
