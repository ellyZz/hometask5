package simple_test_case.tests;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import simple_test_case.pages.AdPage;
import simple_test_case.pages.MainPage;
import simple_test_case.pages.MainPageWithAdditionalFilters;
import simple_test_case.pages.NewBuildingsPage;

import java.util.concurrent.TimeUnit;

public class KrishaTests {
    private WebDriver driver;
    private static final String RENT_MEDEO_SIMPLE_FILTERS_TEXT = "Аренда трехкомнатных квартир от хозяев в Медеуском р-не Алматы";
    private static final String RENT_MEDEO_ADDITIONAL_FILTERS_TEXT = "Аренда квартир на длительный срок от хозяев в Медеуском р-не Алматы";
    private static final String MIN_SQUARE = "50";
    private static final String MAX_PRICE = "300000";

    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://krisha.kz");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test(groups = "UITest")
    public void verifyPageContentInDefaultState() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainPageWithFilters.isCategoryDropDownListDisplayed())
                .overridingErrorMessage("CategoryDropDownList is not displayed")
                .isTrue();
        softAssertions.assertThat(mainPageWithFilters.isRoomCountDropDownListDisplayed())
                .overridingErrorMessage("RoomCountDropDownList is not displayed")
                .isTrue();
        softAssertions.assertThat(mainPageWithFilters.isSearchButtonDisplayed())
                .overridingErrorMessage("Search button is not displayed")
                .isTrue();
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkCorrectTextInResultOfSearch() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectRentCategory();
        mainPage.selectRegion();
        mainPage.inputPrice(MAX_PRICE);
        mainPage.selectHavePhotoCheckBox();
        mainPage.selectOwnerCheckBox();
        mainPage.selectAgency();
        mainPage.selectRoomCount();
        mainPage.selectRoomCount();
        mainPage.pressSearchButton();
        mainPage.checkValidText(RENT_MEDEO_SIMPLE_FILTERS_TEXT);
    }

    @Test(groups = "UITest")
    public void checkResultOfSpecialFilters() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        mainPageWithFilters
                .selectRentCategory()
                .selectRegion()
                .inputPrice(MAX_PRICE)
                .selectHavePhotoCheckBox()
                .selectOwnerCheckBox()
                .selectAgency()
                .selectRoomCount()
                .selectRoomCount()
                .pressSearchButton()
                .selectPeriod()
                .selectNotFirstCheckBox()
                .inputMinSquare(MIN_SQUARE)
                .clickSearchButton()
                .checkTitleTextAfterAdditionalFilters(RENT_MEDEO_ADDITIONAL_FILTERS_TEXT);
    }

    @Test(groups = "UITest")
    public void checkValuesInAd() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        mainPageWithFilters
                .selectRentCategory()
                .selectRegion()
                .inputPrice(MAX_PRICE)
                .selectHavePhotoCheckBox()
                .selectOwnerCheckBox()
                .selectAgency()
                .selectRoomCount()
                .selectRoomCount()
                .pressSearchButton()
                .selectPeriod()
                .selectNotFirstCheckBox()
                .inputMinSquare(MIN_SQUARE)
                .clickSearchButton()
                .clickToFirstAd();
        AdPage adPage = new AdPage(driver)
                .switchTab()
                .pressHideHint();
        softAssertions.assertThat(adPage.checkFloorIsNotFirst())
                .overridingErrorMessage("Floor is first")
                .isTrue();
        softAssertions.assertThat(adPage.checkSquareIsMoreThanMin(MIN_SQUARE))
                .overridingErrorMessage("Square is more than min")
                .isTrue();
        softAssertions.assertThat(adPage.checkOfferPriceLessThanMaxPrice(MAX_PRICE))
                .overridingErrorMessage("Offer price less than max price")
                .isTrue();
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkPhoneNumber() {
        MainPage mainPage = new MainPage(driver);
        mainPage.pressNewBuildingsLink();
        NewBuildingsPage newBuildingsPage = new NewBuildingsPage(driver);
        newBuildingsPage.isCorrectPhoneNumber();
    }

    @Test(groups = "UITest")
    public void checkPhotoIsDisplayed() {
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters(driver);
        mainPageWithAdditionalFilters
                .selectHavePhotoCheckBox()
                .clickSearchButton()
                .clickToFirstAd();
        AdPage adPage = new AdPage(driver)
                .switchTab()
                .pressHideHint();
        adPage.checkAdMainPhotoIsDisplayed();
    }
}
