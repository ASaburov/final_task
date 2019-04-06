package util;

import driver.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class GmailTestWatcher implements TestWatcher {

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] captureScreenshot() {
        return ((TakesScreenshot) Driver.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {

    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        captureScreenshot();
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {

    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        captureScreenshot();

    }

}
