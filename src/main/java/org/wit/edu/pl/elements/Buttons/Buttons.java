package org.wit.edu.pl.elements.Buttons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;

public class Buttons extends BasePage {

    @FindBy(id="doubleClickBtn")
    private WebElement doubleClickMe;

    @FindBy(id="rightClickBtn")
    private WebElement rightClickMe;

    @FindBy(xpath="//button[text()='Click Me']")
    private WebElement clickMe;

    @FindBy(xpath="//p[@id='dynamicClickMessage']")
    private WebElement clickClickMessage;

    @FindBy(xpath="//p[@id='rightClickMessage']")
    private WebElement rightClickMessage;

    @FindBy(xpath="//p[@id='doubleClickMessage']")
    private WebElement doubleClickMessage;

    public Buttons(WebDriver driver) {
        super(driver);
        URL = PageMenu.Buttons.getURL();
    }

    public void clickDoubleClick(){
        make.doubleClick(doubleClickMe).perform();
    }

    public void clickRightClick(){
        make.contextClick(rightClickMe).perform();
    }

    public void clickClickMe(){
        clickMe.click();
    }

    private String getMessage(WebElement element){
        waitUntilElementToBeVisibility(element);
        scrollToElement(element);
        return element.getText();
    }

    public String getDoubleClickMessage(){
        return getMessage(doubleClickMessage);
    }

    public String getRightClickMessage(){
        return getMessage(rightClickMessage);
    }

    public String getClickMeClickMessage(){
        return getMessage(clickClickMessage);
    }

}
