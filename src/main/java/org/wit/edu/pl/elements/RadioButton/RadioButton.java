package org.wit.edu.pl.elements.RadioButton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;
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
        URL = PageMenu.RadioButton.getURL();
    }

    private void clickToSomeButton(WebElement webElement){
        doJSLeftClick(webElement);
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
        waitUntilElementToBeVisibility(uHaveSelected);
        scrollToElement(uHaveSelected);
        return uHaveSelected.getText().equals(text);
    }

}
