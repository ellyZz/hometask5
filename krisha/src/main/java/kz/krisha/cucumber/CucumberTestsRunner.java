package kz.krisha.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import kz.krisha.driver.WebDriverCustomizer;
import org.testng.annotations.AfterMethod;

@CucumberOptions(plugin = {
        "pretty", "json:target/Cucumber.json",
        "html:target/cucumber-html-report"
},
        features = "src/test/java/cucumber_features",
        glue = {"kz/krisha/cucumber/steps"})

public class CucumberTestsRunner extends AbstractTestNGCucumberTests {
    @AfterMethod
    public void closeDriver() {
        WebDriverCustomizer.quitDriver();
    }

}
