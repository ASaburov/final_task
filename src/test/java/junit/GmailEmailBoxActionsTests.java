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
public class GmailEmailBoxActionsTests {
    private static final String USER_EMAIL = "seleniumtests30@gmail.com";
    private static final String USER_PASSWORD = "060788avavav";
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


    //test log in the app, selects 4 last letters and removes them, then checks whether removed
    //emails are displayed in the trash

    @Feature("Remove Email")
    @Description("Verify the ability to remove an email")
    @Test
    public void removeEmailsAndCheckTrash() {
        gmailInboxPage = gmailHomePage.login(USER_EMAIL, USER_PASSWORD);
        int qtyBefore = gmailInboxPage.getQtyOfLettersInTrash();
        gmailInboxPage.removeLastFourLetters();
        int qtyAfter = gmailInboxPage.getQtyOfLettersInTrash();
        assertEquals(qtyBefore + 4, qtyAfter);

    }
}
