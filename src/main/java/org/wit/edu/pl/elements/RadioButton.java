package org.wit.edu.pl.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wit.edu.pl.BasePage;

import java.time.Duration;
import java.util.Optional;

public class RadioButton extends BasePage{

    @FindBy(id = "yesRadio")
    private WebElement yesButton;

    @FindBy(id = "impressiveRadio")
    private WebElement impressiveButton;

    @FindBy(id = "noRadio")
    private WebElement noButton;

    @FindBy(xpath = "//p[@class='mt-3']/span")
    private WebElement uHaveSelected;

    public RadioButton(WebDriver driver) {
        super(driver);
        URL = "https://demoqa.com/radio-button";
    }

    private void clickToSomeButton(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
    }

    public String clickToYesButton(){
        clickToSomeButton(yesButton);
        return "Yes";
    }

    public String clickToImpressiveButton(){
        clickToSomeButton(impressiveButton);
        return "Impressive";
    }

    public boolean noButtonHasDisabledAttribute(){
        return Optional.ofNullable(noButton.getAttribute("disabled")).isPresent();
    }

    public boolean outputHas(String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        uHaveSelected = wait.until(ExpectedConditions.visibilityOf(uHaveSelected));
        return uHaveSelected.getText().equals(text);
    }

}
