package kz.krisha.steps;

import kz.krisha.pages.AbstractPage;
import kz.krisha.pages.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

public class MainPageSteps extends AbstractPage {

    public MainPageSteps(WebDriver driver) {
        super(driver);
    }

    public void isDefaultContentDisplayed() {
        SoftAssertions softAssertions = new SoftAssertions();
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
}
