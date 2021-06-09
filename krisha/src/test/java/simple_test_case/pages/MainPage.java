package simple_test_case.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void selectRentCategory() {
        categoryDropDownList.click();
        rentCategory.click();
    }

    public void selectRegion() {
        regionDropDown.click();
        new WebDriverWait(driver, 21).until(ExpectedConditions.elementToBeClickable(regionAlmaty));
        regionAlmaty.click();
        medeuskkijRaion.click();
        selectButton.click();
    }

    public void inputPrice(String price) {
        priceTo.sendKeys(price);
    }

    public void selectHavePhotoCheckBox() {
        photoCheckbox.click();
    }

    public void selectOwnerCheckBox() {
        fromOwnerCheckBox.click();
    }

    public void selectAgency() {
        fromCheckedAgency.click();
    }

    public void selectRoomCount() {
        roomCountDropDownList.click();
        new WebDriverWait(driver, 21).until(ExpectedConditions.elementToBeClickable(selectThreeRoom));
        selectThreeRoom.click();
    }

    public void pressSearchButton() {
        searchButton.click();
    }

    public void checkValidText(String textFromDoc) {
        String textOnPage = pageTitle.getText();
        assertEquals(textOnPage, textFromDoc);
    }

    public void pressNewBuildingsLink() {
        new WebDriverWait(driver, 21).until(ExpectedConditions.elementToBeClickable(newBuildingsLink));
        newBuildingsLink.click();
    }
}
