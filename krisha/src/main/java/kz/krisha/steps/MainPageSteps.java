package kz.krisha.steps;

import kz.krisha.pages.AbstractPage;
import kz.krisha.pages.MainPage;
import org.assertj.core.api.SoftAssertions;

public class MainPageSteps extends AbstractPage {


    public void isDefaultContentDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage();
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
}
