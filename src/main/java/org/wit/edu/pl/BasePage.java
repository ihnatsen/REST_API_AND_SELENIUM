package org.wit.edu.pl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public abstract class BasePage  {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    public WebDriver driver;
    public WebDriverWait wait;
    public String URL;
    public Actions make;
    public JavascriptExecutor js;


    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.make = new Actions(driver);
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.js = (JavascriptExecutor) driver;
    }

    protected void waitUntilElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitUntilElementToBeVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilBeTheConditions(Boolean conditions){
        wait.until((WebDriver driver) -> conditions);
    }

    protected void doJSLeftClick(WebElement element){
        js.executeScript("arguments[0].click()", element);
    }


    protected void scrollToElement(WebElement element){
        make.scrollToElement(element);
    }


    public void openPage(){
        driver.get(URL);
    }

    public void quitDriver(){driver.quit();}

    public void closePage(){
        driver.close();
    }

    public void deleteAdds(){
        make.pause(Duration.ofSeconds(5)).perform();
        js.executeScript(
        "let adds = document.querySelectorAll('iframe, .advertisement');" +
                "adds.forEach(a => a.remove());");
        js.executeScript("document.querySelector('footer').remove();");
    }

    public Boolean hasAttribute(WebElement element, String attributeName, String value){
        return element.getAttribute(attributeName).contains(value);
    }

    public Boolean hasAttribute(WebElement element, String attributeName){
        return Optional.ofNullable(element.getAttribute(attributeName)).isPresent();
    }


    protected void clearField(WebElement element){
        element.clear();
    }
}
