package junit;

import com.sun.org.glassfish.gmbal.Description;
import driver.Driver;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.GmailHomePage;
import pages.GmailInboxPage;
import util.GmailTestWatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(GmailTestWatcher.class)
public class GmailSendEmailTests {

    private static final String USER_EMAIL_1 = "seleniumtests10@gmail.com";
    private static final String USER_EMAIL_2 = "seleniumtests30@gmail.com";
    private static final String USER_PASSWORD = "060788avavav";
    private static final String EMAIL_THEME = "This is test email";
    private static final String EMAIL_BODY = "THIS IS THE BODY OF THE TEST EMAIL. HELLO!";
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

    @Feature("Send Email")
    @Description("Verify the ability to send email. Checks the email on the receiver's side")
    @Test
    public void sendEmailTestAndCheckInboxOfReceiver() {
        gmailInboxPage = gmailHomePage.login(USER_EMAIL_1, USER_PASSWORD);
        gmailInboxPage = gmailInboxPage.sendLetter(USER_EMAIL_2, EMAIL_THEME, EMAIL_BODY);
        gmailHomePage = gmailInboxPage.logOut();
        gmailHomePage.clickButtonsToChangeAccount();
        gmailInboxPage = gmailHomePage.login(USER_EMAIL_2, USER_PASSWORD);
        assertEquals(USER_EMAIL_1, gmailInboxPage.getEmailOfTheLastInboxLetter());
    }

    @Feature("Send Email")
    @Description("Verify the ability to send email. Checks the email on the sender's side")
    @Test
    public void sendEmailAndCheckSentBoxOfSender() {
        gmailInboxPage = gmailHomePage.login(USER_EMAIL_1, USER_PASSWORD);
        gmailInboxPage = gmailInboxPage.sendLetter(USER_EMAIL_2, EMAIL_THEME, EMAIL_BODY);
        assertEquals(USER_EMAIL_2, gmailInboxPage.getEmailOfLastSentLetter());

    }

}
