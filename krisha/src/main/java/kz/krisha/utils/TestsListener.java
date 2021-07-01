package kz.krisha.utils;

import kz.krisha.driver.WebDriverCustomizer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestsListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Logger.error("Test failed " + result.getName());
        Screenshoter.takeScreenshot(WebDriverCustomizer.get());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logger.info("Test skipped");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        Logger.error("Test failed with timeout");
    }

    @Override
    public void onStart(ITestContext context) {
        Logger.info("Starting tests");
    }

    @Override
    public void onFinish(ITestContext context) {
        Logger.info("Finish tests");
    }
}
