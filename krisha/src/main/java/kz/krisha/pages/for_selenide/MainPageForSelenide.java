package kz.krisha.pages.for_selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPageForSelenide {

    private static final By CABINET_LINK = By.xpath("//li[@class='cabinet-link-item']/a[@class='cabinet-link']");

    public void openLoginPage() {
        $(CABINET_LINK).shouldBe(visible).click();
    }

}
