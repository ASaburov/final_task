package driver;

import org.openqa.selenium.WebDriver;
import stategy.SetupGridDriver;
import stategy.SetupLocalDriver;


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
        System.setProperty("driver_type", "local");
        String driverType = System.getProperty("driver_type");
        if (THREAD_LOCAL_WEBDRIVER.get() == null) {
            WebDriver driver = null;
            switch (driverType) {
                case "local":
                    SetupLocalDriver localDriver = new SetupLocalDriver();
                    driver = localDriver.setup();
                    break;
                case "remote_grid":
                    SetupGridDriver remoteDriver = new SetupGridDriver();
                    driver = remoteDriver.setup();
                    break;
                default:
            }

            THREAD_LOCAL_WEBDRIVER.set(driver);
        }

        return THREAD_LOCAL_WEBDRIVER.get();
    }

    public void closeDriver() {
        THREAD_LOCAL_WEBDRIVER.get().close();
        THREAD_LOCAL_WEBDRIVER.remove();
    }
}
