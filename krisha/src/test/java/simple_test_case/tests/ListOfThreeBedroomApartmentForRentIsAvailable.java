package simple_test_case.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import simple_test_case.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class ListOfThreeBedroomApartmentForRentIsAvailable {
    private WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://krisha.kz");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void checkCorrectTextInResultOfSearch(){
        MainPage mainPage = new MainPage(driver);
        mainPage.selectRentCategory();
        mainPage.selectRegion();
        mainPage.inputPrice("30000000");
        mainPage.selectHavePhotoCheckBox();
        mainPage.selectOwnerCheckBox();
        mainPage.selectAgency();
        mainPage.selectRoomCount();
        mainPage.selectRoomCount();
        mainPage.pressSearchButton();
        mainPage.checkValidText("Аренда трехкомнатных квартир от хозяев в Медеуском р-не Алматы");
    }
}
