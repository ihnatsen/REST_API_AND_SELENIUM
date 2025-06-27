package org.wit.edu.pl.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wit.edu.pl.WebDriverFactory;
import org.wit.edu.pl.elements.UploadAndDownload.UploadAndDownload;
import org.wit.edu.pl.support.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadAndDownloadIT {
    ThreadLocal<UploadAndDownload> thePage = new ThreadLocal<>();
    String browser = ConfigReader.get("browser");

    @BeforeEach
    public void setup(){
        thePage.set(new UploadAndDownload(WebDriverFactory.getWebDriver(browser)));
        UploadAndDownload page = thePage.get();
        page.openPage();
        page.deleteAdds();
    }

    @AfterEach
    public void closePage(){
        thePage.get().closePage();
    }

    @Test
    public void downloadFile(){
        UploadAndDownload page = thePage.get();
        assertTrue(page.isDownloadImg());
    }

    @Test
    public void loadFile(){
        String filePath = "C:\\Users\\Acer\\Downloads\\sampleFile.jpeg";
        UploadAndDownload page = thePage.get();
        page.selectFile(filePath);
        String loadedFilePath = page.getFilePath();
        assertTrue(loadedFilePath.contains("sampleFile.jpeg"));
    }
}
