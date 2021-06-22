package kz.krisha.cases;

import kz.krisha.pages.*;
import kz.krisha.steps.AdPageSteps;
import kz.krisha.steps.LogIn;
import kz.krisha.steps.MainPageSteps;
import kz.krisha.utils.CreateUser;
import kz.krisha.utils.Screenshoter;
import kz.krisha.utils.Utils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static kz.krisha.utils.Constants.RENT_MEDEO_ADDITIONAL_FILTERS_TEXT;
import static kz.krisha.utils.Constants.RENT_MEDEO_SIMPLE_FILTERS_TEXT;

public class KrishaTests extends BaseTest {

    @Test(groups = "UITest")
    public void isSuccessfulLogin() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openLoginPage();
        new LogIn(driver).logIn(CreateUser.getUser());
        softAssertions.assertThat(loginPage.isPostAdButtonIsDisplayedInCabinet())
                .isTrue()
                .overridingErrorMessage("Login is failed");
        Screenshoter.takeScreenshot(driver);
    }

    @Test(groups = "UITest")
    public void verifyPageContentInDefaultState() {
        MainPageSteps mainPageSteps = new MainPageSteps(driver);
        mainPageSteps.isDefaultContentDisplayed();
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
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters(driver);
        MainPage mainPage = new MainPage(driver);
        AdPageSteps adPageSteps = new AdPageSteps(driver);
        mainPage.fillMainFilters();
        mainPageWithFilters
                .fillAdditionalFilters()
                .clickToFirstAd();
        AdPage adPage = new AdPage(driver);
        Utils utils = new Utils(driver);
        utils.switchTab();
        adPage.pressHideHint();
        adPageSteps.isCorrectValuesInAddAfterAllFilters();
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
