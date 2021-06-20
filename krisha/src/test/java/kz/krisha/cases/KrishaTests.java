package kz.krisha.cases;

import kz.krisha.config.Config;
import kz.krisha.driver.WebDriverCustomizer;
import kz.krisha.driver.factory.BrowserType;
import kz.krisha.driver.factory.DriverFactory;
import kz.krisha.driver.singleton.WebDriverSingleton;
import kz.krisha.steps.LogIn;
import kz.krisha.utils.CreateUser;
import kz.krisha.utils.ReadConfig;
import kz.krisha.pages.*;
import kz.krisha.utils.Screenshoter;
import kz.krisha.utils.Utils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static kz.krisha.utils.Constants.*;

public class KrishaTests extends BaseTest{

    @Test(groups = "UITest")
    public void isSuccessfulLogin() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        LogIn logIn = new LogIn(driver);
        mainPage.openLoginPage();
        loginPage = logIn.logIn(CreateUser.getUser());
        softAssertions.assertThat(loginPage.isPostAdButtonIsDisplayedInCabinet())
                .isTrue()
                .overridingErrorMessage("Login is failed");
        Screenshoter.takeScreenshot(driver);
    }

    @Test(groups = "UITest")
    public void verifyPageContentInDefaultState() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        softAssertions.assertThat(mainPage.isCategoryDropDownListDisplayed())
                .isTrue()
                .overridingErrorMessage("Category ddlist is not displayed");
        softAssertions.assertThat(mainPage.isRoomCountDropDownListDisplayed())
                .isTrue()
                .overridingErrorMessage("Room ddlist is not displayed");
        softAssertions.assertThat(mainPage.isSearchButtonDisplayed())
                .isTrue()
                .overridingErrorMessage("Search button is not displayed");
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkCorrectTextInResultOfSearch() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(driver);
        mainPage.fillMainFilters();
        softAssertions.assertThat(mainPage.getTitleTextFromPage())
                .containsIgnoringCase(RENT_MEDEO_SIMPLE_FILTERS_TEXT)
                .overridingErrorMessage("Text is not correct");

    }

    @Test(groups = "UITest")
    public void checkResultOfSpecialFilters() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.fillMainFilters();
        mainPageWithAdditionalFilters.fillAdditionalFilters();
        softAssertions.assertThat(mainPageWithAdditionalFilters.getTitleTextAfterAdditionalFilters())
                .containsIgnoringCase(RENT_MEDEO_ADDITIONAL_FILTERS_TEXT)
                .overridingErrorMessage("Text is not correct");
    }

    @Test(groups = "UITest")
    public void checkValuesInAd() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.fillMainFilters();
        mainPageWithFilters
                .fillAdditionalFilters()
                .clickToFirstAd();
        AdPage adPage = new AdPage(driver);
        Utils utils = new Utils(driver);
        utils.switchTab();
        adPage.pressHideHint();
        softAssertions.assertThat(adPage.checkFloorIsNotFirst())
                .isTrue()
                .overridingErrorMessage("Floor is first");
        softAssertions.assertThat(adPage.checkSquareIsMoreThanMin(MIN_SQUARE))
                .isTrue()
                .overridingErrorMessage("Square is more than min");
        softAssertions.assertThat(adPage.checkOfferPriceLessThanMaxPrice(MAX_PRICE))
                .isTrue()
                .overridingErrorMessage("Offer price less than max price");
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkPhoneNumber() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(driver);
        mainPage.pressNewBuildingsLink();
        NewBuildingsPage newBuildingsPage = new NewBuildingsPage(driver);
        softAssertions.assertThat(newBuildingsPage.isCorrectPhoneNumber())
                .isTrue()
                .overridingErrorMessage("Phone number is incorrect");
    }

    @Test(groups = "UITest")
    public void checkPhotoIsDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
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
        softAssertions.assertThat(adPage.checkAdMainPhotoIsDisplayed())
                .isTrue()
                .overridingErrorMessage("Photo is not displayed");
    }
}
