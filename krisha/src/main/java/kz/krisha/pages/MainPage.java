package kz.krisha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static kz.krisha.pages.Constants.THIRTY;
import static org.testng.Assert.assertEquals;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
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
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='page-title']/h1")
    WebElement pageTitle;

    @FindBy(xpath = "//a[@href='/complex/search/']")
    WebElement newBuildingsLink;

    @FindBy(xpath = "//li[@class='cabinet-link-item']/a[@class='cabinet-link']")
    WebElement linkToLoginPage;

    public void openLoginPage(){
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(linkToLoginPage));
        linkToLoginPage.click();
    }

    public MainPage selectRentCategory() {
        categoryDropDownList.click();
        rentCategory.click();
        return this;
    }

    public MainPage selectRegion() {
        regionDropDown.click();
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(regionAlmaty));
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
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(selectThreeRoom));
        selectThreeRoom.click();
        return this;
    }

    public MainPage pressSearchButton() {
        searchButton.click();
        return this;
    }

    public MainPage checkValidText(String textFromDoc) {
        String textOnPage = pageTitle.getText();
        assertEquals(textOnPage, textFromDoc);
        return this;
    }

    public void pressNewBuildingsLink() {
        new WebDriverWait(driver, THIRTY).until(ExpectedConditions.elementToBeClickable(newBuildingsLink));
        newBuildingsLink.click();
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
}
