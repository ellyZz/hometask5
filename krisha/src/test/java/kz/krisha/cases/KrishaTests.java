package kz.krisha.cases;

import kz.krisha.bisness_objects.User;
import kz.krisha.config.Config;
import kz.krisha.utils.CreateUser;
import kz.krisha.utils.ReadConfig;
import kz.krisha.pages.*;
import kz.krisha.utils.Screenshoter;
import kz.krisha.utils.Utils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static kz.krisha.utils.Constants.*;

public class KrishaTests {
    private WebDriver driver;
    private final Config config = ReadConfig.getConfig();

    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(config.getStartUrl());
        driver.manage().timeouts().implicitlyWait(TWENTY, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test(groups = "UITest")
    public void isSuccessfulLogin() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openLoginPage();
        loginPage.isLogIn(CreateUser.getUser());
        Screenshoter.takeScreenshot(driver);
    }

    @Test(groups = "UITest")
    public void verifyPageContentInDefaultState() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainPage.isCategoryDropDownListDisplayed())
                .overridingErrorMessage("CategoryDropDownList is not displayed")
                .isTrue();
        softAssertions.assertThat(mainPage.isRoomCountDropDownListDisplayed())
                .overridingErrorMessage("RoomCountDropDownList is not displayed")
                .isTrue();
        softAssertions.assertThat(mainPage.isSearchButtonDisplayed())
                .overridingErrorMessage("Search button is not displayed")
                .isTrue();
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkCorrectTextInResultOfSearch() {
        MainPage mainPage = new MainPage(driver);
        mainPage.fillMainFilters()
                .checkValidText(RENT_MEDEO_SIMPLE_FILTERS_TEXT);
    }

    @Test(groups = "UITest")
    public void checkResultOfSpecialFilters() {
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.fillMainFilters();
        mainPageWithAdditionalFilters
                .fillAdditionalFilters()
                .checkTitleTextAfterAdditionalFilters(RENT_MEDEO_ADDITIONAL_FILTERS_TEXT);
    }

    @Test(groups = "UITest")
    public void checkValuesInAd() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        mainPage.fillMainFilters();
        mainPageWithFilters
                .fillAdditionalFilters()
                .clickToFirstAd();
        AdPage adPage = new AdPage(driver);
        Utils utils = new Utils(driver);
        utils.switchTab();
        adPage
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
        MainPage mainPage = new MainPage(driver);
        mainPage.selectHavePhotoCheckBox();
        mainPageWithAdditionalFilters
                .clickSearchButton()
                .clickToFirstAd();
        AdPage adPage = new AdPage(driver);
        Utils utils = new Utils(driver);
        utils.switchTab();
        adPage.pressHideHint();
        adPage.checkAdMainPhotoIsDisplayed();
    }
}
