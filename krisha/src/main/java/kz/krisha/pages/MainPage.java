package kz.krisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static kz.krisha.utils.Constants.MAX_PRICE;

public class MainPage extends AbstractPage {
    private static final By CATEGORY_DROP_DOWN_LIST_LOCATOR = By.className("category-type");
    private static final By ROOM_COUNT_DROP_DOWN_LIST_LOCATOR = By.cssSelector("select[name='das[live.rooms]']");
    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[@class='search-btn-main']");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "category-type")
    private WebElement categoryDropDownList;

    @FindBy(xpath = "//option[@value='rent']")
    private WebElement rentCategory;

    @FindBy(className = "region-dropdown-label")
    private WebElement regionDropDown;

    @FindBy(xpath = "//option[@data-label='Алматы']")
    private WebElement regionAlmaty;

    @FindBy(xpath = "//option[text()='Медеуский р-н']")
    private WebElement medeuskkijRaion;

    @FindBy(xpath = "//div[@class='leveled-select is-visible'][3]/a")
    private WebElement selectButton;

    @FindBy(xpath = "//input[@name='das[price][to]']")
    private WebElement priceTo;

    @FindBy(xpath = "//label[@for='das[_sys.hasphoto]-checkbox-0']")
    private WebElement photoCheckbox;

    @FindBy(xpath = "//label[@for='das[who]-checkbox-0']")
    private WebElement fromOwnerCheckBox;

    @FindBy(xpath = "//label[@for='das[checked]-checkbox-0']")
    private WebElement fromCheckedAgency;

    @FindBy(css = "select[name='das[live.rooms]']")
    private WebElement roomCountDropDownList;

    @FindBy(xpath = "//option[text()='3 комн.']")
    private WebElement selectThreeRoom;

    @FindBy(xpath = "//button[@class='search-btn-main']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='page-title']/h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@href='/complex/search/']")
    private WebElement newBuildingsLink;

    @FindBy(xpath = "//li[@class='cabinet-link-item']/a[@class='cabinet-link']")
    private WebElement linkToLoginPage;

    public void openLoginPage() {
        waitForWebElementBeClickable(linkToLoginPage);
        linkToLoginPage.click();
    }

    public MainPage selectRentCategory() {
        categoryDropDownList.click();
        rentCategory.click();
        return this;
    }

    public MainPage selectRegion() {
        regionDropDown.click();
        waitForWebElementBeClickable(regionAlmaty);
        regionAlmaty.click();
        medeuskkijRaion.click();
        selectButton.click();
        return this;
    }

    public MainPage inputPrice(String price) {
        priceTo.sendKeys(price);
        return this;
    }

    public MainPage selectHavePhotoCheckBox() {
        photoCheckbox.click();
        return this;
    }

    public MainPage selectOwnerCheckBox() {
        fromOwnerCheckBox.click();
        return this;
    }

    public MainPage selectAgency() {
        fromCheckedAgency.click();
        return this;
    }

    public MainPage selectRoomCount() {
        roomCountDropDownList.click();
        waitForWebElementBeClickable(selectThreeRoom);
        selectThreeRoom.click();
        return this;
    }

    public MainPage pressSearchButton() {
        searchButton.click();
        return this;
    }

    public String getTitleTextFromPage() {
        return pageTitle.getText();
    }

    public void pressNewBuildingsLink() {
        waitForWebElementBeClickable(newBuildingsLink);
        newBuildingsLink.click();
    }

    public boolean isCategoryDropDownListDisplayed() {
        return isWebElementDisplayed(CATEGORY_DROP_DOWN_LIST_LOCATOR);
    }

    public boolean isRoomCountDropDownListDisplayed() {
        return isWebElementDisplayed(ROOM_COUNT_DROP_DOWN_LIST_LOCATOR);
    }

    public boolean isSearchButtonDisplayed() {
        return isWebElementDisplayed(SEARCH_BUTTON_LOCATOR);
    }

    public MainPage fillMainFilters() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectRentCategory()
                .selectRegion()
                .inputPrice(MAX_PRICE)
                .selectHavePhotoCheckBox()
                .selectOwnerCheckBox()
                .selectAgency()
                .selectRoomCount()
                .selectRoomCount()
                .pressSearchButton();
        return this;
    }
}
