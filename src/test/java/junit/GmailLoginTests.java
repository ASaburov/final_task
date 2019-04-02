package junit;

import com.sun.org.glassfish.gmbal.Description;
import driver.Driver;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.GmailHomePage;

public class GmailLoginTests {

    private static final String LOGIN = "seleniumtests10" + "@gmail.com";
    private static final String PASSWORD = "060788avavav";
    private static final String HOME_URL = "https://gmail.com";
    private GmailHomePage gmailHomePage = new GmailHomePage();

    @BeforeEach
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        Driver.getInstance().openHomePage();
    }

    @AfterEach
    public void closeBrowser() {
        Driver.getInstance().closeDriver();
    }

    //test logs in the app and check whether username appeared in the corresponding field
    @Feature("Login")
    @Description("Verify the ability to login")
    @Test
    public void loginProcessTest() throws InterruptedException {
        gmailHomePage.login(LOGIN, PASSWORD);
    }

}
