package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;


public abstract class Page {
    WebDriver driver;

    Page() {
        driver = Driver.getInstance().getWebDriver();
    }

}
