package kz.krisha.steps;

import kz.krisha.pages.AbstractPage;
import kz.krisha.pages.AdPage;
import org.assertj.core.api.SoftAssertions;

import static kz.krisha.utils.Constants.MAX_PRICE;
import static kz.krisha.utils.Constants.MIN_SQUARE;

public class AdPageSteps extends AbstractPage {

    public void isCorrectValuesInAddAfterAllFilters() {
        SoftAssertions softAssertions = new SoftAssertions();
        AdPage adPage = new AdPage();
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
}
