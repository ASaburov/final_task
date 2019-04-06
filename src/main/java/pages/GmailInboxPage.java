package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.CustomWaiter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GmailInboxPage extends Page {

    private static final By USER_ACCOUNT_BUTTON = By.cssSelector("a[aria-label*='@gmail.com']");
    private static final By SIGN_OUT_BUTTON = By.cssSelector("a#gb_71");
    private static final By CREATE_EMAIL_BUTTON = By.cssSelector("div [gh='cm']");
    private static final By TO_TEXTAREA = By.cssSelector("textarea[name='to']");
    private static final By SUBJECT_TEXTAREA = By.cssSelector("input[name='subjectbox']");
    private static final By EMAIL_BODY_INPUT = By.cssSelector("div[class*='editable']");
    private static final By SEND_BUTTON = By.cssSelector("div [aria-label*='Enter']");
    private static final By SENDER_DATA_OF_OPENED_EMAIL = By.cssSelector("td h3 span[email][name]");
    private static final By RECEIVER_DATA_OF_OPENED_EMAIL = By.cssSelector("span.hb span[email][name]");
    private static final By LAST_EMAIL = By.cssSelector("div[gh='tl'] tr.zA:first-child td:nth-of-type(6)");
    private static final By TRASH_LETTERS_QTY = By.xpath("//div[@class=\"D E G-atb\" and not(contains(@style,'display'))]//span[@class='Dj']/span[2]");
    private static final By REMOVE_SEARCH_REQUEST_BUTTON = By.xpath("//form[@id=\"aso_search_form_anchor\"]//button[not(contains(@gh, \"sda\"))][2]");
    private static final By SUCCESSFULLY_SENT_EMAIL_POPUP = By.cssSelector("div.vh span");

    private static final String TRASH_PAGE_URL = "https://mail.google.com/mail/u/0/#trash";
    private static final String INBOX_PAGE_URL = "https://mail.google.com/mail/u/1/#inbox";


    public GmailInboxPage() {
        PageFactory.initElements(driver, this);
    }


    //this method sends a letter
    public GmailInboxPage sendLetter(String email, String theme, String body) {
        this.driver.findElement(CREATE_EMAIL_BUTTON).click();
        CustomWaiter.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(TO_TEXTAREA));
        this.driver.findElement(TO_TEXTAREA).click();
        this.driver.findElement(TO_TEXTAREA).sendKeys(email);
        this.driver.findElement(SUBJECT_TEXTAREA).sendKeys(theme);
        CustomWaiter.getWebDriverWait().until(ExpectedConditions.textToBe(By.cssSelector("div.aYF"), theme));
        this.driver.findElement(EMAIL_BODY_INPUT).click();
        this.driver.findElement(EMAIL_BODY_INPUT).sendKeys(body);
        this.driver.findElement(SEND_BUTTON).click();
        CustomWaiter.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULLY_SENT_EMAIL_POPUP));

        return this;
    }

    public GmailHomePage logOut() {
        this.driver.findElement(USER_ACCOUNT_BUTTON).click();
        this.driver.findElement(SIGN_OUT_BUTTON).click();

        return new GmailHomePage();
    }

    public boolean isSignOutButtonEnabled() {
        return this.driver.findElement(SIGN_OUT_BUTTON).isEnabled();
    }

    //method goes to Inbox, opens the last email from the Inbox
    public String getEmailOfTheLastInboxLetter() {
        this.driver.get(INBOX_PAGE_URL);
        CustomWaiter.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(LAST_EMAIL));
        this.driver.findElement(LAST_EMAIL).click();

        return this.getEmailFromOpenedLetter("s");

    }

    //this method goes to the Sent box, opens the last email from the Sent
    public String getEmailOfLastSentLetter() {
        this.driver.get("https://mail.google.com/mail/u/1/#sent");
        CustomWaiter.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(LAST_EMAIL));
        this.driver.findElement(LAST_EMAIL).click();

        return this.getEmailFromOpenedLetter("r");

    }


    /*method looks at the current opened letter and returns email of receiver or email of sender.
    which one email will be returned? It depends on the parameter that we pass to the method: 's' - returns
    the email of sender; 'r' - returns the email of receiver*/
    public String getEmailFromOpenedLetter(String whoseEmail) {
        String email = "";
        switch (whoseEmail) {
            case "s":
                email = this.driver.findElement(SENDER_DATA_OF_OPENED_EMAIL).getAttribute("email");
                break;
            case "r":
                email = this.driver.findElement(RECEIVER_DATA_OF_OPENED_EMAIL).getAttribute("email");
                break;
        }
        return email;
    }


    public GmailInboxPage removeLastFourLetters() {
        this.driver.get(INBOX_PAGE_URL);
        Robot c3po = null;
        try {
            c3po = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        c3po.mouseMove(281, 259);
        c3po.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        c3po.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        c3po.mouseMove(281, 380);
        c3po.keyPress(KeyEvent.VK_SHIFT);
        c3po.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        c3po.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        c3po.keyRelease(KeyEvent.VK_SHIFT);
        c3po.mouseMove(429, 163);
        c3po.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        c3po.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        return this;
    }

    public int getQtyOfLettersInTrash() {
        this.driver.get(TRASH_PAGE_URL);
        CustomWaiter.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(REMOVE_SEARCH_REQUEST_BUTTON));
        WebElement trashLettersQty;
        try {
            trashLettersQty = this.driver.findElement(TRASH_LETTERS_QTY);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return 0;
        }
        return Integer.parseInt(trashLettersQty.getText());
    }

}
