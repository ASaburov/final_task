package junit;

import com.sun.org.glassfish.gmbal.Description;
import driver.Driver;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.GmailHomePage;
import pages.GmailInboxPage;
import util.GmailTestWatcher;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(GmailTestWatcher.class)
public class GmailLoginTests {

    private static final String CSV_FILE_PATH = "/login_data.csv";
    private GmailHomePage gmailHomePage = new GmailHomePage();
    private GmailInboxPage gmailInboxPage;

    @BeforeEach
    public void openBrowser() {
        Driver.getInstance().openHomePage();
    }

    @AfterEach
    public void closeBrowser() {
        Driver.getInstance().closeDriver();
    }

    //test logs in the app and check whether username appeared in the corresponding field
    @Feature("Login")
    @Description("Verify the ability to log in")
    @ParameterizedTest
    @CsvFileSource(resources = CSV_FILE_PATH)
    public void loginProcessTest(String login, String password) {
        gmailInboxPage = gmailHomePage.login(login + "@gmail.com", password);
        assertTrue(gmailInboxPage.isSignOutButtonEnabled());
    }







}


