package stategy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SetupGridDriver implements SetupStrategy {
    private String URL = System.getProperty("url");
    private String PORT = System.getProperty("port");

    @Override
    public WebDriver setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(URL + ":"+ PORT +"/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
