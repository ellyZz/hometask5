package kz.krisha.cases;

import kz.krisha.pages.*;
import kz.krisha.steps.AdPageSteps;
import kz.krisha.steps.LogIn;
import kz.krisha.steps.MainPageSteps;
import kz.krisha.utils.CreateUser;
import kz.krisha.utils.TestsListener;
import kz.krisha.utils.Utils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static kz.krisha.utils.Constants.RENT_MEDEO_ADDITIONAL_FILTERS_TEXT;
import static kz.krisha.utils.Constants.RENT_MEDEO_SIMPLE_FILTERS_TEXT;

@Listeners(TestsListener.class)
public class KrishaTests extends BaseTest {

    @Test(groups = "UITest")
    public void isSuccessfulLogin() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        mainPage.openLoginPage();
        new LogIn().logIn(CreateUser.getUser());
        softAssertions.assertThat(loginPage.isPostAdButtonIsDisplayedInCabinet())
                .isTrue()
                .overridingErrorMessage("Login is failed");
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void verifyPageContentInDefaultState() {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.isDefaultContentDisplayed();
    }

    @Test(groups = "UITest")
    public void checkCorrectTextInResultOfSearch() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage();
        mainPage.fillMainFilters();
        softAssertions.assertThat(mainPage.getTitleTextFromPage())
                .containsIgnoringCase(RENT_MEDEO_SIMPLE_FILTERS_TEXT)
                .overridingErrorMessage("Text is not correct");
        softAssertions.assertAll();

    }

    @Test(groups = "UITest")
    public void checkResultOfSpecialFilters() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters();
        MainPage mainPage = new MainPage();
        mainPage.fillMainFilters();
        mainPageWithAdditionalFilters.fillAdditionalFilters();
        softAssertions.assertThat(mainPageWithAdditionalFilters.getTitleTextAfterAdditionalFilters())
                .containsIgnoringCase(RENT_MEDEO_ADDITIONAL_FILTERS_TEXT)
                .overridingErrorMessage("Text is not correct");
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkValuesInAd() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters();
        MainPage mainPage = new MainPage();
        AdPageSteps adPageSteps = new AdPageSteps();
        mainPage.fillMainFilters();
        mainPageWithFilters
                .fillAdditionalFilters()
                .clickToFirstAd();
        AdPage adPage = new AdPage();
        Utils utils = new Utils();
        utils.switchTab();
        adPage.pressHideHint();
        adPageSteps.isCorrectValuesInAddAfterAllFilters();
    }

    @Test(groups = "UITest")
    public void checkPhoneNumber() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage();
        mainPage.pressNewBuildingsLink();
        NewBuildingsPage newBuildingsPage = new NewBuildingsPage();
        softAssertions.assertThat(newBuildingsPage.isCorrectPhoneNumber())
                .isTrue()
                .overridingErrorMessage("Phone number is incorrect");
        softAssertions.assertAll();
    }

    @Test(groups = "UITest")
    public void checkPhotoIsDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters();
        MainPage mainPage = new MainPage();
        mainPage.selectHavePhotoCheckBox();
        mainPageWithAdditionalFilters
                .clickSearchButton()
                .clickToFirstAd();
        AdPage adPage = new AdPage();
        Utils utils = new Utils();
        utils.switchTab();
        adPage.pressHideHint();
        softAssertions.assertThat(adPage.checkAdMainPhotoIsDisplayed())
                .isTrue()
                .overridingErrorMessage("Photo is not displayed");
        softAssertions.assertAll();
    }
}
