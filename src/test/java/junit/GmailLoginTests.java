package junit;

import com.sun.org.glassfish.gmbal.Description;
import driver.Driver;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.GmailHomePage;
import pages.GmailInboxPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GmailLoginTests {

    private static final String CSV_FILE_PATH = "/login_data.csv";
    private GmailHomePage gmailHomePage = new GmailHomePage();
    private GmailInboxPage gmailInboxPage;


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
    @Description("Verify the ability to log in")
    @ParameterizedTest
    @CsvFileSource(resources = CSV_FILE_PATH)
    public void loginProcessTest(String login, String password) {
        gmailInboxPage = gmailHomePage.login(login + "@gmail.com", password);
        assertTrue(gmailInboxPage.isSignOutButtonEnabled());
    }

    //test logs in the app, then logs out and check that title is correct
    @Feature("Logout")
    @Description("Verify the ability to log out")
    @ParameterizedTest
    @CsvFileSource(resources = CSV_FILE_PATH)
    public void logOutProcessTest(String login, String password) throws InterruptedException {
        gmailInboxPage = gmailHomePage.login(login + "@gmail.com", password);
        gmailHomePage = gmailInboxPage.logOut();
        assertTrue(gmailHomePage.isPasswordInputEnabled());
    }

    @Test
    public void sendEmailTestAndCheckInboxOfReceiver(){
        gmailInboxPage = gmailHomePage.login("seleniumtests10@gmail.com", "060788avavav");
        gmailInboxPage = gmailInboxPage.sendLetter("seleniumtests30@gmail.com", "theme", "email_body");
        gmailHomePage = gmailInboxPage.logOut();
        gmailHomePage.clickButtonsToChangeAccount();
        gmailInboxPage = gmailHomePage.login("seleniumtests30@gmail.com", "060788avavav");
        assertEquals("seleniumtests10@gmail.com", gmailInboxPage.getSenderEmailFromOpenedLetter());
    }

    @Test
    public void sendEmailAndCheckSentBoxOfSender(){
        gmailInboxPage = gmailHomePage.login("seleniumtests10@gmail.com", "060788avavav");
        gmailInboxPage = gmailInboxPage.sendLetter("seleniumtests30@gmail.com", "theme", "email_body");
        assertEquals("seleniumtests30@gmail.com", gmailInboxPage.getEmailOfLastSentLetter());

    }



}


