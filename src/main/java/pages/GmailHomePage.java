package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GmailHomePage extends Page {

    private static final By LOGIN_INPUT = By.id("identifierId");
    private static final By LOGIN_NEXT_BUTTON = By.id("identifierNext");
    private static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    private static final By PASSWORD_NEXT_BUTTON = By.id("passwordNext");
    private static final String TITLE = "Gmail";

    public GmailHomePage() {
        super(TITLE);
        PageFactory.initElements(driver, this);
    }


    public void login(String login, String password) {
        this.driver.findElement(LOGIN_INPUT).sendKeys(login);
        this.driver.findElement(LOGIN_NEXT_BUTTON).click();
        explicitlyWaitUntilElementIsClickable(4, PASSWORD_INPUT);
        this.driver.findElement(PASSWORD_INPUT).sendKeys(password);
        this.driver.findElement(PASSWORD_NEXT_BUTTON).click();
    }

    //method waits until the element is clickable
    private void explicitlyWaitUntilElementIsClickable(int timeOutSeconds, By elementLocator){
        WebDriverWait wait;
        wait = new WebDriverWait(this.driver, timeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }
}
