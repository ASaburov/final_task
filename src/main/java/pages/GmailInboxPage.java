package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import util.CustomWaiter;

public class GmailInboxPage extends Page {

    private static final By USER_ACCOUNT_BUTTON = By.cssSelector("a[aria-label*='@gmail.com']");
    //private static final By SIGN_OUT_BUTTON = By.xpath("//a[contains(text(),'Sign out')]");
    private static final By SIGN_OUT_BUTTON = By.cssSelector("a#gb_71");
    private static final By CREATE_EMAIL_BUTTON = By.cssSelector("div [gh='cm']");
    private static final By TO_TEXTAREA = By.cssSelector("textarea[name='to']");
    private static final By SUBJECT_TEXTAREA = By.cssSelector("input[name='subjectbox']");
    private static final By EMAIL_BODY_INPUT = By.cssSelector("div[class*='editable']");
    private static final By SEND_BUTTON = By.cssSelector("div [aria-label*='Enter']");
    private static final By LAST_EMAIL = By.cssSelector("[class='yW'] span:first-of-type");
    private static final By SENDER_DATA_OF_OPENED_EMAIL = By.cssSelector("td h3 span[email][name]");
    private static final By RECEIVER_DATA_OF_OPENED_EMAIL = By.cssSelector("span.hb span[email][name]");
    private static final By LAST_SENT_EMAIL = By.cssSelector("div[gh='tl'] tr.zA:first-child td:nth-of-type(6)"); //need to change selector!

    public GmailInboxPage() {
        PageFactory.initElements(driver, this);
    }

    public String getEmailOfTheLastInboxLetter() {
        this.driver.findElement(LAST_EMAIL).click();

        return this.getSenderEmailFromOpenedLetter();

    }

    public GmailInboxPage sendLetter(String email, String theme, String body) {
        this.driver.findElement(CREATE_EMAIL_BUTTON).click();
        new CustomWaiter().waitUntilElementIsClickable(15, TO_TEXTAREA, Driver.getInstance().getWebDriver());
        this.driver.findElement(TO_TEXTAREA).click();
        this.driver.findElement(TO_TEXTAREA).sendKeys(email);
        this.driver.findElement(SUBJECT_TEXTAREA).sendKeys(theme);
        this.driver.findElement(EMAIL_BODY_INPUT).click();
        this.driver.findElement(EMAIL_BODY_INPUT).sendKeys(body);
        this.driver.findElement(SEND_BUTTON).click();

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

    public String getEmailOfLastSentLetter() {
        this.driver.get("https://mail.google.com/mail/u/1/#sent");
        new CustomWaiter().waitUntilElementIsClickable(20, LAST_SENT_EMAIL, this.driver);
        return this.getReceiverEmailFromOpenedLetter();

    }

    //method that helps to get 'email' attribute related to sender of the opened email
    public String getSenderEmailFromOpenedLetter() {
        this.driver.findElement(LAST_SENT_EMAIL).click();
        new CustomWaiter().waitUntilElementIsClickable(20, SENDER_DATA_OF_OPENED_EMAIL, Driver.getInstance().getWebDriver());
        return this.driver.findElement(SENDER_DATA_OF_OPENED_EMAIL).getAttribute("email");
    }

    //method that helps to get 'email' attribute related to receiver of the opened email
    public String getReceiverEmailFromOpenedLetter() {
        this.driver.findElement(LAST_SENT_EMAIL).click();
        //new CustomWaiter().waitUntilElementIsClickable(20, RECEIVER_DATA_OF_OPENED_EMAIL, Driver.getInstance().getWebDriver());
        return this.driver.findElement(RECEIVER_DATA_OF_OPENED_EMAIL).getAttribute("email");
    }

}
