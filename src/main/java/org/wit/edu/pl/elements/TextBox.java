package org.wit.edu.pl.elements;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wit.edu.pl.BasePage;

import java.time.Duration;

public class TextBox extends BasePage {

    // input
    @FindBy(id="userName")
    private WebElement inputFullName;

    @FindBy(id="userEmail")
    private WebElement inputEmail;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private WebElement inputCurrentAddress;

    @FindBy(xpath = "//textarea[@id='permanentAddress']")
    private WebElement inputPermanentAddress;

    // submit

    @FindBy(id = "submit")
    private WebElement submitButton;

    // output
    @FindBy(id ="name")
    private  WebElement outputFullName;

    @FindBy(id ="email")
    private WebElement outputEmail;

    @FindBy(xpath ="//p[@id='currentAddress']")
    private WebElement outputCurrentAddress;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement outputPermanentAddress;


    public TextBox(WebDriver driver) {
        super(driver);
        URL = "https://demoqa.com/text-box";
    }

    private void fillField(WebElement theElement, String data){
        theElement.sendKeys(data);
    }

    public void fillFullName(String data){
        fillField(inputFullName, data);
    }

    public void fillEmail(String data){
        fillField(inputEmail, data);
    }

    public void fillCurrentAddress(String data){
        fillField(inputCurrentAddress, data);
    }

    public void fillPermanentAddress(String data){
        fillField(inputPermanentAddress, data);
    }

    public void clickSubmit(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", submitButton);
    }

    private String getOutputField(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        webElement = wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText().split(":")[1];
    }

    public String getOutputName(){
        return getOutputField(outputFullName);
    }

    public String getOutputEmail(){
        return getOutputField(outputEmail);
    }

    public String getCurrentAddress(){
        return getOutputField(outputCurrentAddress);
    }
    public String getPermanentAddress(){
        return getOutputField(outputPermanentAddress);
    }

}
