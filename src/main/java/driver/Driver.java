package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static final String HOME_URL = "https://gmail.com";
    private static Driver driver;
    private final ThreadLocal<WebDriver> THREAD_LOCAL_WEBDRIVER;

    private Driver() {
        THREAD_LOCAL_WEBDRIVER = new ThreadLocal<>();
    }

    public static Driver getInstance() {
        if (driver == null) {
            synchronized (Driver.class) {
                if (driver == null) {
                    driver = new Driver();
                }
            }
        }
        return driver;
    }

    public void openHomePage() {
        getWebDriver().navigate().to(HOME_URL);
    }

    public WebDriver getWebDriver() {
        if (THREAD_LOCAL_WEBDRIVER.get() == null) {
            WebDriver d = new FirefoxDriver();
            d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            d.manage().window().maximize();

            THREAD_LOCAL_WEBDRIVER.set(d);
        }

        return THREAD_LOCAL_WEBDRIVER.get();
    }

    public void closeDriver() {
        THREAD_LOCAL_WEBDRIVER.get().close();
        THREAD_LOCAL_WEBDRIVER.remove();
    }
}
