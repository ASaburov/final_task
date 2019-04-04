package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import util.CustomWaiter;


public class GmailHomePage extends Page {

    private static final By LOGIN_INPUT = By.id("identifierId");
    private static final By LOGIN_NEXT_BUTTON = By.id("identifierNext");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private static final By PASSWORD_NEXT_BUTTON = By.id("passwordNext");
    private static final By PROFILE_IDENTIFIER = By.xpath("//div[@id='profileIdentifier']");
    private static final By CHANGE_ACCOUNT_BUTTON = By.cssSelector("div[jsname='rwl3qc']");

    public GmailHomePage() {
        PageFactory.initElements(driver, this);
    }


    public GmailInboxPage login(String login, String password) {
        new CustomWaiter().waitUntilElementIsClickable(15, LOGIN_INPUT, Driver.getInstance().getWebDriver());
        this.driver.findElement(LOGIN_INPUT).sendKeys(login);
        new CustomWaiter().waitUntilElementIsClickable(15, LOGIN_NEXT_BUTTON, Driver.getInstance().getWebDriver());
        this.driver.findElement(LOGIN_NEXT_BUTTON).click();
        (new CustomWaiter()).waitUntilElementIsClickable(15, PASSWORD_INPUT, Driver.getInstance().getWebDriver());
        this.driver.findElement(PASSWORD_INPUT).sendKeys(password);
        this.driver.findElement(PASSWORD_NEXT_BUTTON).click();

        return new GmailInboxPage();
    }

    public boolean isPasswordInputEnabled(){
        return this.driver.findElement(PASSWORD_INPUT).isEnabled();
    }

    public void clickButtonsToChangeAccount(){
        new CustomWaiter().waitUntilElementIsClickable(15, PROFILE_IDENTIFIER, Driver.getInstance().getWebDriver());
        this.driver.findElement(PROFILE_IDENTIFIER).click();
        new CustomWaiter().waitUntilElemenIsDisplayed(15, CHANGE_ACCOUNT_BUTTON, Driver.getInstance().getWebDriver());
        this.driver.findElement(CHANGE_ACCOUNT_BUTTON).click();
    }


}
