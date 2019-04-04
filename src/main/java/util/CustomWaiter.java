package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWaiter {

    //method waits until the element is clickable
    public void waitUntilElementIsClickable(int timeOutSeconds, By elementLocator, WebDriver driver){
        WebDriverWait wait;
        wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

}
