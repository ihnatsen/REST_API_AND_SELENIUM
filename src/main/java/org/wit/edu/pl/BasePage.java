package org.wit.edu.pl;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public abstract class BasePage  {

    public WebDriver driver;
    public String URL;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage(){
        driver.get(URL);
    }

}
