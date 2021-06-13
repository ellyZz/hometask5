package kz.krisha.cases;

import kz.krisha.pages.yandex.DiskAccountPage;
import kz.krisha.pages.yandex.LoginPage;
import kz.krisha.pages.yandex.MainPage;
import kz.krisha.pages.yandex.TrashPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static kz.krisha.pages.Constants.*;

public class YandexDiskTests {
    private WebDriver driver;

    @BeforeMethod
    public void setDriver() throws MalformedURLException {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//        driver = new ChromeDriver();
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        driver.get("https://disk.yandex.kz/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test(priority = 1)
    public void isFileDeleted() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        TrashPage trashPage = new TrashPage(driver);
        DiskAccountPage diskAccountPage = new DiskAccountPage(driver);
        mainPage.clickLoginIcon();
        loginPage.login(LOGIN, PASSWORD);
        diskAccountPage
                .moveFirstFileInTrash()
                .openTrash();
        trashPage.isFileInTrash();
    }

    @Test(priority = 2)
    public void isFileRestored() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DiskAccountPage diskAccountPage = new DiskAccountPage(driver);
        TrashPage trashPage = new TrashPage(driver);
        mainPage.clickLoginIcon();
        loginPage.login(LOGIN, PASSWORD);
        diskAccountPage.openTrash();
        trashPage.selectFileForRestore();
        trashPage.restoreFile();
    }
}
