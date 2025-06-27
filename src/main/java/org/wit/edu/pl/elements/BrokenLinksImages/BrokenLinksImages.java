package org.wit.edu.pl.elements.BrokenLinksImages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;
import static io.restassured.RestAssured.*;

public class BrokenLinksImages extends BasePage {

    @FindBy(xpath="//h1[normalize-space()='Broken Links - Images']/../img[1]")
    WebElement validImage;

    @FindBy(xpath="//h1[normalize-space()='Broken Links - Images']/../img[2]")
    WebElement brokenImage;

    @FindBy(linkText="Click Here for Valid Link")
    WebElement validLink;

    @FindBy(linkText="Click Here for Broken Link")
    WebElement brokenLink;

    public BrokenLinksImages(WebDriver driver) {
        super(driver);
        this.URL = PageMenu.BrokenLinksImages.getURL();
    }

    public String validImageHeaderContentType(){
        return getHeaderContentType(validImage);
    }

    public String brokenImageHeaderContentType(){
        return getHeaderContentType(brokenImage);
    }

    public int isBrokenLinkStatusCode(){
        return getStatusCode(brokenLink);
    }

    public int isValidLinkStatusCode(){
        return getStatusCode(validLink);
    }

    private String getHeaderContentType(WebElement img){
        String URL = img.getAttribute("src");
        return given().get(URL).getHeader("Content-Type");
    }

    private int getStatusCode(WebElement link){
        String URL = link.getAttribute("href");
        return given().get(URL).getStatusCode();
    }
}
