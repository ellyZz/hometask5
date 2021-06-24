package kz.krisha.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kz.krisha.pages.AdPage;
import kz.krisha.pages.MainPage;
import kz.krisha.pages.MainPageWithAdditionalFilters;
import kz.krisha.utils.Utils;
import org.assertj.core.api.SoftAssertions;

import static kz.krisha.utils.Constants.RENT_MEDEO_ADDITIONAL_FILTERS_TEXT;
import static kz.krisha.utils.Constants.RENT_MEDEO_SIMPLE_FILTERS_TEXT;

public class SearchSteps {
    @Given("^fill main filters$")
    public void fillMainFilters() {
        MainPage mainPage = new MainPage();
        mainPage.fillMainFilters();
    }

    @Then("^text on page after main filters matches with text in case$")
    public void textOfPageMatchesWithTextCase() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainPage mainPage = new MainPage();
        softAssertions.assertThat(mainPage.getTitleTextFromPage())
                .containsIgnoringCase(RENT_MEDEO_SIMPLE_FILTERS_TEXT)
                .overridingErrorMessage("Text is not correct");
    }

    @When("^fill additional filters$")
    public void fillAdditionalFilters() {
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters();
        mainPageWithAdditionalFilters.fillAdditionalFilters();

    }

    @Then("^text on page after additional filters matches with text in case$")
    public void textOnPageAfterAdditionalFiltersMatchesWithTextInCase() {
        MainPageWithAdditionalFilters mainPageWithAdditionalFilters = new MainPageWithAdditionalFilters();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mainPageWithAdditionalFilters.getTitleTextAfterAdditionalFilters())
                .containsIgnoringCase(RENT_MEDEO_ADDITIONAL_FILTERS_TEXT)
                .overridingErrorMessage("Text is not correct");
    }

    @And("^click to first ad$")
    public void clickToFirstAd() {
        MainPageWithAdditionalFilters mainPageWithFilters = new MainPageWithAdditionalFilters();
        mainPageWithFilters.clickToFirstAd();
    }

    @And("^switch to ad page$")
    public void switchToAdPage() {
        Utils utils = new Utils();
        utils.switchTab();
    }

    @And("^press hide hint$")
    public void pressHideHint() {
        AdPage adPage = new AdPage();
        adPage.pressHideHint();
    }

    @Then("^values in ad matches with \"([^\"]*)\",\"([^\"]*)\"$")
    public void valuesInAdMatchesWith(String minSquare, String maxPrice) {
        SoftAssertions softAssertions = new SoftAssertions();
        AdPage adPage = new AdPage();
        softAssertions.assertThat(adPage.checkFloorIsNotFirst())
                .isTrue()
                .overridingErrorMessage("Floor is first");
        softAssertions.assertThat(adPage.checkSquareIsMoreThanMin(minSquare))
                .isTrue()
                .overridingErrorMessage("Square is more than min");
        softAssertions.assertThat(adPage.checkOfferPriceLessThanMaxPrice(maxPrice))
                .isTrue()
                .overridingErrorMessage("Offer price less than max price");
        softAssertions.assertAll();
    }
}
