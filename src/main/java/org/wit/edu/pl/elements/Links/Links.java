package org.wit.edu.pl.elements.Links;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;


public class Links extends BasePage {

    @FindBy(linkText="Home")
    WebElement home;

    @FindBy(xpath="//a[@id='dynamicLink']")
    WebElement dynamicLink;


    public Links(WebDriver driver) {
        super(driver);
        URL = PageMenu.Links.getURL();
    }

    public void clickHomeButton(){
        home.click();
    }

    public void clickDynamicLink(){
        waitUntilElementToBeVisibility(dynamicLink);
        dynamicLink.click();
    }

    public void goFollowTab(){
        String[] handles = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles[handles.length-1]);
        wait.until(driver -> !driver.getCurrentUrl().equals("about:blank"));
    }


    public String getURL(){
        return driver.getCurrentUrl();
    }

}
