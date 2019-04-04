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
    private static final By LAST_LETTER = By.cssSelector("[class='yW'] span:first-of-type");
    private static final By OPENED_LETTER_EMAIL_AND_OTHER_DATA = By.cssSelector("td h3 span[email][name]");


    public GmailInboxPage() {
        PageFactory.initElements(driver, this);
    }

    public String getEmailOfTheFirstLetter() {
        this.driver.findElement(LAST_LETTER).click();
        String email = this.driver.findElement(OPENED_LETTER_EMAIL_AND_OTHER_DATA).getAttribute("email");
        System.out.println(email);
        return email;

    }

    public GmailInboxPage sendLetter(String email, String theme, String body) {
        this.driver.findElement(CREATE_EMAIL_BUTTON).click();
        new CustomWaiter().waitUntilElementIsClickable(20, TO_TEXTAREA, Driver.getInstance().getWebDriver());
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


}
