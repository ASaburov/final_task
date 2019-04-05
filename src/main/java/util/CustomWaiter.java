package util;

import driver.Driver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWaiter {

    public static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(Driver.getInstance().getWebDriver(), 10);
    }
}
