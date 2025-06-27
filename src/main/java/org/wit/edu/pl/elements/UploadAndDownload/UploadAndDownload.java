package org.wit.edu.pl.elements.UploadAndDownload;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class UploadAndDownload extends BasePage {

    @FindBy(linkText="Download")
    WebElement download;

    @FindBy(id="uploadFile")
    WebElement input;

    @FindBy(id="uploadedFilePath")
    WebElement filePath;

    public UploadAndDownload(WebDriver driver) {
        super(driver);
        URL= PageMenu.UploadAndDownload.getURL();
    }

    public void selectFile(String filePath){
        input.sendKeys(filePath);
    }

    public String getFilePath(){
        return filePath.getText();
    }

    public boolean isDownloadImg(){
        boolean dataIsPresent = Optional.ofNullable(download.getAttribute("href")).isPresent();
        boolean downloadAttribute = Optional.ofNullable(download.getAttribute("download")).isPresent();
        return dataIsPresent && downloadAttribute;
    }

}
