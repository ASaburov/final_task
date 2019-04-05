package junit;

import driver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.GmailHomePage;
import pages.GmailInboxPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GmailEmailBoxActionsTests {
    private static final String USER_EMAIL_1 = "seleniumtests30@gmail.com";
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

    @Test
    public void removeEmailsAndCheckTrash() {
        gmailInboxPage = gmailHomePage.login(USER_EMAIL_1, USER_PASSWORD);
        int qtyBefore = gmailInboxPage.getQtyOfLettersInTrash();
        gmailInboxPage.removeLastFourLetters();
        int qtyAfter = gmailInboxPage.getQtyOfLettersInTrash();
        assertEquals(qtyBefore + 4, qtyAfter);

    }
}
