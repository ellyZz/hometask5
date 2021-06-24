package kz.krisha.cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kz.krisha.pages.MainPage;
import kz.krisha.pages.NewBuildingsPage;
import org.assertj.core.api.SoftAssertions;

public class CheckPhoneNumberSteps {

    @When("^click to link new Buildings$")
    public void clickToLinkNewBuildings() {
        MainPage mainPage = new MainPage();
        mainPage.pressNewBuildingsLink();
    }

    @Then("^check that phone number matches with regex$")
    public void checkThatPhoneNumberMatchesWithRegex() {
        SoftAssertions softAssertions = new SoftAssertions();
        NewBuildingsPage newBuildingsPage = new NewBuildingsPage();
        softAssertions.assertThat(newBuildingsPage.isCorrectPhoneNumber())
                .isTrue()
                .overridingErrorMessage("Phone number is incorrect");
    }
}
